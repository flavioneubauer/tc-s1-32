package br.com.fiap.soat1.t32.vendas.driven.repositories;

import org.springframework.stereotype.Repository;

import br.com.fiap.soat1.t32.utils.mappers.ClienteMapper;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.driven.repositories.dao.ClienteDao;
import br.com.fiap.soat1.t32.vendas.ports.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

	private final ClienteDao clienteDao;

	@Override
	public void cadastrarCliente(Cliente cliente) {
		clienteDao.save(ClienteMapper.map(cliente));
	}

	@Override
	public Cliente consultarClientePorCpf(String cpf) {
		return ClienteMapper.map(clienteDao.findByCpf(cpf));
	}
}