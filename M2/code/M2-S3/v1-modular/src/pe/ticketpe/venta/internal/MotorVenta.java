package pe.ticketpe.venta.internal;

import pe.ticketpe.catalogo.CatalogoApi;
import pe.ticketpe.catalogo.EventoDto;
import pe.ticketpe.plataforma.Igv;
import pe.ticketpe.venta.Comprobante;

public class MotorVenta {

    private final CatalogoApi catalogo = new CatalogoApi();
    private final EntradaAlmacen almacen = new EntradaAlmacen();

    public Comprobante vender(String eventoId, String clienteId, int cantidad) {
        if (!catalogo.hayCupo(eventoId, cantidad)) {
            throw new IllegalStateException("Sin cupo");
        }
        EventoDto evento = catalogo.buscar(eventoId);

        for (int i = 0; i < cantidad; i++) {
            almacen.emitir(eventoId, clienteId);
        }
        catalogo.registrarVendidas(eventoId, cantidad);

        double subtotal = evento.precioUnitario * cantidad;
        double igv = Igv.sobre(subtotal);
        return new Comprobante(clienteId, cantidad, subtotal, igv, subtotal + igv);
    }
}
