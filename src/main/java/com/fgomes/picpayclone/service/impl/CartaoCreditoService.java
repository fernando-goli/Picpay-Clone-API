package com.fgomes.picpayclone.service.impl;

import com.fgomes.picpayclone.conversor.CartaoCreditoConversor;
import com.fgomes.picpayclone.dto.CartaoCreditoDTO;
import com.fgomes.picpayclone.model.CartaoCredito;
import com.fgomes.picpayclone.repository.CartaoCreditoRepository;
import com.fgomes.picpayclone.service.ICartaoCreditoService;
import com.fgomes.picpayclone.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoCreditoService implements ICartaoCreditoService {

    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @Autowired
    private CartaoCreditoConversor cartaoCreditoConversor;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCreditoDTO) {
        CartaoCreditoDTO cartaoCreditoRetorno = null;
        if (cartaoCreditoDTO.getIsSalva()) {
            CartaoCredito cartaoCredito = cartaoCreditoConversor.converterDtoParaEntidade(cartaoCreditoDTO);
            usuarioService.validar(cartaoCredito.getUsuario());
            CartaoCredito cartaoCreditoSalvo = cartaoCreditoRepository.save(cartaoCredito);
            cartaoCreditoRetorno = cartaoCreditoConversor.converterEntidadeParaDto(cartaoCreditoSalvo);
        }

        return cartaoCreditoRetorno;
    }

}