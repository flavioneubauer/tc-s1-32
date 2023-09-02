package br.com.fiap.soat1.t32.pagamentos.use_cases;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pagamentos.entities.Checkout;
import br.com.fiap.soat1.t32.pedidos.entities.Pedido;
import br.com.fiap.soat1.t32.pedidos.entities.PedidoProduto;
import br.com.fiap.soat1.t32.pedidos.entities.Produto;
import br.com.fiap.soat1.t32.pedidos.use_cases.PedidoService;
import br.com.fiap.soat1.t32.vendas.entities.Cliente;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import static br.com.fiap.soat1.t32.pedidos.entities.StatusPagamentoPedido.PENDENTE;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class CheckoutService {

    private final PedidoService pedidoService;

    public Long realizarCheckout(Checkout checkout) {

        validaCheckout(checkout);

        return this.pedidoService.cadastrar(converterPedido(checkout));
    }

    private void validaCheckout(Checkout checkout) {

        if(checkout == null) {
            throw new ValidationException("Informar dados para checkout do pedido.");
        } else if(checkout.getProdutos() == null || checkout.getProdutos().isEmpty()) {
            throw new ValidationException("Produtos selecionados para checkout do pedido não foram informados.");
        }

    }

    private Pedido converterPedido(Checkout checkout) {
        return Pedido.builder()
                .statusPagamento(PENDENTE)
                .cliente(isNull(checkout.getCliente()) ? null : Cliente.builder().id(checkout.getCliente()).build())
                .produtos(checkout.getProdutos().stream()
                        .map(produto -> PedidoProduto.builder()
                                .produto(Produto.builder().id(produto.getId()).build())
                                .quantidade(produto.getQuantidade())
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }

}
