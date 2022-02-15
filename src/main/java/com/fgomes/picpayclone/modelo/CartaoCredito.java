package com.fgomes.picpayclone.modelo;

import com.fgomes.picpayclone.enums.BandeiraCartao;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "USUARIOS")
public class CartaoCredito extends EntidadeBase{
    @Column(name = "CC_NUMERO", nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "CC_BANDEIRA", nullable = false)
    private BandeiraCartao bandeira;

    @Column(name = "CC_TOKEN")
    private String numeroToken;

    @ManyToOne(cascade = {CascadeType.MERGE })
    @JoinColumn(name = "CC_USUARIO_ID", nullable = false)
    private Usuario usuario;
}
