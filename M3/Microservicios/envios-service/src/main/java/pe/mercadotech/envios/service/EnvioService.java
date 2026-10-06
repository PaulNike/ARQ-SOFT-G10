package pe.mercadotech.envios.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import pe.mercadotech.envios.dto.CrearEnvioRequest;
import pe.mercadotech.envios.dto.EnvioResponse;
import pe.mercadotech.envios.exception.SinStockRealException;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class EnvioService {

    private static final Logger log = LoggerFactory.getLogger(EnvioService.class);
    private final AtomicLong secuencia = new AtomicLong();

    public EnvioResponse generar(CrearEnvioRequest request) {
        if (request.productoId() == 99L) {
            log.warn("[{}] Sin stock real para el producto 99", MDC.get("traceId"));
            throw new SinStockRealException();
        }
        EnvioResponse envio = new EnvioResponse(secuencia.incrementAndGet(), "GENERADO");
        log.info("[{}] Envío {} generado para pedido {}", MDC.get("traceId"),
                envio.envioId(), request.pedidoId());
        return envio;
    }
}
