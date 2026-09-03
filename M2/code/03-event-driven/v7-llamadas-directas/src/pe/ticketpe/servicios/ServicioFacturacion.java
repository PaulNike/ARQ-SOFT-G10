package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

/** Emite el comprobante. Venta lo llama directamente por su URL. */
public class ServicioFacturacion {

    static final int PUERTO = 8086;

    public static void main(String[] args) throws IOException {
        HttpServer servidor = Http.servidor(PUERTO);
        servidor.createContext("/facturar", intercambio -> {
            String q = intercambio.getRequestURI().getQuery();
            System.out.println("   [facturacion] comprobante emitido para "
                    + Http.param(q, "cliente") + " (" + Http.param(q, "cantidad") + " entradas)");
            Http.responder(intercambio, "estado=OK");
        });
        servidor.start();
        System.out.println("[facturacion] puerto " + PUERTO);
    }
}
