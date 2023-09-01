package br.com.fiap.soat1.t32.pagamentos.adapter;

import br.com.fiap.soat1.t32.pagamentos.api.vo.request.CheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.api.vo.request.ClienteCheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.api.vo.request.ProdutoCheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.api.vo.response.CheckoutResponse;
import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;
import br.com.fiap.soat1.t32.pagamentos.domain.ProdutoCheckout;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckoutAdapter {

    public static CheckoutVo toRequest(Checkout checkout) {
        return CheckoutVo.builder()
                .cliente(checkout.getCliente() == null ? null : ClienteCheckoutVo.builder()
                        .id(checkout.getCliente())
                        .build())
                .produtos(checkout.getProdutos().stream()
                        .map(produto -> ProdutoCheckoutVo.builder()
                                .id(produto.getId())
                                .quantidade(produto.getQuantidade())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

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
