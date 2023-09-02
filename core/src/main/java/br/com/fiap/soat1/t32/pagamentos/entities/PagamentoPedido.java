package br.com.fiap.soat1.t32.pagamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoPedido {

    private Long idPedido;
    private Boolean pagamentoAprovado;
}