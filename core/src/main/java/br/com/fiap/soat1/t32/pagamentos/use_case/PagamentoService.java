package br.com.fiap.soat1.t32.pagamentos.use_case;

import br.com.fiap.soat1.t32.pagamentos.domain.PagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPagamentoPedido;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.use_case.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Service
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
        //consultar pedido

        //validar se status do pedido não é RECUSADO
    }

}
