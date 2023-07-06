package br.com.fiap.soat1.t32.vendas.driven.repositories.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.soat1.t32.vendas.driven.entities.ClienteEntity;

@Repository
public interface ClienteDao extends CrudRepository<ClienteEntity, Long>{

	ClienteEntity save(ClienteEntity cliente);
	
	ClienteEntity findByCpf(String cpf);
}