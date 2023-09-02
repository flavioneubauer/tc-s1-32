package br.com.fiap.soat1.t32.pedidos.adapters;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.soat1.t32.pedidos.entities.Pedido;
import br.com.fiap.soat1.t32.pedidos.entities.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.entities.Produto;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoDb;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoProdutoDb;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.ClientePedidoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.ProdutoPedidoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosClienteData;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosData;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosProdutoData;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.vendas.entities.Cliente;
import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoAdapter {

	private static Cliente toClienteEntity(ClientePedidoVo clientePedidoVo) {
		if (clientePedidoVo == null)
			return null;
		var cliente = new Cliente();
		cliente.setId(clientePedidoVo.getId());
		return cliente;
	}

	private static PedidoProduto toPedidoProdutoEntity(ProdutoPedidoVo produtoPedidoVo) {
		return PedidoProduto.builder()
				.quantidade(produtoPedidoVo.getQuantidade())
				.produto(toProdutoEntity(produtoPedidoVo))
				.build();
	}

	private static PedidoProduto toPedidoProdutoEntity(PedidoProdutoDb entity) {
		return PedidoProduto.builder()
				.quantidade(entity.getQuantidade())
				.produto(toProdutoEntity(entity))
				.build();
	}

	private static Produto toProdutoEntity(ProdutoPedidoVo produtoPedidoVo) {
		return Produto.builder()
				.id(produtoPedidoVo.getId())
				.build();
	}

	private static Produto toProdutoEntity(PedidoProdutoDb entity) {

		var produto = entity.getProduto();

		return Produto.builder()
				.id(produto.getId())
				.descricao(produto.getDescricao())
				.preco(entity.getValorUnitario())
				.categoria(produto.getCategoria())
				.build();
	}

	private static List<PedidoProduto> toListaProdutoEntity(List<ProdutoPedidoVo> listaProdutoPedidoVo) {
		return listaProdutoPedidoVo.stream().map(PedidoAdapter::toPedidoProdutoEntity).collect(Collectors.toList());
	}

	public static Pedido toEntity(PedidoVo pedidoVo) {
		return Pedido.builder().produtos(toListaProdutoEntity(pedidoVo.getProdutos()))
				.cliente(toClienteEntity(pedidoVo.getCliente())).build();
	}

	public static PedidoDb toEntityDb(Pedido pedido) {
		PedidoDb pedidoDb = new PedidoDb();
		if (pedido.getCliente() != null) {
			pedidoDb.setCliente(ClienteDb.builder().id(pedido.getCliente().getId()).build());
		}
		pedidoDb.setStatusPreparacao(pedido.getStatusPreparacao());
		pedidoDb.setStatusPagamento(pedido.getStatusPagamento());
		return pedidoDb;
	}

	private static Cliente fromEntityToEntityDb(ClienteDb clienteEntity) {
		if (clienteEntity == null)
			return null;
		var cliente = new Cliente();
		cliente.setId(clienteEntity.getId());
		cliente.setNome(clienteEntity.getNome());
		return cliente;
	}

	public static Pedido fromEntityDb(PedidoDb pedidoDb) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoDb.getId());
		pedido.setCliente(fromEntityToEntityDb(pedidoDb.getCliente()));
		pedido.setStatusPagamento(pedidoDb.getStatusPagamento());
		pedido.setStatusPreparacao(pedidoDb.getStatusPreparacao());
		pedido.setProdutos(pedidoDb.getProdutos().stream()
						.map(PedidoAdapter::toPedidoProdutoEntity)
								.collect(Collectors.toList()));
		return pedido;
	}

	public static ListaPedidosData toListaVo(Pedido pedido) {
		return ListaPedidosData.builder()
				.id(pedido.getId())
				.statusPagamento(pedido.getStatusPagamento())
				.statusPreparacao(pedido.getStatusPreparacao())
				.produtos(toListaProdutosVo(pedido.getProdutos()))
				.cliente(toClienteVo(pedido.getCliente()))
				.build();
	}

	private static ListaPedidosClienteData toClienteVo(Cliente cliente) {
		return cliente == null ? null : ListaPedidosClienteData.builder()
				.nome(cliente.getNome())
				.id(cliente.getId())
				.build();
	}

	private static List<ListaPedidosProdutoData> toListaProdutosVo(List<PedidoProduto> pedidoProdutos) {
		return pedidoProdutos.stream()
				.map(produto -> ListaPedidosProdutoData.builder()
						.categoria(produto.getProduto().getCategoria())
						.id(produto.getProduto().getId())
						.descricao(produto.getProduto().getDescricao())
						.quantidade(produto.getQuantidade())
						.build())
				.collect(Collectors.toList());
	}

	public static ListaPedidosResponse toListaResponse(List<Pedido> listaPedido) {
		return ListaPedidosResponse.builder()
				.pedidos(listaPedido.stream()
						.map(PedidoAdapter::toListaVo)
						.collect(Collectors.toList())).build();
	}

}
