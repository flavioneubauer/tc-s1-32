package br.com.fiap.soat1.t32.pagamentos.controler;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pagamentos.adapter.PagamentoAdapter;
import br.com.fiap.soat1.t32.pagamentos.api.vo.request.PagamentoPedidoVo;
import br.com.fiap.soat1.t32.pagamentos.use_case.PagamentoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoWebhookController {

	private final PagamentoService pagamentoService;
	
	 public void recebeAtualizacaoPagamento(PagamentoPedidoVo pagamentoPedidoVo) {
		 pagamentoService.recebeAtualizacaoPagamento(PagamentoAdapter.toDomain(pagamentoPedidoVo));
	 }
}