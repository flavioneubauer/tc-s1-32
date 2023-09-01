package br.com.fiap.soat1.t32.vendas.apis.vo.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVO {

	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String email;
}