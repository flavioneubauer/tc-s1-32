package br.com.fiap.soat1.t32.pagamentos.adapters;

import br.com.fiap.soat1.t32.pagamentos.apis.vo.request.CheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.apis.vo.response.CheckoutResponse;
import br.com.fiap.soat1.t32.pagamentos.entities.Checkout;
import br.com.fiap.soat1.t32.pagamentos.entities.ProdutoCheckout;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckoutAdapter {

    public static Checkout toDomain(CheckoutVo checkout) {
        return Checkout.builder()
                .cliente(checkout.getCliente() == null ? null : checkout.getCliente().getId())
                .produtos(checkout.getProdutos().stream()
                        .map(produto -> ProdutoCheckout.builder()
                                .id(produto.getId())
                                .quantidade(produto.getQuantidade())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static CheckoutResponse toResponse(Long idPedido) {
        return CheckoutResponse.builder()
                .idPedido(idPedido)
                .build();
    }

}
