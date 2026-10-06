package pe.mercadotech.pedidos.event;

import java.io.Serializable;

public class PedidoConfirmadoEvent implements Serializable {

    private Long pedidoId;
    private Long productoId;
    private int cantidad;

    public PedidoConfirmadoEvent() {
        // Necesario para reconstruir el mensaje JSON en el consumidor.
    }

    public PedidoConfirmadoEvent(Long pedidoId, Long productoId, int cantidad) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
