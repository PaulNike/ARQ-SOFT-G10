package pe.mercadotech.envios.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearEnvioRequest(
        @NotNull @Positive Long pedidoId,
        @NotNull @Positive Long productoId) {
}
