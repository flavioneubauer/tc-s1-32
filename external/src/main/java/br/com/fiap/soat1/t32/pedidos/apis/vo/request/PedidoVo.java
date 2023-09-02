package br.com.fiap.soat1.t32.pedidos.apis.vo.request;

import java.util.List;

import lombok.Data;

@Data
public class PedidoVo {
	private List<ProdutoPedidoVo> produtos;
	private ClientePedidoVo cliente;
}
