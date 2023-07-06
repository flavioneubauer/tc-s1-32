package br.com.fiap.soat1.t32.pedidos.ports;

import java.util.List;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;

public interface PedidoPort {
	
	Long criarPedido(Pedido pedido);
	void alterarStatusPedido(Long id, StatusPedido statusPedido);
	List<Pedido> listarPedidos();

	Pedido consultarPedido(Long idPedido);
}
