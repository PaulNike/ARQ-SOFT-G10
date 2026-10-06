package pe.mercadotech.pedidos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CrearPedidoRequest(
        @NotNull @Positive Long productoId,
        @Positive int cantidad,
        @NotNull @Positive BigDecimal monto
) {
}
