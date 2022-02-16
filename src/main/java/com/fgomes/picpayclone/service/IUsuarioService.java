package com.fgomes.picpayclone.service;

import com.fgomes.picpayclone.dto.UsuarioDTO;
import com.fgomes.picpayclone.model.Transacao;
import com.fgomes.picpayclone.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario consultarEntidade(String login);

    UsuarioDTO consultar(String login);

    void atualizarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito);

    void validar(Usuario... usuarios);

    List<UsuarioDTO> listar(String login);

}