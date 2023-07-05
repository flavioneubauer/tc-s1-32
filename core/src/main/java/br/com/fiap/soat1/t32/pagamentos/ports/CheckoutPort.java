package br.com.fiap.soat1.t32.pagamentos.ports;

import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;

public interface CheckoutPort {

    Long criarPedido(Checkout checkout);
}
