package br.com.fiap.soat1.t32.pedidos.utils.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.Produto;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ClientePedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.request.ProdutoPedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosData;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import br.com.fiap.soat1.t32.vendas.driven.entities.ClienteEntity;

@Service
public class PedidoMapper {

	private Cliente toClienteDomain(ClientePedidoVo clientePedidoVo) {
		if (clientePedidoVo == null)
			return null;
		return Cliente.builder().id(clientePedidoVo.getId()).build();
	}

	private Produto toProdutoDomain(ProdutoPedidoVo produtoPedidoVo) {
		return Produto.builder().id(produtoPedidoVo.getId()).build();
	}

	private List<Produto> toListaProdutoDomain(List<ProdutoPedidoVo> listaProdutoPedidoVo) {
		return listaProdutoPedidoVo.stream().map(this::toProdutoDomain).collect(Collectors.toList());
	}

	public Pedido toDomain(PedidoVo pedidoVo) {
		return Pedido.builder().produtos(toListaProdutoDomain(pedidoVo.getProdutos()))
				.cliente(toClienteDomain(pedidoVo.getCliente())).build();
	}

	private ProdutoEntity toProdutoEntity(Produto produto) {
		return ProdutoEntity.builder().id(produto.getId()).build();
	}

	private List<ProdutoEntity> toListaProdutoEntity(List<Produto> listaProduto) {
		return listaProduto.stream().map(this::toProdutoEntity).collect(Collectors.toList());
	}

	public PedidoEntity toEntity(Pedido pedido) {
		PedidoEntity pedidoEntity = new PedidoEntity();
		if (pedido.getCliente() != null) {
			pedidoEntity.setCliente(ClienteEntity.builder().id(pedido.getCliente().getId()).build());
		}
		if (pedido.getProdutos() != null && !pedido.getProdutos().isEmpty()) {
			pedidoEntity.setProdutoEntities(toListaProdutoEntity(pedido.getProdutos()));
		}
		pedidoEntity.setStatus(pedido.getStatus());
		return pedidoEntity;
	}

	private Cliente fromEntityToDomain(ClienteEntity clienteEntity) {
		if (clienteEntity == null)
			return null;
		return Cliente.builder().id(clienteEntity.getId()).build();
	}

	private Produto fromEntityToDomain(ProdutoEntity produtoEntity) {
		return Produto.builder().id(produtoEntity.getId()).build();
	}

	private List<Produto> fromEntityToDomain(List<ProdutoEntity> listaProdutoEntities) {
		if (listaProdutoEntities == null || listaProdutoEntities.isEmpty()) {
			return new ArrayList<Produto>();
		}
		return listaProdutoEntities.stream().map(this::fromEntityToDomain).collect(Collectors.toList());
	}

	public Pedido fromEntity(PedidoEntity pedidoEntity) {
		Pedido pedido = new Pedido();
		pedido.setId(pedidoEntity.getId());
		pedido.setCliente(fromEntityToDomain(pedidoEntity.getCliente()));
		pedido.setProdutos(fromEntityToDomain(pedidoEntity.getProdutoEntities()));
		pedido.setStatus(pedidoEntity.getStatus());
		return pedido;
	}

	public ListaPedidosData toListaVo(Pedido pedido) {
		return ListaPedidosData.builder().id(pedido.getId()).status(pedido.getStatus()).build();
	}

	public ListaPedidosResponse toListaResponse(List<Pedido> listaPedido) {
		return ListaPedidosResponse.builder()
				.pedidos(listaPedido.stream().map(this::toListaVo).collect(Collectors.toList())).build();
	}

}
