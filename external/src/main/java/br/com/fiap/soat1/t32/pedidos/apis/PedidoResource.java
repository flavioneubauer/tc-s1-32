package br.com.fiap.soat1.t32.pedidos.apis;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.CriacaoPedidoResponse;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.pedidos.controllers.PedidoController;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "Pedido", description = "API de Pedido")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
class PedidoResource {

	private final PedidoController pedidoController;

	@PostMapping("/v1/pedidos")
	@ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Inclui pedido")
	public ResponseEntity<CriacaoPedidoResponse> adicionarPedido(@RequestBody PedidoVo pedidoVo) {
		return ResponseEntity.status(CREATED).body(pedidoController.cadastrar(pedidoVo));
	}

	@PutMapping("/v1/pedidos/{id}/{status}")
	@ApiResponse(responseCode = "204", description = "Status de preparação do pedido alterado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Altera status de preparação do pedido")
	public ResponseEntity<Void> alterarStatusPreparacaoPedido(@PathVariable Long id, @PathVariable StatusPreparacaoPedido status) {
		pedidoController.alterarStatusPreparacaoPedido(id, status);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/v1/pedidos")
	@ApiResponse(responseCode = "200", description = "Status de pedido retornado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Lista pedidos")
	public ResponseEntity<ListaPedidosResponse> listarPedidos() {
		return ResponseEntity.ok(pedidoController.listar());
	}

	@GetMapping("/v1/pedidos/{idPedido}")
	@ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Consulta Status Pedido")
	public ResponseEntity<String> consultarStatusPagamentoPedido(@PathVariable Long idPedido) {
		return ResponseEntity.ok(pedidoController.consultarStatusPagamento(idPedido));
	}
}