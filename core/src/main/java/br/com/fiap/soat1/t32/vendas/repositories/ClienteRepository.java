package br.com.fiap.soat1.t32.vendas.repositories;

import br.com.fiap.soat1.t32.vendas.entities.Cliente;

public interface ClienteRepository {

	void cadastrar(Cliente cliente);
	
	Cliente consultarPorCpf(String cpf);
}