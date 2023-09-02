package br.com.fiap.soat1.t32.pedidos.repositories;

import br.com.fiap.soat1.t32.pedidos.entities.Pedido;
import br.com.fiap.soat1.t32.pedidos.entities.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.PedidoDb;
import br.com.fiap.soat1.t32.pedidos.repositories.entities.ProdutoDb;
import br.com.fiap.soat1.t32.pedidos.adapters.PedidoAdapter;
import br.com.fiap.soat1.t32.pedidos.adapters.PedidoProdutoAdapter;
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
public class PedidoRepositoryImpl implements PedidoRepository {

	private final PedidoCrudRepository pedidoCrudRepository;
	private final ProdutoCrudRepository produtoCrudRepository;
	private final PedidoProdutoCrudRepository pedidoProdutoDAO;

	@Override
	@Transactional
	public Long cadastrar(Pedido pedido) {
		var pedidoEntity = PedidoAdapter.toEntityDb(pedido);

		pedidoEntity = pedidoCrudRepository.save(pedidoEntity);

		var produtos = getProdutos(pedido.getProdutos());

		var pedidoProdutosEntity = PedidoProdutoAdapter.toEntityDb(produtos, pedido.getProdutos(), pedidoEntity.getId());

		pedidoProdutoDAO.saveAll(pedidoProdutosEntity);

		return pedidoEntity.getId();
	}

	@Override
	public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido) {
		var pedido = pedidoCrudRepository.findById(id);
		pedido.ifPresent(pedidoEntity -> {
			pedidoEntity.setStatusPreparacao(statusPreparacaoPedido);
			pedidoCrudRepository.save(pedidoEntity);
		});
	}

	@Override
	public void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido) {
		var pedido = pedidoCrudRepository.findById(id);
		pedido.ifPresent(pedidoEntity -> {
			pedidoEntity.setStatusPagamento(statusPagamentoPedido);
			pedidoCrudRepository.save(pedidoEntity);
		});
	}

	@Override
	public List<Pedido> listar() {
		var listaPedidos = new ArrayList<Pedido>();
		var pedidos = pedidoCrudRepository.findAll();
		for (PedidoDb pedido : pedidos) {
			listaPedidos.add(PedidoAdapter.fromEntityDb(pedido));
		}
		return listaPedidos;
	}

	@Override
	public Pedido consultarPorId(Long idPedido) {
		var pedido = pedidoCrudRepository.findById(idPedido);

		return pedido.map(PedidoAdapter::fromEntityDb).orElse(null);
	}

	private Map<Long, ProdutoDb> getProdutos(List<PedidoProduto> pedidoProdutos) {
		var produtos = produtoCrudRepository.findAllById(pedidoProdutos.stream()
				.map(pedidoProduto -> pedidoProduto.getProduto().getId())
				.collect(Collectors.toSet()));

		var produtosMap = new HashMap<Long, ProdutoDb>();

		produtos.forEach(produto -> produtosMap.put(produto.getId(), produto));

		return produtosMap;
	}
}