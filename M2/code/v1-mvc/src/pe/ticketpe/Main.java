package pe.ticketpe;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.infraestructura.InventarioRepositorioMemoria;
import pe.ticketpe.presentacion.controlador.DisponibilidadControlador;
import pe.ticketpe.presentacion.modelo.DisponibilidadModelo;
import pe.ticketpe.presentacion.vista.VistaMovil;
import pe.ticketpe.presentacion.vista.VistaWeb;

public class Main {
    public static void main(String[] args) {
        InventarioRepositorioMemoria repositorio = new InventarioRepositorioMemoria();
        repositorio.registrarEvento("CONCIERTO-EN", 10);
        VentaService servicio = new VentaService(repositorio);

        DisponibilidadControlador controlador = new DisponibilidadControlador(servicio);
        VistaWeb web = new VistaWeb();
        VistaMovil movil = new VistaMovil();

        System.out.println("=== TicketPe :: pantalla de disponibilidad (v1: MVC) ===");
        System.out.println();

        System.out.println("-- Aforo 10, nadie ha comprado --");
        DisponibilidadModelo m1 = controlador.consultar("CONCIERTO-EN");
        web.pintar(m1);
        movil.pintar(m1);

        for (int i = 1; i <= 6; i++) servicio.vender("CONCIERTO-EN", "cliente" + i);
        System.out.println();
        System.out.println("-- Se vendieron 6 de 10: quedan 4 (40%) --");
        DisponibilidadModelo m2 = controlador.consultar("CONCIERTO-EN");
        web.pintar(m2);
        movil.pintar(m2);

        System.out.println();
        System.out.println("Distinto formato, misma respuesta. La decision se toma una sola vez.");
        System.out.println("Y notese: MVC vive DENTRO de la capa de presentacion de Layered.");
    }
}
