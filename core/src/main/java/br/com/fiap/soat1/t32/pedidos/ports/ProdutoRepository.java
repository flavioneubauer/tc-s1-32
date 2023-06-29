package br.com.fiap.soat1.t32.pedidos.ports;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;

import java.util.Set;

public interface ProdutoRepository {

    void criarProduto(Produto produto);
    void excluirProduto(Long produtoId);
    void editarProduto(Produto produto);
    Set<Produto> consultarProdutoPorCategoria(CategoriaProduto categoriaProduto);

}
