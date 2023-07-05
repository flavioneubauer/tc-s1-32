package br.com.fiap.soat1.t32.pedidos.ports;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;

public interface PedidoPort {
	
	Long criarPedido(Pedido pedido);
	void alterarStatusPedido(Long id, StatusPedido statusPedido);
}
