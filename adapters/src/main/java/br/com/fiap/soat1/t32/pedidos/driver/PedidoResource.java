package br.com.fiap.soat1.t32.pedidos.driver;

import br.com.fiap.soat1.t32.pedidos.driver.vo.PedidoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.soat1.t32.pedidos.use_case.PedidoService;

@RestController
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/")
    public String adicionarPedido(PedidoVo pedidoVo) {
        return pedidoService.adicionarPedido();
    }

}
