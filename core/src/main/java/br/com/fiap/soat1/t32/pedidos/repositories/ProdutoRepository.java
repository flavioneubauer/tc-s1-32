package br.com.fiap.soat1.t32.pedidos.repositories;

import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.entities.Produto;

import java.util.Set;

public interface ProdutoRepository {

    Long cadastrar(Produto produto);
    void excluir(Long produtoId);
    void editar(Produto produto);
    Set<Produto> listarPorCategoria(CategoriaProduto categoriaProduto);
    Produto consultarPorId(Long id);

}
