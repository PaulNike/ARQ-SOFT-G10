package pe.mercadotech.catalogo.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record ActualizarStockRequest(@PositiveOrZero int stock) {
}
