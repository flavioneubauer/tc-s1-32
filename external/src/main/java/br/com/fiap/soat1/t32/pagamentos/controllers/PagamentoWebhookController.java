package br.com.fiap.soat1.t32.pagamentos.controllers;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.pagamentos.adapters.PagamentoAdapter;
import br.com.fiap.soat1.t32.pagamentos.apis.vo.request.PagamentoPedidoVo;
import br.com.fiap.soat1.t32.pagamentos.use_cases.PagamentoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoWebhookController {

	private final PagamentoService pagamentoService;
	
	 public void recebeAtualizacaoPagamento(PagamentoPedidoVo pagamentoPedidoVo) {
		 pagamentoService.recebeAtualizacaoPagamento(PagamentoAdapter.toDomain(pagamentoPedidoVo));
	 }
}