package br.com.fiap.soat1.t32.pedidos.driver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat1.t32.pedidos.driver.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.driver.vo.response.CriacaoPedidoResponse;
import br.com.fiap.soat1.t32.pedidos.use_case.PedidoService;
import br.com.fiap.soat1.t32.pedidos.utils.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;

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

}
