package br.com.fiap.soat1.t32.pedidos.utils.mappers;

import br.com.fiap.soat1.t32.pedidos.domain.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoProdutoKey;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoProdutoMapper {

    public static List<PedidoProdutoEntity> toEntity(Map<Long, ProdutoEntity> produtos,
                                                     List<PedidoProduto> pedidoProdutos,
                                                     Long pedidoId) {
        return pedidoProdutos.stream()
                .map(pedidoProduto -> {
                    final var produto = produtos.get(pedidoProduto.getProduto().getId());

                    return PedidoProdutoEntity.builder()
                            .id(PedidoProdutoKey.builder()
                                    .pedidoId(pedidoId)
                                    .produtoId(produto.getId())
                                    .build())
                            .pedido(PedidoEntity.builder().id(pedidoId).build())
                            .produto(produto)
                            .quantidade(pedidoProduto.getQuantidade())
                            .valorUnitario(produto.getPreco())
                            .build();
                    }
                )
                .collect(Collectors.toList());
    }
}
