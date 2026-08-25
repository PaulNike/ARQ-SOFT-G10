package pe.ticketpe.servicio;

import pe.ticketpe.modelo.Evento;
import pe.ticketpe.repositorio.EventoRepositorio;

public class PrecioServicio {

    // Copia 2 de 4 de la tasa de IGV en este proyecto.
    private static final double IGV = 0.18;

    private final EventoRepositorio eventoRepositorio = new EventoRepositorio();

    public double subtotal(String eventoId, int cantidad) {
        Evento evento = eventoRepositorio.buscar(eventoId);
        return evento.precioUnitario * cantidad;
    }

    public double igv(double subtotal) {
        return subtotal * IGV;
    }
}
