package br.com.fiap.soat1.t32.vendas.use_case;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fiap.soat1.t32.exceptions.NotFoundException;
import br.com.fiap.soat1.t32.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.vendas.utils.validadores.CpfValidator;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.ports.ClientePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClientePort clientePort;
	
	public void cadastrarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.clientePort.cadastrarCliente(cliente);
	}
	
	public Cliente consultarClientePorCpf(String cpf) {
		final var cliente = this.clientePort.consultarClientePorCpf(cpf);

		if(cliente == null) {
			throw new NotFoundException("Cliente não cadastrado.");
		}

		return cliente;
	}
	
	private void validarCliente(Cliente cliente) {
		if(!CpfValidator.isCPF(cliente.getCpf())) {
			throw new ValidationException("CPF informado inválido.");
		}
		if(!isEmailValido(cliente.getEmail())) {
			throw new ValidationException("E-mail informado inválido.");
		}	
	}
	
	private boolean isEmailValido(String email) {
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