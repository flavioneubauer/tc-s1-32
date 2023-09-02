package br.com.fiap.soat1.t32.pedidos.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProduto {

    private Produto produto;
    private Long quantidade;

}
