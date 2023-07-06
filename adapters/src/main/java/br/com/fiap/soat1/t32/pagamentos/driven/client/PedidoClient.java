package br.com.fiap.soat1.t32.pagamentos.driven.client;

import br.com.fiap.soat1.t32.pagamentos.driven.client.vo.CriarPedidoResponse;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.request.CheckoutVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "pedidoClient", url = "${pedido.host}")
public interface PedidoClient {

    @PostMapping(value = "/v1/pedidos",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    CriarPedidoResponse criarPedido(@RequestBody CheckoutVo checkoutVo);

}
