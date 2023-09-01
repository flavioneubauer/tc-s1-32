package br.com.fiap.soat1.t32.vendas.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

	private UUID id;
	private String nome;
	private Cpf cpf;
	private Email email;
	
	public String getCpf() {
		return cpf.cpf();
	}
	
	public String getEmail() {
		return email.email();
	}
	
}
