package pe.ticketpe.servicio;

import pe.ticketpe.modelo.Entrada;
import pe.ticketpe.modelo.Evento;
import pe.ticketpe.repositorio.EntradaRepositorio;
import pe.ticketpe.repositorio.EventoRepositorio;

public class VentaServicio {

    private final EventoRepositorio eventoRepositorio = new EventoRepositorio();
    private final EntradaRepositorio entradaRepositorio = new EntradaRepositorio();
    private final AforoServicio aforoServicio = new AforoServicio();

    private static int correlativo = 0;

    public void vender(String eventoId, String clienteId, int cantidad) {
        if (!aforoServicio.hayCupo(eventoId, cantidad)) {
            throw new IllegalStateException("Sin cupo");
        }
        Evento evento = eventoRepositorio.buscar(eventoId);
        for (int i = 0; i < cantidad; i++) {
            correlativo++;
            entradaRepositorio.guardar(new Entrada("E-" + correlativo, eventoId, clienteId));
            evento.vendidas++;
        }
        eventoRepositorio.guardar(evento);
    }
}
