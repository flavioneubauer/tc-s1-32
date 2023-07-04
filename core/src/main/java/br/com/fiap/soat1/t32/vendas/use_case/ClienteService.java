package br.com.fiap.soat1.t32.vendas.use_case;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.utils.validadores.CpfValidator;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.ports.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	public void cadastrarCliente(Cliente cliente) {
		validarCliente(cliente);
		this.clienteRepository.cadastrarCliente(cliente);
	}
	
	public Cliente consultarClientePorCpf(String cpf) {
		return this.clienteRepository.consultarClientePorCpf(cpf);
	}
	
	private void validarCliente(Cliente cliente) {
		if(!CpfValidator.isCPF(cliente.getCpf())) {
			throw new RuntimeException("CPF informado inválido.");
		}
		if(!isEmailValido(cliente.getEmail())) {
			throw new RuntimeException("E-mail informado inválido.");
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