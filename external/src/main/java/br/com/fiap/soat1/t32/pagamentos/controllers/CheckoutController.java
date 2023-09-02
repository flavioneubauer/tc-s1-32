package br.com.fiap.soat1.t32.pagamentos.controllers;

import br.com.fiap.soat1.t32.pagamentos.adapters.CheckoutAdapter;
import br.com.fiap.soat1.t32.pagamentos.apis.vo.request.CheckoutVo;
import br.com.fiap.soat1.t32.pagamentos.apis.vo.response.CheckoutResponse;
import br.com.fiap.soat1.t32.pagamentos.use_cases.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutResponse realizarCheckout(CheckoutVo checkoutVo) {
        return CheckoutAdapter.toResponse(checkoutService.realizarCheckout(CheckoutAdapter.toDomain(checkoutVo)));
    }

}
