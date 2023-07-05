package br.com.fiap.soat1.t32.pedidos.driver.vo.response;

import br.com.fiap.soat1.t32.pedidos.domain.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPedidosData {
    private Long id;
    private StatusPedido status;
}
