package br.com.fiap.soat1.t32.pagamentos.apis.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoPedidoVo {

    private Long idPedido;
    private Boolean pagamentoAprovado;

}