package com.fgomes.picpayclone.modelo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "TRANSACOES")
public class Transacao extends EntidadeBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "TR_CODIGO", nullable = false)
    private String codigo;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "TR_USUARIO_ORIGEM", nullable = false)
    private Usuario origem;

    @ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "TR_USUARIO_DESTINO", nullable = false)
    private Usuario destino;

    @Column(name = "TR_DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "TR_VALOR", nullable = false)
    private Double valor;

}