package br.com.fiap.soat1.t32.pedidos.entities;

import br.com.fiap.soat1.t32.vendas.entities.Cliente;
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