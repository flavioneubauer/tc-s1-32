package br.com.fiap.soat1.t32.pedidos.driven.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;
import br.com.fiap.soat1.t32.pedidos.driven.repositories.dao.PedidoDao;
import br.com.fiap.soat1.t32.pedidos.ports.PedidoPort;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoGateway implements PedidoPort {

	private final PedidoDao pedidoDao;
	private final PedidoMapper pedidoMapper;

	@Override
	public Long criarPedido(Pedido pedido) {
		var pedidoEntity = pedidoMapper.toEntity(pedido);
		pedidoDao.save(pedidoEntity);
		return pedidoEntity.getId();
	}

	@Override
	public void alterarStatusPedido(Long id, StatusPedido statusPedido) {
		var pedido = pedidoDao.findById(id);
		pedido.ifPresent(pedidoEntity -> {
			pedidoEntity.setStatus(statusPedido);
			pedidoDao.save(pedidoEntity);
		});
	}

	@Override
	public List<Pedido> listarPedidos() {
		var listaPedidos = new ArrayList<Pedido>();
		var pedidos = pedidoDao.findAll();
		var iterator = pedidos.iterator();
		while(iterator.hasNext()) {
			listaPedidos.add(pedidoMapper.fromEntity(iterator.next()));
		}
		return listaPedidos;
	}

}
