package pe.ticketpe.venta;

/** Resultado de una venta. El modulo devuelve datos; no imprime nada. */
public class Comprobante {
    public final String clienteId;
    public final int cantidad;
    public final double subtotal;
    public final double igv;
    public final double total;

    public Comprobante(String clienteId, int cantidad, double subtotal, double igv, double total) {
        this.clienteId = clienteId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }
}
