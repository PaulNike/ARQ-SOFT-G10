package pe.mercadotech.pedidos.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pe.mercadotech.pedidos.exception.CatalogoServiceCaidoException;
import pe.mercadotech.pedidos.exception.ProductoNoEncontradoException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class CatalogoClient {

    private final WebClient webClient;

    public CatalogoClient(@Qualifier("catalogoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "catalogo", fallbackMethod = "catalogoNoDisponible")
    public DisponibilidadDTO verificarDisponibilidad(Long productoId) {
        try {
            return webClient.get()
                .uri("/api/catalogo/{id}/disponibilidad", productoId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, r ->
                    Mono.error(new ProductoNoEncontradoException(productoId)))
                .bodyToMono(DisponibilidadDTO.class)
                .timeout(Duration.ofMillis(2000))
                .block();
        } catch (ProductoNoEncontradoException e) {
            throw e;
        } catch (WebClientResponseException e) {
            throw new CatalogoServiceCaidoException(e.getMessage());
        } catch (RuntimeException e) {
            /*
             * Reactor envuelve el TimeoutException porque block() no declara excepciones
             * comprobadas. Por eso Java no permite el catch de TimeoutException que suele
             * verse en pseudocódigo; esta rama es su equivalente compilable.
             */
            throw new CatalogoServiceCaidoException(e.getMessage());
        }
    }

    private DisponibilidadDTO catalogoNoDisponible(Long productoId, Throwable throwable) {
        return new DisponibilidadDTO(false);
    }
}
