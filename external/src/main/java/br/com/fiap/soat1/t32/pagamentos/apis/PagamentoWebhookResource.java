package br.com.fiap.soat1.t32.pagamentos.apis;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pagamentos.apis.vo.request.PagamentoPedidoVo;
import br.com.fiap.soat1.t32.pagamentos.controllers.PagamentoWebhookController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;


@Tag(name = "Webhook", description = "Webhook de pagamento de pedido")
@RequestMapping(value = "/v1/webhook",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class PagamentoWebhookResource {

    private final PagamentoWebhookController pagamentoWebhookController;

    @Operation(description = "Endpoint para receber notificação acerca de alteração do status de pagamento do pedido.")
    @ApiResponse(responseCode = "200", description = "Pedido atualizado.")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
            content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> alteraPagamentoPedido(@RequestBody PagamentoPedidoVo pagamentoPedidoVo) {

    	pagamentoWebhookController.recebeAtualizacaoPagamento(pagamentoPedidoVo);

        return ResponseEntity.ok().build();
    }
}