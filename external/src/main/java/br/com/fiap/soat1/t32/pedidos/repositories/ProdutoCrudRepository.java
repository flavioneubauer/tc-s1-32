package br.com.fiap.soat1.t32.pedidos.repositories;

import br.com.fiap.soat1.t32.pedidos.entities.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.ProdutoDb;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProdutoCrudRepository extends CrudRepository<ProdutoDb, Long> {

    ProdutoDb save(ProdutoDb entity);
    
    void deleteById(Long id);

    Set<ProdutoDb> findAllByCategoria(CategoriaProduto categoriaProduto);
}