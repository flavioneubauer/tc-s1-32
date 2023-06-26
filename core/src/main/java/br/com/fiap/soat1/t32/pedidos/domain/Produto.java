package br.com.fiap.soat1.t32.pedidos.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    private Long id;
    private BigDecimal preco;
    private CategoriaProduto categoria;
    private String descricao;

}
