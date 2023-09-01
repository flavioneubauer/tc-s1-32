package br.com.fiap.soat1.t32.vendas.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "internalBuilder")
public class Cliente {

	private UUID id;
	@NonNull
	private String nome;
	@NonNull
	private Cpf cpf;
	@NonNull
	private Email email;
	
	public String getCpf() {
		return cpf.getCpf();
	}
	
	public String getEmail() {
		return email.getEmail();
	}
	
	public static ClienteBuilder builder(String nome, String cpf, String email) {
        return internalBuilder()
        		.nome(nome)
        		.cpf(new Cpf(cpf))
        		.email(new Email(email));
    }
	
}
