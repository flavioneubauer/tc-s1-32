package br.com.fiap.soat1.t32.vendas.driven.repositories;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.exceptions.DuplicateKeyException;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.driven.repositories.dao.ClienteDao;
import br.com.fiap.soat1.t32.vendas.ports.ClientePort;
import br.com.fiap.soat1.t32.vendas.utils.mappers.ClienteMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteGateway implements ClientePort {

	private final ClienteDao clienteDao;

	@Override
	public void cadastrarCliente(Cliente cliente) {
		try {
		clienteDao.save(ClienteMapper.toEntity(cliente));
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateKeyException("CPF informado já cadastrado!");
		}
	}

	@Override
	public Cliente consultarClientePorCpf(String cpf) {
		return ClienteMapper.toDomain(clienteDao.findByCpf(cpf.replace(".", "").replace("-", "")));
	}
}