package pe.ticketpe.reporte.internal;

import pe.ticketpe.catalogo.CatalogoApi;
import pe.ticketpe.catalogo.EventoDto;
import pe.ticketpe.plataforma.Igv;
import pe.ticketpe.venta.VentaApi;

public class CalculoReporte {

    private final VentaApi venta = new VentaApi();
    private final CatalogoApi catalogo = new CatalogoApi();

    public int entradasVendidas(String eventoId) {
        return venta.contarPorEvento(eventoId);
    }

    public double recaudado(String eventoId) {
        EventoDto evento = catalogo.buscar(eventoId);
        int vendidas = venta.contarPorEvento(eventoId);
        double base = evento.precioUnitario * vendidas;
        return base + Igv.sobre(base);
    }
}
