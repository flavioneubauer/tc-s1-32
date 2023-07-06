package br.com.fiap.soat1.t32.pedidos.utils.mappers;

import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.CriacaoProdutoResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoMapper {

    public static Produto toDomain(ProdutoVo produto, Long produtoId) {
        return Produto.builder()
                .id(produtoId)
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .build();
    }

    public static ProdutoEntity toEntity(Produto produto) {
        return ProdutoEntity.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

    public static Set<Produto> toDomain(Set<ProdutoEntity> produtos) {
        return produtos == null ? null : produtos.stream()
                .map(ProdutoMapper::toDomain)
                .collect(Collectors.toSet());
    }

    private static Produto toDomain(ProdutoEntity produto) {
        return Produto.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

    public static ConsultaProdutoResponse toResponse(Set<Produto> produtos) {

        return ConsultaProdutoResponse.builder()
                .produtos(produtos.stream()
                        .map(produto -> ConsultaProdutoData.builder()
                                .categoria(produto.getCategoria())
                                .descricao(produto.getDescricao())
                                .id(produto.getId())
                                .preco(produto.getPreco())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static CriacaoProdutoResponse toResponse(Long idProduto) {

        return CriacaoProdutoResponse.builder()
                .id(idProduto)
                .build();
    }

}
