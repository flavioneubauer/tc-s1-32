package br.com.fiap.soat1.t32.pedidos.use_cases;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pedidos.entities.Pedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido.PENDENTE;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;

    public Long cadastrar(Pedido pedido){
    	validarStatusInclusao(pedido);

    	return pedidoRepository.cadastrar(pedido);
    }

	public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido){
		validarPreparacaoAlteracao(id, statusPreparacaoPedido);

		pedidoRepository.alterarStatusPreparacaoPedido(id, statusPreparacaoPedido);
	}

	public void alterarStatusPagamentoPedido(Long id, StatusPagamentoPedido statusPagamentoPedido){
		pedidoRepository.alterarStatusPagamentoPedido(id, statusPagamentoPedido);
	}
	
	public List<Pedido> listar(){
		var pedidos = pedidoRepository.listar();

		pedidos.removeIf(pedido -> isNull(pedido.getStatusPreparacao()) ||
				pedido.getStatusPreparacao() == StatusPreparacaoPedido.FINALIZADO);

		Map<StatusPreparacaoPedido, Integer> orderMapping = new EnumMap<>(StatusPreparacaoPedido.class);
		orderMapping.put(StatusPreparacaoPedido.PRONTO, 0);
		orderMapping.put(StatusPreparacaoPedido.EM_PREPARACAO, 1);
		orderMapping.put(StatusPreparacaoPedido.RECEBIDO, 2);

		pedidos.sort(Comparator.comparing(pedido -> orderMapping.get(pedido.getStatusPreparacao())));

		return pedidos;
	}
	
	public StatusPagamentoPedido consultarStatusPagamento(Long idPedido) {
		Pedido pedido = pedidoRepository.consultarPorId(idPedido);
		
		if(Objects.nonNull(pedido)) {
			return pedido.getStatusPagamento();
		}
		return null;
	}

	private void validarPreparacaoAlteracao(Long idPedido, StatusPreparacaoPedido status) {
		Pedido pedido = pedidoRepository.consultarPorId(idPedido);

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

	private void validarStatusInclusao(final Pedido pedido) {
		if(!isNull(pedido.getStatusPreparacao())) {
			throw new ValidationException("Status de preparação não pode ser informado na criação do pedido.");
		} else if(!isNull(pedido.getStatusPagamento()) && !PENDENTE.equals(pedido.getStatusPagamento())) {
			throw new ValidationException("Status de pagamento informado deve ser PENDENTE na criação do pedido.");
		}
	}
}