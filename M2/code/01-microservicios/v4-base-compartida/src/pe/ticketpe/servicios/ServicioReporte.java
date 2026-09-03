package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import pe.ticketpe.datos.BaseDatos;

/**
 * Servicio de reporte. Lo mantiene OTRO equipo.
 *
 * Lee directamente las tablas de venta y de catalogo desde la base compartida
 * y las cruza. Es un JOIN: rapido, exacto, en un solo lugar.
 *
 * Y depende del formato interno de una tabla que no es suya.
 */
public class ServicioReporte {

    static final int PUERTO = 8084;
    static final BaseDatos BD = new BaseDatos("datos/ticketpe.db");

    public static void main(String[] args) throws IOException {
        HttpServer servidor = Http.servidor(PUERTO);

        servidor.createContext("/reporte", intercambio -> {
            // Tabla EVENTO: tipo ; id ; nombre ; aforo ; precio
            Map<String, Double> precioPorEvento = new HashMap<>();
            for (String[] f : BD.tabla("EVENTO")) {
                precioPorEvento.put(f[1], Double.parseDouble(f[4]));
            }

            // Tabla ENTRADA: tipo ; id ; eventoId ; clienteId
            // La columna 3 es el evento. Eso lo sabemos porque miramos su tabla.
            List<String[]> entradas = BD.tabla("ENTRADA");
            double recaudado = 0;
            int contadas = 0;
            StringBuilder problemas = new StringBuilder();

            for (String[] e : entradas) {
                String eventoId = e[2];
                Double precio = precioPorEvento.get(eventoId);
                if (precio == null) {
                    problemas.append(" fila-desconocida(").append(eventoId).append(")");
                    continue;
                }
                recaudado += precio;
                contadas++;
            }

            String detalle = problemas.length() == 0 ? "" : ";problemas=" + problemas.toString().trim();
            Http.responder(intercambio, String.format(Locale.US,
                    "entradas=%d;recaudado=%.2f%s", contadas, recaudado, detalle));
        });

        servidor.start();
        System.out.println("[reporte] puerto " + PUERTO + " | lee la base compartida y hace JOIN");
    }
}
