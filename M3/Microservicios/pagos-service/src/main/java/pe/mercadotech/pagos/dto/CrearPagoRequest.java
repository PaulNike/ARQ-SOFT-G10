package pe.mercadotech.pagos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CrearPagoRequest(
        @NotNull @Positive Long pedidoId,
        @NotNull @Positive BigDecimal monto) {
}
