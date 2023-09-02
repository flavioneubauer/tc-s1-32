package br.com.fiap.soat1.t32.pedidos.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoDb {

    @EmbeddedId
    private PedidoProdutoKey id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    PedidoDb pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    ProdutoDb produto;

    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

}
