package com.fgomes.picpayclone.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fgomes.picpayclone.constants.MensagemValidacao;
import com.fgomes.picpayclone.conversor.UsuarioConversor;
import com.fgomes.picpayclone.dto.UsuarioDTO;

import com.fgomes.picpayclone.configuration.exceptions.NegocioException;
import com.fgomes.picpayclone.model.Transacao;
import com.fgomes.picpayclone.model.Usuario;
import com.fgomes.picpayclone.repository.UsuarioRepository;
import com.fgomes.picpayclone.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConversor usuarioConversor;

    @Override
    public UsuarioDTO consultar(String login) {
        Usuario usuario = consultarEntidade(login);
        return usuarioConversor.converterEntidadeParaDto(usuario);
    }

    @Override
    @Transactional
    public Usuario consultarEntidade(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    @Async("asyncExecutor")
    @Transactional
    public void atualizarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito) {
        decrementarSaldo(transacaoSalva, isCartaoCredito);
        incrementarSaldo(transacaoSalva);
    }

    private void incrementarSaldo(Transacao transacaoSalva) {
        usuarioRepository.updateIncrementarSaldo(transacaoSalva.getDestino().getLogin(), transacaoSalva.getValor());
    }

    private void decrementarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito) {
        if (!isCartaoCredito) {
            usuarioRepository.updateDecrementarSaldo(transacaoSalva.getOrigem().getLogin(), transacaoSalva.getValor());
        }
    }

    @Override
    public void validar(Usuario... usuarios) {

        Arrays.asList(usuarios).stream().forEach(usuario -> {
            if (usuario == null) {
                throw new NegocioException(MensagemValidacao.ERRO_USUARIO_INEXISTENTE);
            }

        });
    }

    @Override
    public List<UsuarioDTO> listar(String login) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioConversor.converterEntidadesParaDtos(
                usuarios.stream().filter(v -> !v.getLogin().equals(login)).collect(Collectors.toList()));
    }

}