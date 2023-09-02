package br.com.fiap.soat1.t32.pedidos.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PedidoProdutoKey implements Serializable {

    @Column
    private Long pedidoId;
    @Column
    private Long produtoId;

}
