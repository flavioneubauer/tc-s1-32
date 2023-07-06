package br.com.fiap.soat1.t32.pedidos.driven.repositories.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoEntity;

public interface PedidoDao extends CrudRepository<PedidoEntity, Long> {

}
