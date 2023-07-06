package br.com.fiap.soat1.t32.pedidos.domain;

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
