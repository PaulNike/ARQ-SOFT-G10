package pe.mercadotech.pedidos.dto;

import pe.mercadotech.pedidos.model.Pedido;

import java.time.LocalDateTime;

public record PedidoResponse(
        Long id,
        Long productoId,
        int cantidad,
        boolean stockVerificado,
        Long pagoId,
        Long envioId,
        String estado,
        LocalDateTime fecha
) {
    public static PedidoResponse desde(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getProductoId(),
                pedido.getCantidad(),
                pedido.isStockVerificado(),
                pedido.getPagoId(),
                pedido.getEnvioId(),
                pedido.getEstado(),
                pedido.getFecha());
    }
}
