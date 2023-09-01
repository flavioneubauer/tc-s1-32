package br.com.fiap.soat1.t32.vendas.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import lombok.Getter;

@Getter
public class Email {
	
	private String email;
	
	public Email(String email) {
		if(!isValido(email)) {
			throw new ValidationException("E-mail informado inválido.");
		}
		this.email = email;
	}
	
	private boolean isValido(String email) {
	    boolean isValido = false;
	    if (email != null && email.length() > 0) {
	        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	        Matcher matcher = pattern.matcher(email);
	        if (matcher.matches()) {
	            isValido = true;
	        }
	    }
	    return isValido;
	}
}
