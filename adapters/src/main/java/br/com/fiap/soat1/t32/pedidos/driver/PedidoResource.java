package br.com.fiap.soat1.t32.pedidos.driver;

import br.com.fiap.soat1.t32.handler.vo.RespostaErro;
import br.com.fiap.soat1.t32.pedidos.domain.StatusPreparacaoPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.soat1.t32.pedidos.driver.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.CriacaoPedidoResponse;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.pedidos.use_case.PedidoService;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pedido", description = "API de Pedido")
@RestController
@RequiredArgsConstructor
class PedidoResource {

	private final PedidoService pedidoService;

	@PostMapping("/v1/pedidos")
	@ApiResponse(responseCode = "201", description = "Pedido criado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Inclui pedido")
	public CriacaoPedidoResponse adicionarPedido(@RequestBody PedidoVo pedidoVo) {
		var pedidoId = pedidoService.adicionarPedido(PedidoMapper.toDomain(pedidoVo));
		return CriacaoPedidoResponse.builder().id(pedidoId).build();
	}

	@PutMapping("/v1/pedidos/{id}/{status}")
	@ApiResponse(responseCode = "204", description = "Status de preparação do pedido alterado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Altera status de preparação do pedido")
	public ResponseEntity<Void> alterarStatusPreparacaoPedido(@PathVariable Long id, @PathVariable StatusPreparacaoPedido status) {
		pedidoService.alterarStatusPreparacaoPedido(id, status);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/v1/pedidos")
	@ApiResponse(responseCode = "200", description = "Pedidos listados com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Lista pedidos")
	public ResponseEntity<ListaPedidosResponse> listarPedidos() {
		return ResponseEntity.ok(PedidoMapper.toListaResponse(pedidoService.listarPedidos()));
	}

}
