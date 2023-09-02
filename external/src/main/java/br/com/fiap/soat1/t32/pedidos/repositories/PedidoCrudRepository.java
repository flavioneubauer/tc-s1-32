package br.com.fiap.soat1.t32.pedidos.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoDb;

public interface PedidoCrudRepository extends CrudRepository<PedidoDb, Long> {

}
