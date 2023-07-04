package br.com.fiap.soat1.t32.pedidos.driven.entities;

import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;
import br.com.fiap.soat1.t32.vendas.driven.entities.ClienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PEDIDO")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Enumerated(STRING)
    private StatusPedido status;

    @ManyToOne
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "PEDIDO_PRODUTO",
            joinColumns = @JoinColumn(name = "PEDIDO_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID"))
    private List<ProdutoEntity> produtoEntities;


}
