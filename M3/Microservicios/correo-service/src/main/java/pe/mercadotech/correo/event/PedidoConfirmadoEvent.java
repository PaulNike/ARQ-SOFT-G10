package pe.mercadotech.correo.event;

import java.io.Serializable;

/**
 * Copia local del contrato del evento. Correo no depende del código fuente de
 * Pedidos; ambos solo acuerdan la forma del mensaje que viaja por RabbitMQ.
 */
public class PedidoConfirmadoEvent implements Serializable {

    private Long pedidoId;
    private Long productoId;
    private int cantidad;

    public PedidoConfirmadoEvent() {
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
