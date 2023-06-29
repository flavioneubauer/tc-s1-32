package br.com.fiap.soat1.t32.pedidos.driver.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaProdutoResponse {

    private List<ConsultaProdutoData> data;

}
