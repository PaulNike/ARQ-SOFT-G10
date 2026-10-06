package pe.mercadotech.pedidos.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.mercadotech.pedidos.exception.EnviosServiceCaidoException;
import pe.mercadotech.pedidos.exception.SinStockRealException;

import java.time.Duration;

@Component
public class EnviosClient {

    private final WebClient webClient;
    private final Duration timeout;

    public EnviosClient(
            @Qualifier("enviosWebClient") WebClient webClient,
            @Value("${mercadotech.envios-service.timeout-ms}") long timeoutMs) {
        this.webClient = webClient;
        this.timeout = Duration.ofMillis(timeoutMs);
    }

    public EnvioDTO generarGuia(Long productoId) {
        try {
            return webClient.post()
                    .uri("/api/envios")
                    .bodyValue(new GenerarEnvioRequest(productoId, productoId))
                    .retrieve()
                    .onStatus(status -> status.value() == HttpStatus.UNPROCESSABLE_ENTITY.value(),
                            response -> response.bodyToMono(String.class)
                                    .map(SinStockRealException::new))
                    .bodyToMono(EnvioDTO.class)
                    .timeout(timeout)
                    .block();
        } catch (SinStockRealException exception) {
            throw exception;
        } catch (RuntimeException exception) {
            throw new EnviosServiceCaidoException(exception.getMessage());
        }
    }

    private record GenerarEnvioRequest(Long pedidoId, Long productoId) {
    }
}
