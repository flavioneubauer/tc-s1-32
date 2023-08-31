package br.com.fiap.soat1.t32.pedidos.use_case;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
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
    	return pedidoPort.criarPedido(pedido);
    }

	public void alterarStatusPedido(Long id, StatusPedido statusPedido){
		validaStatusPedido(id, statusPedido);

		pedidoPort.alterarStatusPedido(id, statusPedido);
	}
	
	public List<Pedido> listarPedidos(){
		var pedidos = pedidoPort.listarPedidos();

		pedidos.removeIf(pedido -> pedido.getStatus() == StatusPedido.FINALIZADO);
		
        Map<StatusPedido, Integer> orderMapping = new EnumMap<>(StatusPedido.class);
        orderMapping.put(StatusPedido.PRONTO, 0);
        orderMapping.put(StatusPedido.EM_PREPARACAO, 1);
        orderMapping.put(StatusPedido.RECEBIDO, 2);

        pedidos.sort(Comparator.comparing(pedido -> orderMapping.get(pedido.getStatus())));

		return pedidos;
	}

	private void validaStatusPedido(Long idPedido, StatusPedido status) {
		Pedido pedido = pedidoPort.consultarPedido(idPedido);

		if(pedido == null) {
			throw new ValidationException("Pedido não localizado.");
		} else{
			if(StatusPedido.RECEBIDO == pedido.getStatus() &&
				StatusPedido.EM_PREPARACAO != status) {
				throw new ValidationException("Pedido RECEBIDO só pode ser alterado para EM PREPARAÇÃO.");
			} else if(StatusPedido.EM_PREPARACAO == pedido.getStatus() &&
					StatusPedido.PRONTO != status) {
				throw new ValidationException("Pedido EM PREPARAÇÃO só pode ser alterado para PRONTO.");
			} else if(StatusPedido.PRONTO == pedido.getStatus() &&
					StatusPedido.FINALIZADO != status) {
				throw new ValidationException("Pedido PRONTO só pode ser alterado para FINALIZADO.");
			}
		}
	}
}
