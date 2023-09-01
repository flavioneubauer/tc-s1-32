package br.com.fiap.soat1.t32.pedidos.repositories;

import java.util.List;

import br.com.fiap.soat1.t32.pedidos.entities.Pedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;

public interface PedidoRepository {
	
	Long cadastrar(Pedido pedido);
	void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido);
	void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido);
	List<Pedido> listar();

	Pedido consultarPorId(Long idPedido);
}
