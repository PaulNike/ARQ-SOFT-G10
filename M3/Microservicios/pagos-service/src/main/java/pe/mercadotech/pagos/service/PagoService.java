package pe.mercadotech.pagos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mercadotech.pagos.dto.CrearPagoRequest;
import pe.mercadotech.pagos.exception.PagoNoEncontradoException;
import pe.mercadotech.pagos.model.Pago;
import pe.mercadotech.pagos.repository.PagoRepository;

@Service
public class PagoService {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);
    private final PagoRepository repositorio;

    public PagoService(PagoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public Pago cobrar(CrearPagoRequest request) {
        Pago pago = repositorio.save(new Pago(request.pedidoId(), request.monto()));
        log.info("[{}] Pago {} cobrado para pedido {}", MDC.get("traceId"),
                pago.getId(), pago.getPedidoId());
        return pago;
    }

    @Transactional
    public Pago reversar(Long id) {
        Pago pago = repositorio.findById(id)
                .orElseThrow(() -> new PagoNoEncontradoException(id));
        pago.reversar();
        log.info("[{}] Pago {} reversado", MDC.get("traceId"), pago.getId());
        return pago;
    }
}
