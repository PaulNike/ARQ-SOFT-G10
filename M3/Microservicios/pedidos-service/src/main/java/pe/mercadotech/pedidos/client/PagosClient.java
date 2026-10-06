package pe.mercadotech.pedidos.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.mercadotech.pedidos.exception.PagosServiceCaidoException;

import java.math.BigDecimal;
import java.time.Duration;

@Component
public class PagosClient {

    private final WebClient webClient;
    private final Duration timeout;

    public PagosClient(
            @Qualifier("pagosWebClient") WebClient webClient,
            @Value("${mercadotech.pagos-service.timeout-ms}") long timeoutMs) {
        this.webClient = webClient;
        this.timeout = Duration.ofMillis(timeoutMs);
    }

    public PagoDTO cobrar(Long pedidoId, BigDecimal monto) {
        try {
            return webClient.post()
                    .uri("/api/pagos")
                    .bodyValue(new CobroRequest(pedidoId, monto))
                    .retrieve()
                    .bodyToMono(PagoDTO.class)
                    .timeout(timeout)
                    .block();
        } catch (RuntimeException exception) {
            throw new PagosServiceCaidoException(exception.getMessage());
        }
    }

    public void reversar(Long pagoId) {
        try {
            webClient.post()
                    .uri("/api/pagos/{id}/reversar", pagoId)
                    .retrieve()
                    .bodyToMono(PagoDTO.class)
                    .timeout(timeout)
                    .block();
        } catch (RuntimeException exception) {
            throw new PagosServiceCaidoException(exception.getMessage());
        }
    }

    private record CobroRequest(Long pedidoId, BigDecimal monto) {
    }
}
