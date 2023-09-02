package br.com.fiap.soat1.t32.configs;

import br.com.fiap.soat1.t32.pedidos.repositories.ProdutoRepository;
import br.com.fiap.soat1.t32.pedidos.use_cases.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfiguration {
	
	@Bean
	public ProdutoService produtoService(ProdutoRepository produtoRepository) {
		return new ProdutoService(produtoRepository);
	}
	
}
