package com.fgomes.picpayclone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    @NotBlank
    private String login;

    private String senha;

    private String email;

    private String nomeCompleto;

    private String cpf;

    private LocalDate dataNascimento;

    private String numeroTelefone;

    private Double saldo;

}