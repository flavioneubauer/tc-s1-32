package br.com.fiap.soat1.t32.pagamentos.use_case;

import br.com.fiap.soat1.t32.pagamentos.ports.CheckoutRepository;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.exceptions.ValidationException;
import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;

    public Long realizarCheckout(Checkout checkout) {

        validaCheckout(checkout);

        return this.checkoutRepository.criarPedido(checkout);
    }

    private void validaCheckout(Checkout checkout) {

        if(checkout == null) {
            throw new ValidationException("Informar dados para checkout do pedido.");
        } else if(checkout.getProdutos() == null || checkout.getProdutos().isEmpty()) {
            throw new ValidationException("Produtos selecionados para checkout do pedido não foram informados.");
        }

    }

}
