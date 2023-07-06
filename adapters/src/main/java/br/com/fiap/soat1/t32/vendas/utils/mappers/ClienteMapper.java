package br.com.fiap.soat1.t32.vendas.utils.mappers;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.driven.entities.ClienteEntity;
import br.com.fiap.soat1.t32.vendas.driver.vo.request.ClienteVO;
import br.com.fiap.soat1.t32.vendas.driver.vo.response.ConsultaClienteResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ClienteMapper {

	public static Cliente toDomain(ClienteVO clienteVO, UUID clienteId) {
		return Cliente.builder()
				.id(clienteId)
				.nome(clienteVO.getNome())
				.cpf(clienteVO.getCpf().replace(".", "").replace("-", ""))
				.email(clienteVO.getEmail())
				.build();
	}
	
	public static ClienteEntity toEntity(Cliente cliente) {
		return ClienteEntity.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.cpf(cliente.getCpf())
				.email(cliente.getEmail())
				.build();
	}
	
	public static Cliente toDomain(ClienteEntity clienteEntity) {
		return clienteEntity == null ? null :  Cliente.builder()
				.id(clienteEntity.getId())
				.nome(clienteEntity.getNome())
				.cpf(clienteEntity.getCpf())
				.email(clienteEntity.getEmail())
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