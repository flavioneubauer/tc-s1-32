package br.com.fiap.soat1.t32.pagamentos.utils.mappers;

import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;
import br.com.fiap.soat1.t32.pagamentos.domain.ProdutoCheckout;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.request.CheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.request.ProdutoCheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.driver.vo.response.CheckoutResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckoutMapper {

    public static CheckoutVo toRequest(Checkout checkout) {
        return CheckoutVo.builder()
                .cliente(checkout.getCliente())
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
                .cliente(checkout.getCliente())
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
