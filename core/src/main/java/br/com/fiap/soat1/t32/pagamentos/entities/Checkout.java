package br.com.fiap.soat1.t32.pagamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Checkout {

    private List<ProdutoCheckout> produtos;

    private UUID cliente;

}
