package pe.ticketpe.servicio;

import pe.ticketpe.modelo.Entrada;
import pe.ticketpe.modelo.Evento;
import pe.ticketpe.repositorio.EntradaRepositorio;
import pe.ticketpe.repositorio.EventoRepositorio;

public class ReporteServicio {

    // Copia 3 de 4 de la tasa de IGV en este proyecto.
    private static final double TASA_IGV = 0.18;

    private final EntradaRepositorio entradaRepositorio = new EntradaRepositorio();
    private final EventoRepositorio eventoRepositorio = new EventoRepositorio();

    public int entradasVendidas(String eventoId) {
        return entradaRepositorio.contarPorEvento(eventoId);
    }

    public double recaudado(String eventoId) {
        Evento evento = eventoRepositorio.buscar(eventoId);
        double total = 0;
        for (Entrada e : entradaRepositorio.todas()) {
            if (e.eventoId.equals(eventoId)) {
                double base = evento.precioUnitario;
                total += base + base * TASA_IGV;
            }
        }
        return total;
    }
}
