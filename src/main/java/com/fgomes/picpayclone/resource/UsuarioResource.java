package com.fgomes.picpayclone.resource;

import java.util.List;

import com.fgomes.picpayclone.dto.UsuarioDTO;
import com.fgomes.picpayclone.resource.swagger.IUsuarioResource;
import com.fgomes.picpayclone.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource extends ResourceBase<UsuarioDTO> implements IUsuarioResource {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/{login}/saldo")
    public ResponseEntity<UsuarioDTO> consultarSaldo(@PageableDefault(page = 0, size = 20) Pageable paginacao,
                                                     @PathVariable String login) {
        UsuarioDTO usuarioDTO = usuarioService.consultar(login);
        return responderSucessoComItem(usuarioDTO);
    }

    @GetMapping("/contatos")
    @Override
    public ResponseEntity<List<UsuarioDTO>> listar(@RequestParam String login) {
        List<UsuarioDTO> usuarios = usuarioService.listar(login);
        return responderListaDeItens(usuarios);
    }

    @GetMapping("/{login}")
    public ResponseEntity<UsuarioDTO> consultar(@PathVariable String login) {
        UsuarioDTO usuario = usuarioService.consultar(login);
        return responderSucessoComItem(usuario);
    }

}
