package br.com.fiap.soat1.t32.pagamentos.adapters;

import br.com.fiap.soat1.t32.pagamentos.apis.vo.request.PagamentoPedidoVo;
import br.com.fiap.soat1.t32.pagamentos.entities.PagamentoPedido;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PagamentoAdapter {

    public static PagamentoPedido toDomain(PagamentoPedidoVo pagamentoPedido) {
        return PagamentoPedido.builder()
                .idPedido(pagamentoPedido.getIdPedido())
                .pagamentoAprovado(pagamentoPedido.getPagamentoAprovado())
                .build();
    }

}
