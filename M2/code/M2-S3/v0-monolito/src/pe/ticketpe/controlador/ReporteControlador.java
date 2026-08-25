package pe.ticketpe.controlador;

import java.util.Locale;
import pe.ticketpe.servicio.ReporteServicio;

public class ReporteControlador {

    private final ReporteServicio reporteServicio = new ReporteServicio();

    public void imprimir(String eventoId) {
        System.out.printf(Locale.US, "Reporte: %d entradas vendidas | recaudado S/ %.2f%n",
                reporteServicio.entradasVendidas(eventoId),
                reporteServicio.recaudado(eventoId));
    }
}
