import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TicketPe - version SIN CAPAS.
 *
 * Todo vive en una sola clase: el almacenamiento, las reglas del negocio
 * y la salida por pantalla.
 *
 * Funciona. Vende entradas. Nadie diria que esta "roto" mirandolo por encima.
 *
 * Pero la regla "no vender mas que el aforo" esta escrita DOS VECES:
 * una en venderPorWeb() y otra en venderEnTaquilla().
 * Cuando el negocio pidio contar tambien las reservas pendientes,
 * alguien actualizo la copia de la web y se olvido de la de taquilla.
 *
 * Resultado: sobreventa.
 */
public class SistemaVentas {

    // ---------- "Base de datos" (almacenamiento) ----------
    static Map<String, Integer> aforoPorEvento = new HashMap<>();
    static List<String> ventas = new ArrayList<>();
    static List<String> reservasPendientes = new ArrayList<>();

    public static void main(String[] args) {
        aforoPorEvento.put("CONCIERTO-EN", 5);

        System.out.println("=== TicketPe :: venta de entradas (v0 sin capas) ===");
        System.out.println("Aforo del evento CONCIERTO-EN: " + aforoPorEvento.get("CONCIERTO-EN"));
        System.out.println();

        // Tres personas reservan por la web y aun no pagan
        reservarPorWeb("CONCIERTO-EN", "ana");
        reservarPorWeb("CONCIERTO-EN", "beto");
        reservarPorWeb("CONCIERTO-EN", "carla");

        // Dos compras directas por la web
        venderPorWeb("CONCIERTO-EN", "diana");
        venderPorWeb("CONCIERTO-EN", "elias");

        // La web ya no deja vender mas: 2 vendidas + 3 reservadas = 5 = aforo
        venderPorWeb("CONCIERTO-EN", "fiorella");

        // Pero la taquilla usa su propia copia de la regla, desactualizada
        venderEnTaquilla("CONCIERTO-EN", "gabriel");
        venderEnTaquilla("CONCIERTO-EN", "hugo");

        reporte();
    }

    // ---------- Canal WEB ----------
    static void reservarPorWeb(String evento, String cliente) {
        int aforo = aforoPorEvento.get(evento);
        int ocupadas = ventas.size() + reservasPendientes.size();
        if (ocupadas >= aforo) {
            System.out.println("[WEB]      reserva RECHAZADA para " + cliente + " (no hay cupo)");
            return;
        }
        reservasPendientes.add(cliente);
        System.out.println("[WEB]      reserva OK para " + cliente);
    }

    static void venderPorWeb(String evento, String cliente) {
        int aforo = aforoPorEvento.get(evento);
        // REGLA DE AFORO - copia 1 (actualizada: cuenta reservas pendientes)
        int ocupadas = ventas.size() + reservasPendientes.size();
        if (ocupadas >= aforo) {
            System.out.println("[WEB]      venta RECHAZADA para " + cliente + " (no hay cupo)");
            return;
        }
        ventas.add(cliente);
        System.out.println("[WEB]      venta OK para " + cliente);
    }

    // ---------- Canal TAQUILLA ----------
    static void venderEnTaquilla(String evento, String cliente) {
        int aforo = aforoPorEvento.get(evento);
        // REGLA DE AFORO - copia 2 (DESACTUALIZADA: ignora las reservas pendientes)
        int ocupadas = ventas.size();
        if (ocupadas >= aforo) {
            System.out.println("[TAQUILLA] venta RECHAZADA para " + cliente + " (no hay cupo)");
            return;
        }
        ventas.add(cliente);
        System.out.println("[TAQUILLA] venta OK para " + cliente);
    }

    // ---------- Salida por pantalla ----------
    static void reporte() {
        int aforo = aforoPorEvento.get("CONCIERTO-EN");
        int comprometidas = ventas.size() + reservasPendientes.size();

        System.out.println();
        System.out.println("--- Cierre del dia ---");
        System.out.println("Aforo               : " + aforo);
        System.out.println("Entradas vendidas   : " + ventas.size() + " " + ventas);
        System.out.println("Reservas pendientes : " + reservasPendientes.size() + " " + reservasPendientes);
        System.out.println("Total comprometido  : " + comprometidas);

        if (comprometidas > aforo) {
            System.out.println();
            System.out.println("*** SOBREVENTA: " + (comprometidas - aforo) + " localidad(es) de mas ***");
            System.out.println("*** El escenario E1 (sobreventa cero) NO se cumple ***");
        } else {
            System.out.println("Sin sobreventa.");
        }
    }
}
