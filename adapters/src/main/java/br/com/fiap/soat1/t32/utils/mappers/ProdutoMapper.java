package br.com.fiap.soat1.t32.utils.mappers;

import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ConsultaProdutoResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoMapper {

    public static Produto map(ProdutoVo produto, Long produtoId) {
        return Produto.builder()
                .id(produtoId)
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .build();
    }

    public static ProdutoEntity map(Produto produto) {
        return ProdutoEntity.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

    public static Set<Produto> map(Set<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(ProdutoMapper::map)
                .collect(Collectors.toSet());
    }

    private static Produto map(ProdutoEntity produto) {
        return Produto.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

    public static ConsultaProdutoResponse mapResponse(Set<Produto> produtos) {

        return ConsultaProdutoResponse.builder()
                .data(produtos.stream()
                        .map(ProdutoMapper::mapData)
                        .collect(Collectors.toList()))
                .build();
    }

    private static ConsultaProdutoData mapData(Produto produto) {
        return ConsultaProdutoData.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

}
