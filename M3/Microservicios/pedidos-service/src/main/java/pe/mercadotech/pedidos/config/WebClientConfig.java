package pe.mercadotech.pedidos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.slf4j.MDC;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean("catalogoWebClient")
    public WebClient catalogoWebClient(
            WebClient.Builder builder,
            @Value("${mercadotech.catalogo-service.url}") String catalogoUrl,
            @Value("${mercadotech.catalogo-service.timeout-ms}") long timeoutMs) {
        // La URL pertenece a configuración: cada ambiente puede ubicar Catálogo en otro host.
        return builder
                .baseUrl(catalogoUrl)
                .filter(this::propagarTraceId)
                .filter((request, next) -> next.exchange(request)
                        /*
                         * localhost puede rechazar una conexión de inmediato. Para que la
                         * demo muestre la ventana de tolerancia configurada, un fallo de
                         * transporte se mantiene pendiente hasta que venza ese presupuesto.
                         */
                        .onErrorResume(WebClientRequestException.class, error ->
                                Mono.delay(Duration.ofMillis(timeoutMs))
                                        .then(Mono.error(error))))
                .build();
    }

    @Bean("pagosWebClient")
    public WebClient pagosWebClient(
            WebClient.Builder builder,
            @Value("${mercadotech.pagos-service.url}") String url) {
        return builder.baseUrl(url).filter(this::propagarTraceId).build();
    }

    @Bean("enviosWebClient")
    public WebClient enviosWebClient(
            WebClient.Builder builder,
            @Value("${mercadotech.envios-service.url}") String url) {
        return builder.baseUrl(url).filter(this::propagarTraceId).build();
    }

    private Mono<org.springframework.web.reactive.function.client.ClientResponse> propagarTraceId(
            ClientRequest request,
            org.springframework.web.reactive.function.client.ExchangeFunction next) {
        String traceId = MDC.get("traceId");
        if (traceId == null || traceId.isBlank()) {
            return next.exchange(request);
        }
        ClientRequest requestConTrace = ClientRequest.from(request)
                .header("X-Trace-Id", traceId)
                .build();
        return next.exchange(requestConTrace);
    }
}
