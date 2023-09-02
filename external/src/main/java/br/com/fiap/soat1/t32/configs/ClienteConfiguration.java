package br.com.fiap.soat1.t32.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.soat1.t32.vendas.repositories.ClienteRepository;
import br.com.fiap.soat1.t32.vendas.use_cases.ClienteService;

@Configuration
public class ClienteConfiguration {
	
	@Bean
	public ClienteService clienteService(ClienteRepository clienteRepository) {
		return new ClienteService(clienteRepository);
	}
	
}
