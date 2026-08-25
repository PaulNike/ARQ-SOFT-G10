package pe.ticketpe;

import java.util.Locale;
import pe.ticketpe.acceso.AccesoApi;
import pe.ticketpe.catalogo.CatalogoApi;
import pe.ticketpe.catalogo.EventoDto;
import pe.ticketpe.plataforma.Igv;
import pe.ticketpe.reporte.ReporteApi;
import pe.ticketpe.venta.Comprobante;
import pe.ticketpe.venta.VentaApi;

/**
 * Punto de entrada. Arma los modulos y presenta el resultado.
 * Los modulos devuelven datos: la impresion vive aqui.
 */
public class Main {
    public static void main(String[] args) {
        CatalogoApi catalogo = new CatalogoApi();
        VentaApi venta = new VentaApi();
        AccesoApi acceso = new AccesoApi();
        ReporteApi reporte = new ReporteApi();

        EventoDto evento = catalogo.buscar("EV-1");

        System.out.println("=== TicketPe ===");
        System.out.println("Evento: " + evento.nombre + " (aforo " + evento.aforo + ")");

        imprimir(venta.vender("EV-1", "C-001", 2));
        imprimir(venta.vender("EV-1", "C-002", 4));

        System.out.println("Acceso E-1: " + acceso.validar("E-1"));
        System.out.println("Acceso E-1: " + acceso.validar("E-1"));

        System.out.printf(Locale.US, "Reporte: %d entradas vendidas | recaudado S/ %.2f%n",
                reporte.entradasVendidas("EV-1"), reporte.recaudado("EV-1"));
    }

    private static void imprimir(Comprobante c) {
        System.out.printf(Locale.US, "Venta %s: %d entradas%n", c.clienteId, c.cantidad);
        System.out.printf(Locale.US, "  Subtotal: S/ %.2f%n", c.subtotal);
        System.out.printf(Locale.US, "  IGV %d%%:  S/ %.2f%n", Igv.porcentaje(), c.igv);
        System.out.printf(Locale.US, "  Total:    S/ %.2f%n", c.total);
    }
}
