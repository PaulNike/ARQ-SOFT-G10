package pe.ticketpe;

import pe.ticketpe.controlador.AccesoControlador;
import pe.ticketpe.controlador.ReporteControlador;
import pe.ticketpe.controlador.VentaControlador;
import pe.ticketpe.modelo.Evento;
import pe.ticketpe.repositorio.EventoRepositorio;

public class Main {
    public static void main(String[] args) {
        Evento evento = new EventoRepositorio().buscar("EV-1");

        System.out.println("=== TicketPe ===");
        System.out.println("Evento: " + evento.nombre + " (aforo " + evento.aforo + ")");

        VentaControlador venta = new VentaControlador();
        venta.comprar("EV-1", "C-001", 2);
        venta.comprar("EV-1", "C-002", 4);

        AccesoControlador acceso = new AccesoControlador();
        acceso.validar("E-1");
        acceso.validar("E-1");

        new ReporteControlador().imprimir("EV-1");
    }
}
