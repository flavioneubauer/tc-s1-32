package br.com.fiap.soat1.t32.configs;

import br.com.fiap.soat1.t32.pagamentos.use_cases.CheckoutService;
import br.com.fiap.soat1.t32.pedidos.use_cases.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutConfiguration {
	
	@Bean
	public CheckoutService checkoutService(PedidoService pedidoService) {
		return new CheckoutService(pedidoService);
	}
	
}
