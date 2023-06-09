package br.com.fiap.soat1.t32.pagamentos.driven;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;
import br.com.fiap.soat1.t32.pagamentos.driven.client.PedidoClient;
import br.com.fiap.soat1.t32.pagamentos.ports.CheckoutPort;
import br.com.fiap.soat1.t32.pagamentos.utils.mappers.CheckoutMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutGateway implements CheckoutPort {

    private final PedidoClient pedidoClient;

    @Override
    public Long criarPedido(Checkout checkout) {
        try{
            final var pedido = this.pedidoClient.criarPedido(CheckoutMapper.toRequest(checkout));
            return pedido.getId();
        } catch(FeignException fe) {
            log.error(fe.contentUTF8());
            throw new IntegrationException("Não foi possível incluir o pedido.");
        }


    }
}
