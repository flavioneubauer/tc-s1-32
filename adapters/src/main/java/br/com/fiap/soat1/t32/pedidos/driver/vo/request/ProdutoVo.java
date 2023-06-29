package br.com.fiap.soat1.t32.pedidos.driver.vo.request;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVo {

    @NotNull
    private BigDecimal preco;

    @NotNull
    private String descricao;

    @NotNull
    private CategoriaProduto categoria;
}
