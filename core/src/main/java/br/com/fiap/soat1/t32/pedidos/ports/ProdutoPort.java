package br.com.fiap.soat1.t32.pedidos.ports;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;

import java.util.Set;

public interface ProdutoPort {

    Long criarProduto(Produto produto);
    void excluirProduto(Long produtoId);
    void editarProduto(Produto produto);
    Set<Produto> listarProdutosPorCategoria(CategoriaProduto categoriaProduto);
    Produto consultarProduto(Long id);

}
