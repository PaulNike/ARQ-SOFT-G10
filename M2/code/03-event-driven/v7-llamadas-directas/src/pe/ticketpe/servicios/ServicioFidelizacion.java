package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

/** Suma puntos de fidelizacion. Venta tambien lo llama directamente. */
public class ServicioFidelizacion {

    static final int PUERTO = 8087;

    public static void main(String[] args) throws IOException {
        HttpServer servidor = Http.servidor(PUERTO);
        servidor.createContext("/sumar-puntos", intercambio -> {
            String q = intercambio.getRequestURI().getQuery();
            int puntos = Integer.parseInt(Http.param(q, "cantidad")) * 10;
            System.out.println("   [fidelizacion] " + puntos + " puntos para " + Http.param(q, "cliente"));
            Http.responder(intercambio, "estado=OK");
        });
        servidor.start();
        System.out.println("[fidelizacion] puerto " + PUERTO);
    }
}
