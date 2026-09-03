package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Servicio de control de acceso: valida el QR en la puerta del evento.
 *
 * Necesita saber si la entrada existe, y eso lo sabe venta.
 * Pero guarda POR SU CUENTA cuales ya se usaron: ese dato es suyo.
 */
public class ServicioAcceso {

    static final int PUERTO = 8083;
    static final String VENTA = "http://localhost:8081";

    static final Set<String> YA_USADAS = new HashSet<>();

    public static void main(String[] args) throws IOException {
        // Que hacer cuando venta no responde. NO hay respuesta universal:
        //   tolerante=true  -> dejamos pasar y validamos con lo que tenemos (degradar)
        //   tolerante=false -> no dejamos pasar a nadie (caer)
        // Alguien tiene que elegir, y la eleccion es de negocio, no tecnica.
        boolean tolerante = !"false".equals(System.getProperty("tolerante", "true"));
        HttpServer servidor = Http.servidor(PUERTO);

        servidor.createContext("/acceso", intercambio -> {
            String entradaId = Http.param(intercambio.getRequestURI().getQuery(), "entrada");

            if (YA_USADAS.contains(entradaId)) {
                Http.responder(intercambio, "resultado=RECHAZADO (ya usada)");
                return;
            }

            boolean existe;
            try {
                existe = "true".equals(pe.ticketpe.contrato.Mensaje
                        .leer(Http.llamar(VENTA + "/existe?entrada=" + entradaId)).get("existe"));
            } catch (Exception e) {
                // Venta no responde. La decision es de este servicio, y hay que tomarla.
                if (tolerante) {
                    YA_USADAS.add(entradaId);
                    Http.responder(intercambio,
                            "resultado=DEGRADADO;detalle=dejo pasar, valido con lo que tengo");
                } else {
                    Http.responder(intercambio,
                            "resultado=RECHAZADO;detalle=no puedo verificar, no dejo pasar a nadie");
                }
                return;
            }

            if (!existe) {
                Http.responder(intercambio, "resultado=RECHAZADO (no existe)");
                return;
            }
            YA_USADAS.add(entradaId);
            Http.responder(intercambio, "resultado=VALIDO");
        });

        servidor.start();
        System.out.println("[acceso]   puerto " + PUERTO + " | si venta cae: "
                + (tolerante ? "DEGRADAR (dejo pasar)" : "CAER (no dejo pasar a nadie)"));
    }
}
