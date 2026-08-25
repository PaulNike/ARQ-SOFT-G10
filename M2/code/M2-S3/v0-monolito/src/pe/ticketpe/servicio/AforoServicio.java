package pe.ticketpe.servicio;

import pe.ticketpe.modelo.Evento;
import pe.ticketpe.repositorio.EventoRepositorio;

public class AforoServicio {

    private final EventoRepositorio eventoRepositorio = new EventoRepositorio();

    public boolean hayCupo(String eventoId, int cantidad) {
        Evento evento = eventoRepositorio.buscar(eventoId);
        return evento.vendidas + cantidad <= evento.aforo;
    }

    public boolean puedeRegistrarUnaMas(String eventoId) {
        Evento evento = eventoRepositorio.buscar(eventoId);
        return evento.vendidas <= evento.aforo;
    }
}
