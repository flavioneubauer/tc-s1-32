package br.com.fiap.soat1.t32.vendas.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;

public interface ClienteCrudRepository extends CrudRepository<ClienteDb, Long>{

	@SuppressWarnings("unchecked")
	ClienteDb save(ClienteDb cliente);
	
	ClienteDb findByCpf(String cpf);
}