package pe.mercadotech.pedidos.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import pe.mercadotech.pedidos.client.CatalogoClient;
import pe.mercadotech.pedidos.client.DisponibilidadDTO;
import pe.mercadotech.pedidos.client.EnvioDTO;
import pe.mercadotech.pedidos.client.EnviosClient;
import pe.mercadotech.pedidos.client.PagoDTO;
import pe.mercadotech.pedidos.client.PagosClient;
import pe.mercadotech.pedidos.event.EventoPublicador;
import pe.mercadotech.pedidos.exception.SinStockRealException;
import pe.mercadotech.pedidos.model.Pedido;
import pe.mercadotech.pedidos.repository.PedidoRepository;

import java.math.BigDecimal;

@Service
public class ConfirmarPedidoSaga {

    private static final Logger log = LoggerFactory.getLogger(ConfirmarPedidoSaga.class);

    private final CatalogoClient catalogoClient;
    private final PagosClient pagosClient;
    private final EnviosClient enviosClient;
    private final PedidoRepository pedidoRepo;
    private final EventoPublicador eventoPublicador;

    public ConfirmarPedidoSaga(
            CatalogoClient catalogoClient,
            PagosClient pagosClient,
            EnviosClient enviosClient,
            PedidoRepository pedidoRepo,
            EventoPublicador eventoPublicador) {
        this.catalogoClient = catalogoClient;
        this.pagosClient = pagosClient;
        this.enviosClient = enviosClient;
        this.pedidoRepo = pedidoRepo;
        this.eventoPublicador = eventoPublicador;
    }

    public Pedido ejecutar(Long productoId, int cantidad, BigDecimal monto) {
        DisponibilidadDTO disponibilidad = catalogoClient.verificarDisponibilidad(productoId);
        if (!disponibilidad.disponible()) {
            throw new IllegalStateException("Producto sin stock o catálogo no disponible: " + productoId);
        }

        PagoDTO pago = pagosClient.cobrar(productoId, monto);
        log.info("[{}] Pago {} cobrado para producto {}", MDC.get("traceId"), pago.pagoId(), productoId);
        try {
            EnvioDTO envio = enviosClient.generarGuia(productoId);

            // EJERCICIO DE CIERRE: correoClient.notificarEnvio(envio).
            // Si falla, no se compensa pago ni envío: se reintenta o se encola.

            Pedido pedido = pedidoRepo.save(new Pedido(productoId, cantidad, pago.pagoId(),
                    envio.envioId(), "CONFIRMADO"));
            eventoPublicador.publicarPedidoConfirmado(pedido);
            log.info("[{}] Pedido {} confirmado", MDC.get("traceId"), pedido.getId());
            return pedido;
        } catch (SinStockRealException exception) {
            log.warn("[{}] Envío rechazado por falta de stock real; compensando pago {}",
                    MDC.get("traceId"), pago.pagoId());
            pagosClient.reversar(pago.pagoId());
            log.info("[{}] Pago {} reversado", MDC.get("traceId"), pago.pagoId());
            return pedidoRepo.save(new Pedido(productoId, cantidad, pago.pagoId(),
                    null, "CANCELADO_SIN_STOCK"));
        }
    }
}
