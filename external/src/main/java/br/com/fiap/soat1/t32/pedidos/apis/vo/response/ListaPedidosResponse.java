package br.com.fiap.soat1.t32.pedidos.apis.vo.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPedidosResponse {

	private List<ListaPedidosData> pedidos;
}
