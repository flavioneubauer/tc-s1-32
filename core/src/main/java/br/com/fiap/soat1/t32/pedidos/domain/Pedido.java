package br.com.fiap.soat1.t32.pedidos.domain;

import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private Long id;
    private StatusPedido status;
    private Cliente cliente;
    private List<Produto> produtos;
}
