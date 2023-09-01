package br.com.fiap.soat1.t32.pedidos.utils.mappers;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ClientePedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoPedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosClienteData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosProdutoData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.repositories.entities.ClienteDb;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class PedidoMapper {

	private static Cliente toClienteDomain(ClientePedidoVo clientePedidoVo) {
		if (clientePedidoVo == null)
			return null;
		var cliente = new Cliente();
		cliente.setId(clientePedidoVo.getId());
		return cliente;
	}

	private static PedidoProduto toPedidoProdutoDomain(ProdutoPedidoVo produtoPedidoVo) {
		return PedidoProduto.builder()
				.quantidade(produtoPedidoVo.getQuantidade())
				.produto(toProdutoDomain(produtoPedidoVo))
				.build();
	}

	private static PedidoProduto toPedidoProdutoDomain(PedidoProdutoEntity entity) {
		return PedidoProduto.builder()
				.quantidade(entity.getQuantidade())
				.produto(toProdutoDomain(entity))
				.build();
	}

	private static Produto toProdutoDomain(ProdutoPedidoVo produtoPedidoVo) {
		return Produto.builder()
				.id(produtoPedidoVo.getId())
				.build();
	}

	private static Produto toProdutoDomain(PedidoProdutoEntity entity) {

		var produto = entity.getProduto();

		return Produto.builder()
				.id(produto.getId())
				.descricao(produto.getDescricao())
				.preco(entity.getValorUnitario())
				.categoria(produto.getCategoria())
				.build();
	}

	private static List<PedidoProduto> toListaProdutoDomain(List<ProdutoPedidoVo> listaProdutoPedidoVo) {
		return listaProdutoPedidoVo.stream().map(PedidoMapper::toPedidoProdutoDomain).collect(Collectors.toList());
	}

	public static Pedido toDomain(PedidoVo pedidoVo) {
		return Pedido.builder().produtos(toListaProdutoDomain(pedidoVo.getProdutos()))
				.cliente(toClienteDomain(pedidoVo.getCliente())).build();
	}

	public static PedidoEntity toEntity(Pedido pedido) {
		PedidoEntity pedidoEntity = new PedidoEntity();
		if (pedido.getCliente() != null) {
			pedidoEntity.setCliente(ClienteDb.builder().id(pedido.getCliente().getId()).build());
		}
		pedidoEntity.setStatusPreparacao(pedido.getStatusPreparacao());
		pedidoEntity.setStatusPagamento(pedido.getStatusPagamento());
		return pedidoEntity;
	}

	private static Cliente fromEntityToDomain(ClienteDb clienteEntity) {
		if (clienteEntity == null)
			return null;
		var cliente = new Cliente();
		cliente.setId(clienteEntity.getId());
		cliente.setNome(clienteEntity.getNome());
		return cliente;
	}

	public static Pedido fromEntity(PedidoEntity pedidoEntity) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoEntity.getId());
		pedido.setCliente(fromEntityToDomain(pedidoEntity.getCliente()));
		pedido.setStatusPagamento(pedidoEntity.getStatusPagamento());
		pedido.setStatusPreparacao(pedidoEntity.getStatusPreparacao());
		pedido.setProdutos(pedidoEntity.getProdutos().stream()
						.map(PedidoMapper::toPedidoProdutoDomain)
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
						.map(PedidoMapper::toListaVo)
						.collect(Collectors.toList())).build();
	}

}
