package br.com.fiap.soat1.t32.pedidos.driven.entities;

import br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.vendas.driven.entities.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PEDIDO")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(STRING)
    private StatusPreparacaoPedido statusPreparacao;

    @Column(nullable = false)
    @Enumerated(STRING)
    private StatusPagamentoPedido statusPagamento;

    @ManyToOne
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido")
    Set<PedidoProdutoEntity> produtos;

}
