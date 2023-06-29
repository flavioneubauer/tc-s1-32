package br.com.fiap.soat1.t32.pedidos.driver.vo.response;

import br.com.fiap.soat1.t32.pedidos.domain.CategoriaProduto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaProdutoData {

    private Long id;
    private BigDecimal preco;
    private String descricao;
    private CategoriaProduto categoria;

}
