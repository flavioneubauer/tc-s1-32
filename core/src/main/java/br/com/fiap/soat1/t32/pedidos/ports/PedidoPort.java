package br.com.fiap.soat1.t32.pedidos.ports;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;

public interface PedidoPort {
	
	Long criarPedido(Pedido pedido); 
}
