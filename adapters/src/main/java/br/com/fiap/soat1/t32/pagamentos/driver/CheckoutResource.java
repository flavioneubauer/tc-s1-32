package br.com.fiap.soat1.t32.pagamentos.driver;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.request.CheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.response.CheckoutResponse;
import br.com.fiap.soat1.t32.pagamentos.use_case.CheckoutService;
import br.com.fiap.soat1.t32.pagamentos.utils.mappers.CheckoutMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Checkout", description = "API de Checkout de Pedidos")
@RequestMapping(value = "/v1/checkout",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutService checkoutService;

    @Operation(description = "Realiza checkout de pedido")
    @ApiResponse(responseCode = "201", description = "Checkout realizado")
    @ApiResponse(responseCode = "422", description = "Erro de validação",
        content = @Content(schema = @Schema(implementation = RespostaErro.class)))
    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<CheckoutResponse> realizaCheckout(@RequestBody CheckoutVo checkoutVo) {

        final var idPedido = checkoutService.realizarCheckout(CheckoutMapper.toDomain(checkoutVo));

        return ResponseEntity.status(HttpStatus.CREATED).body(CheckoutMapper.toResponse(idPedido));
    }
}
