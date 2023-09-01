package br.com.fiap.soat1.t32.pagamentos.apis.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutVo {

    private List<ProdutoCheckoutVo> produtos;
    private ClienteCheckoutVo cliente;

}
