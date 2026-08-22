package pe.ticketpe;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.infraestructura.InventarioRepositorioMemoria;
import pe.ticketpe.presentacion.PantallaMovil;
import pe.ticketpe.presentacion.PantallaWeb;

public class Main {
    public static void main(String[] args) {
        InventarioRepositorioMemoria repositorio = new InventarioRepositorioMemoria();
        repositorio.registrarEvento("CONCIERTO-EN", 10);
        VentaService servicio = new VentaService(repositorio);

        PantallaWeb web = new PantallaWeb(servicio);
        PantallaMovil movil = new PantallaMovil(servicio);

        System.out.println("=== TicketPe :: pantalla de disponibilidad (v0: la vista decide) ===");
        System.out.println();

        System.out.println("-- Aforo 10, nadie ha comprado --");
        web.mostrar("CONCIERTO-EN");
        movil.mostrar("CONCIERTO-EN");

        for (int i = 1; i <= 6; i++) servicio.vender("CONCIERTO-EN", "cliente" + i);
        System.out.println();
        System.out.println("-- Se vendieron 6 de 10: quedan 4 (40%) --");
        web.mostrar("CONCIERTO-EN");
        movil.mostrar("CONCIERTO-EN");

        System.out.println();
        System.out.println("Dos pantallas del mismo producto, el mismo dato, distinta respuesta.");
        System.out.println("La decision estaba escrita dos veces.");
    }
}
