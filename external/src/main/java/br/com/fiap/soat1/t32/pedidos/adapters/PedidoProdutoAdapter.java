package br.com.fiap.soat1.t32.pedidos.adapters;

import br.com.fiap.soat1.t32.pedidos.entities.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoDb;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoProdutoDb;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoProdutoKey;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.ProdutoDb;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoProdutoAdapter {

    public static List<PedidoProdutoDb> toEntityDb(Map<Long, ProdutoDb> produtos,
                                                   List<PedidoProduto> pedidoProdutos,
                                                   Long pedidoId) {
        return pedidoProdutos.stream()
                .map(pedidoProduto -> {
                    final var produto = produtos.get(pedidoProduto.getProduto().getId());

                    return PedidoProdutoDb.builder()
                            .id(PedidoProdutoKey.builder()
                                    .pedidoId(pedidoId)
                                    .produtoId(produto.getId())
                                    .build())
                            .pedido(PedidoDb.builder().id(pedidoId).build())
                            .produto(produto)
                            .quantidade(pedidoProduto.getQuantidade())
                            .valorUnitario(produto.getPreco())
                            .build();
                    }
                )
                .collect(Collectors.toList());
    }
}
