package br.com.fiap.soat1.t32.pedidos.ports;

import java.util.List;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;

public interface PedidoPort {
	
	Long criarPedido(Pedido pedido);
	void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido);
	void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido);
	List<Pedido> listarPedidos();

	Pedido consultarPedido(Long idPedido);
}
