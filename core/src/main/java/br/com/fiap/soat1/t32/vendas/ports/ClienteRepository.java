package br.com.fiap.soat1.t32.vendas.ports;

import br.com.fiap.soat1.t32.vendas.domain.Cliente;

public interface ClienteRepository {

	void cadastrarCliente(Cliente cliente);
	
	Cliente consultarClientePorCpf(String cpf);
}