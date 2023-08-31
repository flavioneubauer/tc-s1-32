package br.com.fiap.soat1.t32.pagamentos.utils.mappers;

import br.com.fiap.soat1.t32.pagamentos.domain.PagamentoPedido;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.request.PagamentoPedidoVo;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PagamentoMapper {

    public static PagamentoPedido toDomain(PagamentoPedidoVo pagamentoPedido) {
        return PagamentoPedido.builder()
                .idPedido(pagamentoPedido.getIdPedido())
                .pagamentoAprovado(pagamentoPedido.getPagamentoAprovado())
                .build();
    }

}
