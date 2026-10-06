package pe.mercadotech.pedidos.client;

/**
 * Copia local del contrato HTTP de Catálogo. Compartir su clase Java convertiría
 * un contrato de red en una dependencia de compilación entre microservicios.
 */
public record DisponibilidadDTO(boolean disponible) {
}
