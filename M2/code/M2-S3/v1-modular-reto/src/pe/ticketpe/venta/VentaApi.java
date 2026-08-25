package pe.ticketpe.venta;

import pe.ticketpe.venta.internal.EntradaAlmacen;
import pe.ticketpe.venta.internal.MotorVenta;

/** Puerta de entrada al modulo venta. */
public class VentaApi {

    private final MotorVenta motor = new MotorVenta();
    private final EntradaAlmacen almacen = new EntradaAlmacen();

    public Comprobante vender(String eventoId, String clienteId, int cantidad) {
        return motor.vender(eventoId, clienteId, cantidad);
    }

    public boolean existeEntrada(String entradaId) {
        return almacen.existe(entradaId);
    }

    public int contarPorEvento(String eventoId) {
        return almacen.contarPorEvento(eventoId);
    }
}
