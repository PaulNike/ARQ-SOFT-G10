package pe.ticketpe.reporte;

import pe.ticketpe.reporte.internal.CalculoReporte;

/** Puerta de entrada al modulo reporte. */
public class ReporteApi {

    private final CalculoReporte calculo = new CalculoReporte();

    public int entradasVendidas(String eventoId) {
        return calculo.entradasVendidas(eventoId);
    }

    public double recaudado(String eventoId) {
        return calculo.recaudado(eventoId);
    }
}
