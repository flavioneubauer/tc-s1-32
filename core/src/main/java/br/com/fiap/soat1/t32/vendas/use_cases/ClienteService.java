package br.com.fiap.soat1.t32.vendas.use_cases;

import br.com.fiap.soat1.t32.exceptions.NotFoundException;
import br.com.fiap.soat1.t32.vendas.entities.Cliente;
import br.com.fiap.soat1.t32.vendas.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClienteService {

private final ClienteRepository clienteRepository;
	
	public void cadastrar(Cliente cliente) {
		clienteRepository.cadastrar(cliente);
	}
	
	public Cliente consultarPorCpf(String cpf) {
		final var cliente = clienteRepository.consultarPorCpf(cpf);
		if(cliente == null) {
			throw new NotFoundException("Cliente não cadastrado.");
		}
		return cliente;
	}
}
