package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.Locale;
import pe.ticketpe.contrato.Mensaje;

/**
 * Servicio de reporte. Lo mantiene OTRO equipo.
 *
 * Ya no puede hacer un JOIN: no tiene acceso a la base de venta
 * ni a la de catalogo. Tiene que pedirles los datos y cruzarlos aqui.
 *
 * Dos llamadas de red donde antes habia una consulta.
 */
public class ServicioReporte {

    static final int PUERTO = 8084;
    static final String VENTA = "http://localhost:8081";
    static final String CATALOGO = "http://localhost:8085";

    public static void main(String[] args) throws IOException {
        HttpServer servidor = Http.servidor(PUERTO);

        servidor.createContext("/reporte", intercambio -> {
            String eventoId = "EV-1";

            Mensaje entradas;
            try {
                entradas = Mensaje.leer(Http.llamar(VENTA + "/entradas?evento=" + eventoId));
            } catch (Exception e) {
                Http.responder(intercambio, "estado=PARCIAL;detalle=venta no responde");
                return;
            }

            Mensaje evento;
            try {
                evento = Mensaje.leer(Http.llamar(CATALOGO + "/evento?id=" + eventoId));
            } catch (Exception e) {
                Http.responder(intercambio, "estado=PARCIAL;entradas=" + entradas.get("cantidad")
                        + ";detalle=catalogo no responde, no puedo calcular el monto");
                return;
            }

            // El cruce que antes hacia la base de datos, ahora lo hacemos aqui.
            int cantidad = Integer.parseInt(entradas.get("cantidad"));
            double precio = Double.parseDouble(evento.get("precio"));

            Http.responder(intercambio, String.format(Locale.US,
                    "entradas=%d;recaudado=%.2f", cantidad, cantidad * precio));
        });

        servidor.start();
        System.out.println("[reporte]  puerto " + PUERTO + " | sin base propia: pregunta a venta y a catalogo");
    }
}
