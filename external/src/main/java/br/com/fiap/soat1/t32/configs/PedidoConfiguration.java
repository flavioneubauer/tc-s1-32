package br.com.fiap.soat1.t32.configs;

import br.com.fiap.soat1.t32.pedidos.repositories.PedidoRepository;
import br.com.fiap.soat1.t32.pedidos.use_cases.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfiguration {
	
	@Bean
	public PedidoService pedidoService(PedidoRepository pedidoRepository) {
		return new PedidoService(pedidoRepository);
	}
	
}
