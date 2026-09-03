package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import pe.ticketpe.datos.BaseDatosPropia;

/** Servicio de catalogo. Su base es datos/catalogo.db y nadie mas la abre. */
public class ServicioCatalogo {

    static final int PUERTO = 8085;
    static final BaseDatosPropia BD = new BaseDatosPropia("datos/catalogo.db",
            "EVENTO;EV-1;Concierto Estadio Nacional;40000;180.00");

    public static void main(String[] args) throws IOException {
        HttpServer servidor = Http.servidor(PUERTO);

        servidor.createContext("/evento", intercambio -> {
            String id = Http.param(intercambio.getRequestURI().getQuery(), "id");
            for (String[] f : BD.tabla("EVENTO")) {
                if (f[1].equals(id)) {
                    Http.responder(intercambio,
                            "id=" + f[1] + ";nombre=" + f[2] + ";precio=" + f[4]);
                    return;
                }
            }
            Http.responder(intercambio, "error=no encontrado");
        });

        servidor.start();
        System.out.println("[catalogo] puerto " + PUERTO + " | base propia: datos/catalogo.db");
    }
}
