package br.com.fiap.soat1.t32.pedidos.controllers;

import br.com.fiap.soat1.t32.pedidos.adapters.PedidoAdapter;
import br.com.fiap.soat1.t32.pedidos.apis.vo.request.PedidoVo;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.CriacaoPedidoResponse;
import br.com.fiap.soat1.t32.pedidos.apis.vo.response.ListaPedidosResponse;
import br.com.fiap.soat1.t32.pedidos.entities.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.pedidos.use_cases.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    public CriacaoPedidoResponse cadastrar(PedidoVo pedido) {
        var pedidoId = pedidoService.cadastrar(PedidoAdapter.toEntity(pedido));
        return CriacaoPedidoResponse.builder().id(pedidoId).build();
    }

    public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido) {
        pedidoService.alterarStatusPreparacaoPedido(id, statusPreparacaoPedido);
    }

    public ListaPedidosResponse listar() {
        return PedidoAdapter.toListaResponse(pedidoService.listar());
    }

    public String consultarStatusPagamento(Long idPedido) {
        final var statusPagamento =  pedidoService.consultarStatusPagamento(idPedido);

        if(!isNull(statusPagamento))
            return statusPagamento.name();

        return null;
    }

}
