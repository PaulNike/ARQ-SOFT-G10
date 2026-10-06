package pe.mercadotech.pagos.dto;

import pe.mercadotech.pagos.model.Pago;

public record PagoResponse(Long pagoId, String estado) {
    public static PagoResponse desde(Pago pago) {
        return new PagoResponse(pago.getId(), pago.getEstado().name());
    }
}
