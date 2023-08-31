package br.com.fiap.soat1.t32.pedidos.use_case;

import java.util.List;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pedidos.domain.Pedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.ports.PedidoPort;
import lombok.RequiredArgsConstructor;

import static br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido.PENDENTE;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoPort pedidoPort;

    public Long adicionarPedido(Pedido pedido){
    	validarStatusInclusaoPedido(pedido);

    	return pedidoPort.criarPedido(pedido);
    }

	public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido){
		validarPreparacaoAlteracaoPedido(id, statusPreparacaoPedido);

		pedidoPort.alterarStatusPreparacaoPedido(id, statusPreparacaoPedido);
	}

	public void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido){
		pedidoPort.alterarStatusPagamentoPedido(id, statusPagamentoPedido);
	}
	
	public List<Pedido> listarPedidos(){
		return pedidoPort.listarPedidos();
	}

	private void validarPreparacaoAlteracaoPedido(Long idPedido, StatusPreparacaoPedido status) {
		Pedido pedido = pedidoPort.consultarPedido(idPedido);

		if(pedido == null) {
			throw new ValidationException("Pedido não localizado.");
		} else{
			if(isNull(pedido.getStatusPreparacao()) &&
					StatusPreparacaoPedido.RECEBIDO != status) {
				throw new ValidationException("Pedido sem status de preparação só pode ser alterado para RECEBIDO.");
			} else if(StatusPreparacaoPedido.RECEBIDO == pedido.getStatusPreparacao() &&
				StatusPreparacaoPedido.EM_PREPARACAO != status) {
				throw new ValidationException("Pedido RECEBIDO só pode ser alterado para EM PREPARAÇÃO.");
			} else if(StatusPreparacaoPedido.EM_PREPARACAO == pedido.getStatusPreparacao() &&
					StatusPreparacaoPedido.PRONTO != status) {
				throw new ValidationException("Pedido EM PREPARAÇÃO só pode ser alterado para PRONTO.");
			} else if(StatusPreparacaoPedido.PRONTO == pedido.getStatusPreparacao() &&
					StatusPreparacaoPedido.FINALIZADO != status) {
				throw new ValidationException("Pedido PRONTO só pode ser alterado para FINALIZADO.");
			}
		}
	}

	private void validarStatusInclusaoPedido(final Pedido pedido) {
		if(!isNull(pedido.getStatusPreparacao())) {
			throw new ValidationException("Status de preparação não pode ser informado na criação do pedido.");
		} else if(isNull(pedido.getStatusPagamento()) || !PENDENTE.equals(pedido.getStatusPagamento())) {
			throw new ValidationException("Status de pagamento informado deve ser PENDENTE na criação do pedido.");
		}
	}
}
