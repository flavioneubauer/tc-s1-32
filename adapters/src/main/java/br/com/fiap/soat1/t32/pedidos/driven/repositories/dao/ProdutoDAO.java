package br.com.fiap.soat1.t32.pedidos.driven.repositories.dao;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProdutoDAO extends org.springframework.data.repository.CrudRepository<ProdutoEntity, Long> {

    ProdutoEntity save(ProdutoEntity entity);
    void deleteById(Long id);

    Set<ProdutoEntity> findAllByCategoria(CategoriaProduto categoriaProduto);
}
