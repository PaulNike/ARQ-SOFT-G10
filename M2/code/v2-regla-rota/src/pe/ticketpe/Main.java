package pe.ticketpe;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.dominio.Inventario;
import pe.ticketpe.infraestructura.InventarioRepositorioMemoria;
import pe.ticketpe.presentacion.CanalTaquilla;
import pe.ticketpe.presentacion.CanalWeb;

/**
 * Mismo escenario que la version v0-sin-capas, linea por linea.
 * Lo unico que cambio es donde vive la regla.
 */
public class Main {

    public static void main(String[] args) {
        InventarioRepositorioMemoria repositorio = new InventarioRepositorioMemoria();
        repositorio.registrarEvento("CONCIERTO-EN", 5);

        VentaService servicio = new VentaService(repositorio);
        CanalWeb web = new CanalWeb(servicio);
        CanalTaquilla taquilla = new CanalTaquilla(repositorio);   // <-- salta la capa de aplicacion

        System.out.println("=== TicketPe :: venta de entradas (v2 regla de dependencia ROTA) ===");
        System.out.println("Aforo del evento CONCIERTO-EN: 5");
        System.out.println();

        web.reservar("CONCIERTO-EN", "ana");
        web.reservar("CONCIERTO-EN", "beto");
        web.reservar("CONCIERTO-EN", "carla");

        web.vender("CONCIERTO-EN", "diana");
        web.vender("CONCIERTO-EN", "elias");
        web.vender("CONCIERTO-EN", "fiorella");

        taquilla.vender("CONCIERTO-EN", "gabriel");
        taquilla.vender("CONCIERTO-EN", "hugo");

        reporte(servicio.consultar("CONCIERTO-EN"));
    }

    static void reporte(Inventario inv) {
        System.out.println();
        System.out.println("--- Cierre del dia ---");
        System.out.println("Aforo               : " + inv.aforo());
        System.out.println("Entradas vendidas   : " + inv.vendidas().size() + " " + inv.vendidas());
        System.out.println("Reservas pendientes : " + inv.reservas().size() + " " + inv.reservas());
        System.out.println("Total comprometido  : " + inv.comprometidas());
        System.out.println();
        if (inv.comprometidas() > inv.aforo()) {
            System.out.println("*** SOBREVENTA ***");
        } else {
            System.out.println("Sin sobreventa. El escenario E1 se cumple.");
        }
    }
}
