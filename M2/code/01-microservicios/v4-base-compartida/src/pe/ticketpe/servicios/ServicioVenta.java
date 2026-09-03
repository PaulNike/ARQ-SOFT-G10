package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.List;
import pe.ticketpe.datos.BaseDatos;

/**
 * Servicio de venta. Escribe filas ENTRADA en la base compartida.
 *
 * Se arranca con -Desquema=2 para simular que el equipo de venta
 * agrega una columna a SU tabla. Observar quien se entera.
 */
public class ServicioVenta {

    static final int PUERTO = 8081;
    static final BaseDatos BD = new BaseDatos("datos/ticketpe.db");

    static int correlativo = 0;

    public static void main(String[] args) throws IOException {
        String esquema = System.getProperty("esquema", "1");
        HttpServer servidor = Http.servidor(PUERTO);

        servidor.createContext("/venta", intercambio -> {
            String q = intercambio.getRequestURI().getQuery();
            String eventoId = Http.param(q, "evento");
            String clienteId = Http.param(q, "cliente");
            int cantidad = Integer.parseInt(Http.param(q, "cantidad"));

            for (int i = 0; i < cantidad; i++) {
                correlativo++;
                if ("2".equals(esquema)) {
                    // Esquema 2: el equipo de venta agrega la fecha de compra a SU tabla.
                    BD.insertar("ENTRADA;E-" + correlativo + ";2026-05-14;"
                            + eventoId + ";" + clienteId);
                } else {
                    BD.insertar("ENTRADA;E-" + correlativo + ";" + eventoId + ";" + clienteId);
                }
            }
            Http.responder(intercambio, "estado=OK;cliente=" + clienteId + ";cantidad=" + cantidad);
        });

        servidor.createContext("/entradas", intercambio -> {
            List<String[]> filas = BD.tabla("ENTRADA");
            Http.responder(intercambio, "cantidad=" + filas.size());
        });

        servidor.start();
        System.out.println("[venta]   puerto " + PUERTO + " | esquema de su tabla: v" + esquema);
    }
}
