package tc.soat1.turma32.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tc.soat1.turma32.pedido.service.PedidoService;

@RestController
public class PedidoResource {
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/")
    public String adicionarPedido() {
      return pedidoService.adicionarPedido();
    }
  
}
