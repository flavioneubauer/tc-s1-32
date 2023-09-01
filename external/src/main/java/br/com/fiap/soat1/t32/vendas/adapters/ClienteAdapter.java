package br.com.fiap.soat1.t32.vendas.adapters;

import static lombok.AccessLevel.PRIVATE;

import java.util.UUID;

import br.com.fiap.soat1.t32.vendas.apis.vo.request.ClienteVO;
import br.com.fiap.soat1.t32.vendas.apis.vo.response.ConsultaClienteResponse;
import br.com.fiap.soat1.t32.vendas.entities.Cliente;
import br.com.fiap.soat1.t32.vendas.entities.Cpf;
import br.com.fiap.soat1.t32.vendas.entities.Email;
import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ClienteAdapter {

	public static Cliente toEntity(ClienteVO clienteVO, UUID clienteId) {
		return Cliente.builder()
				.id(clienteId)
				.email(new Email(clienteVO.getEmail()))
				.cpf(new Cpf(clienteVO.getCpf().replace(".", "").replace("-", "")))
				.nome(clienteVO.getNome())
				.build();
	}
	
	public static ClienteDb toEntityDb(Cliente cliente) {
		return ClienteDb.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.cpf(cliente.getCpf().replace(".", "").replace("-", ""))
				.email(cliente.getEmail())
				.build();
	}
	
	public static Cliente toEntity(ClienteDb clienteEntity) {
		return clienteEntity == null ? null : 
			Cliente.builder()
				.id(clienteEntity.getId())
				.cpf(new Cpf(clienteEntity.getCpf()))
				.email(new Email(clienteEntity.getEmail()))
				.nome(clienteEntity.getNome())
				.build();
	}
	
	public static ConsultaClienteResponse toResponse(Cliente cliente) {
		return ConsultaClienteResponse.builder()
				.id(cliente.getId())
				.nome(cliente.getNome())
				.cpf(cliente.getCpf().replace(".", "").replace("-", ""))
				.email(cliente.getEmail())
				.build();
	}
}