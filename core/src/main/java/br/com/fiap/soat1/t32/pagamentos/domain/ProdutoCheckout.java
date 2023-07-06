package br.com.fiap.soat1.t32.pagamentos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoCheckout {

    private Long id;
    private Long quantidade;

}
