package br.com.fiap.soat1.t32.pagamentos.repositories;

import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.pagamentos.adapter.CheckoutAdapter;
import br.com.fiap.soat1.t32.pagamentos.domain.Checkout;
import br.com.fiap.soat1.t32.pagamentos.ports.CheckoutRepository;
import br.com.fiap.soat1.t32.pagamentos.repositories.entities.PedidoClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutRepositoryImpl implements CheckoutRepository {

    private final PedidoClient pedidoClient;

    @Override
    public Long criarPedido(Checkout checkout) {
        try{
            final var pedido = this.pedidoClient.criarPedido(CheckoutAdapter.toRequest(checkout));
            return pedido.getId();
        } catch(FeignException fe) {
            log.error(fe.contentUTF8());
            throw new IntegrationException("Não foi possível incluir o pedido.");
        }
    }
}
