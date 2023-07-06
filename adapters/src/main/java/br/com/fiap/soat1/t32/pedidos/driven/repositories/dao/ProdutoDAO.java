package br.com.fiap.soat1.t32.pedidos.driven.repositories.dao;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface ProdutoDAO extends CrudRepository<ProdutoEntity, Long> {

    ProdutoEntity save(ProdutoEntity entity);
    
    void deleteById(Long id);

    Set<ProdutoEntity> findAllByCategoria(CategoriaProduto categoriaProduto);
}