package br.com.fiap.soat1.t32.pedidos.domain;

import br.com.fiap.soat1.t32.vendas.domain.Cliente;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private Long id;
    private StatusPreparacaoPedido statusPreparacao;
    private StatusPagamentoPedido statusPagamento;
    private Cliente cliente;
    private List<PedidoProduto> produtos;
}
