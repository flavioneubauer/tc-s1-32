package br.com.fiap.soat1.t32.pedidos.driven.repositories;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.driven.entities.PedidoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.entities.ProdutoEntity;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.PedidoDao;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.PedidoProdutoDAO;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.ProdutoDAO;
import br.com.fiap.soat1.t32.pedidos.ports.PedidoPort;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoMapper;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoProdutoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoGateway implements PedidoPort {

	private final PedidoDao pedidoDao;
	private final ProdutoDAO produtoDAO;
	private final PedidoProdutoDAO pedidoProdutoDAO;

	@Override
	@Transactional
	public Long criarPedido(Pedido pedido) {
		var pedidoEntity = PedidoMapper.toEntity(pedido);

		pedidoEntity = pedidoDao.save(pedidoEntity);

		var produtos = getProdutos(pedido.getProdutos());

		var pedidoProdutosEntity = PedidoProdutoMapper.toEntity(produtos, pedido.getProdutos(), pedidoEntity.getId());

		pedidoProdutoDAO.saveAll(pedidoProdutosEntity);

		return pedidoEntity.getId();
	}

	@Override
	public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido) {
		var pedido = pedidoDao.findById(id);
		pedido.ifPresent(pedidoEntity -> {
			pedidoEntity.setStatusPreparacao(statusPreparacaoPedido);
			pedidoDao.save(pedidoEntity);
		});
	}

	@Override
	public void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido) {
		var pedido = pedidoDao.findById(id);
		pedido.ifPresent(pedidoEntity -> {
			pedidoEntity.setStatusPagamento(statusPagamentoPedido);
			pedidoDao.save(pedidoEntity);
		});
	}

	@Override
	public List<Pedido> listarPedidos() {
		var listaPedidos = new ArrayList<Pedido>();
		var pedidos = pedidoDao.findAll();
		for (PedidoEntity pedido : pedidos) {
			listaPedidos.add(PedidoMapper.fromEntity(pedido));
		}
		return listaPedidos;
	}

	@Override
	public Pedido consultarPedido(Long idPedido) {
		var pedido = pedidoDao.findById(idPedido);

		return pedido.map(PedidoMapper::fromEntity).orElse(null);
	}

	private Map<Long, ProdutoEntity> getProdutos(List<PedidoProduto> pedidoProdutos) {
		var produtos = produtoDAO.findAllById(pedidoProdutos.stream()
				.map(pedidoProduto -> pedidoProduto.getProduto().getId())
				.collect(Collectors.toSet()));

		var produtosMap = new HashMap<Long, ProdutoEntity>();

		produtos.forEach(produto -> produtosMap.put(produto.getId(), produto));

		return produtosMap;
	}
}