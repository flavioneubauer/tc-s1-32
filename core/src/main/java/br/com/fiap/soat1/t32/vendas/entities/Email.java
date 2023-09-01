package br.com.fiap.soat1.t32.vendas.entities;

import br.com.fiap.soat1.t32.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Email(String email) {

	public Email {
		if (!isValido(email)) {
			throw new ValidationException("E-mail informado inválido.");
		}
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
