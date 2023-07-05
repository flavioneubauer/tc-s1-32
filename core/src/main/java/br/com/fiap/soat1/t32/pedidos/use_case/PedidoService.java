package br.com.fiap.soat1.t32.pedidos.use_case;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;
import br.com.fiap.soat1.t32.pedidos.ports.PedidoPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoPort pedidoPort;

    public Long adicionarPedido(Pedido pedido){
    	pedido.setStatus(StatusPedido.RECEBIDO);
    	var pedidoId = pedidoPort.criarPedido(pedido);
    	return pedidoId;
    }
}
