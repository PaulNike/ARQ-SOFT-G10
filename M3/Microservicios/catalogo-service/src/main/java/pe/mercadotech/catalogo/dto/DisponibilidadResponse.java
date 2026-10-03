package pe.mercadotech.catalogo.dto;

/**
 * Contrato mínimo entre servicios: Pedidos solo necesita saber si puede pedir.
 * No exponer nombre ni precio reduce el acoplamiento con el modelo de Catálogo.
 */
public record DisponibilidadResponse(boolean disponible) {
}
