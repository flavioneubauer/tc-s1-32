package br.com.fiap.soat1.t32.pagamentos.use_cases;

import static java.lang.Boolean.TRUE;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pagamentos.entities.PagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.use_cases.PedidoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PagamentoService {

    private final PedidoService pedidoService;

    public void recebeAtualizacaoPagamento(PagamentoPedido pagamentoPedido) {
        validaStatusPagamento(pagamentoPedido.getIdPedido());

        if(TRUE.equals(pagamentoPedido.getPagamentoAprovado())) {
            this.pedidoService.alterarStatusPagamentoPedido(pagamentoPedido.getIdPedido(), StatusPagamentoPedido.APROVADO);
            this.pedidoService.alterarStatusPreparacaoPedido(pagamentoPedido.getIdPedido(), StatusPreparacaoPedido.RECEBIDO);
        } else {
            this.pedidoService.alterarStatusPagamentoPedido(pagamentoPedido.getIdPedido(), StatusPagamentoPedido.RECUSADO);
        }
    }

    private void validaStatusPagamento(Long idPedido) {
    	if(StatusPagamentoPedido.APROVADO.equals(pedidoService.consultarStatusPagamento(idPedido))) {
    		throw new ValidationException("Já consta aprovação do pagamento para o pedido " + idPedido);
    	}
    }
}