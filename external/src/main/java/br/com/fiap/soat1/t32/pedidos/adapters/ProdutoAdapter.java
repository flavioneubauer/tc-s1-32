package br.com.fiap.soat1.t32.pedidos.adapters;

import br.com.fiap.soat1.t32.pedidos.entities.Produto;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.ProdutoDb;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.ProdutoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ConsultaProdutoData;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ConsultaProdutoResponse;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.CriacaoProdutoResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ProdutoAdapter {

    public static Produto toEntity(ProdutoVo produto, Long produtoId) {
        return Produto.builder()
                .id(produtoId)
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .build();
    }

    public static ProdutoDb toEntityDb(Produto produto) {
        return ProdutoDb.builder()
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .id(produto.getId())
                .preco(produto.getPreco())
                .build();
    }

    public static Set<Produto> toEntity(Set<ProdutoDb> produtos) {
        return produtos == null ? null : produtos.stream()
                .map(ProdutoAdapter::toEntity)
                .collect(Collectors.toSet());
    }

    public static Produto toEntity(ProdutoDb produto) {
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
