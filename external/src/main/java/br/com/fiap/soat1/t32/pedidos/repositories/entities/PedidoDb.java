package br.com.fiap.soat1.t32.pedidos.repositories.entities;

import br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;
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
public class PedidoDb {

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
    private ClienteDb cliente;

    @OneToMany(mappedBy = "pedido")
    Set<PedidoProdutoDb> produtos;

}
