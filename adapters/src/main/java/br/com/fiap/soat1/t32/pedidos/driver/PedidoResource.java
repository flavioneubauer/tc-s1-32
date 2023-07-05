package br.com.fiap.soat1.t32.pedidos.driver;

import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.soat1.t32.pedidos.driver.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.CriacaoPedidoResponse;
import br.com.fiap.soat1.t32.pedidos.use_case.PedidoService;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pedido", description = "API de Pedido")
@RestController
@RequiredArgsConstructor
class PedidoResource {

	private final PedidoService pedidoService;
	private final PedidoMapper pedidoMapper;

	@PostMapping("/v1/pedidos")
	public CriacaoPedidoResponse adicionarPedido(@RequestBody PedidoVo pedidoVo) {
		var pedidoId = pedidoService.adicionarPedido(pedidoMapper.toDomain(pedidoVo));
		return CriacaoPedidoResponse.builder().id(pedidoId).build();
	}

	@PutMapping("/v1/pedidos/{id}/{status}")
	public ResponseEntity<Void> alterarStatusPedido(@PathVariable Long id, @PathVariable StatusPedido status) {
		pedidoService.alterarStatusPedido(id, status);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/v1/pedidos")
	public ResponseEntity<ListaPedidosResponse> listarPedidos() {
		return ResponseEntity.ok(PedidoMapper.toResponse(pedidoService.listarPedidos())).build();
	}

}
