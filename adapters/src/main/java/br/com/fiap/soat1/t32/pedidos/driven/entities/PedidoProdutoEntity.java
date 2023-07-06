package br.com.fiap.soat1.t32.pedidos.driven.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoEntity {

    @EmbeddedId
    private PedidoProdutoKey id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    PedidoEntity pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    ProdutoEntity produto;

    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

}
