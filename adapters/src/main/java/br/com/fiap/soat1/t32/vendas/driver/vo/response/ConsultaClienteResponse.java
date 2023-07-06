package br.com.fiap.soat1.t32.vendas.driver.vo.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaClienteResponse {

	private UUID id;
	private String nome;
	private String cpf;
	private String email;
}