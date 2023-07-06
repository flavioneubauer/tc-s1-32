package br.com.fiap.soat1.t32.pedidos.driver.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPedidosClienteData {

    private UUID id;
    private String nome;

}
