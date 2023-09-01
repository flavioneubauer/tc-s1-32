package br.com.fiap.soat1.t32.vendas.adapter;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import br.com.fiap.soat1.t32.vendas.api.vo.request.ClienteVO;
import br.com.fiap.soat1.t32.vendas.api.vo.response.ConsultaClienteResponse;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ClienteAdapter {

	public static Cliente toEntity(ClienteVO clienteVO, UUID clienteId) {
		return Cliente.builder(clienteVO.getNome(),clienteVO.getCpf(),clienteVO.getEmail())
				.id(clienteId)
				.build();
	}
	
	public static ClienteDb toEntityDb(Cliente cliente) {
		return ClienteDb.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.cpf(cliente.getCpf())
				.email(cliente.getEmail())
				.build();
	}
	
	public static Cliente toEntity(ClienteDb clienteEntity) {
		return clienteEntity == null ? null : 
			Cliente.builder(clienteEntity.getNome(),clienteEntity.getCpf(),clienteEntity.getEmail())
				.id(clienteEntity.getId())
				.build();
	}
	
	public static ConsultaClienteResponse toResponse(Cliente cliente) {
		return ConsultaClienteResponse.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.cpf(cliente.getCpf())
				.email(cliente.getEmail())
				.build();
	}
}