package br.com.fiap.soat1.t32.pedidos.driver.vo.response;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPedidosProdutoData {

    private Long id;
    private String descricao;
    private CategoriaProduto categoria;
    private Long quantidade;

}
