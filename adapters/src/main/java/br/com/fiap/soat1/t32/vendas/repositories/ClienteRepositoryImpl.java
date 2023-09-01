package br.com.fiap.soat1.t32.vendas.repositories;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.exceptions.DuplicateKeyException;
import br.com.fiap.soat1.t32.vendas.adapter.ClienteAdapter;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.ports.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

	private final ClienteCrudRepository clienteCrudRepository;

	@Override
	public void cadastrar(Cliente cliente) {
		try {
		clienteCrudRepository.save(ClienteAdapter.toEntityDb(cliente));
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateKeyException("CPF informado já cadastrado!");
		}
	}

	@Override
	public Cliente consultarPorCpf(String cpf) {
		return ClienteAdapter.toEntity(clienteCrudRepository.findByCpf(cpf.replace(".", "").replace("-", "")));
	}
}