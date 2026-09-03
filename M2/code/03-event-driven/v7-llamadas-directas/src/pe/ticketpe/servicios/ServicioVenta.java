package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import pe.ticketpe.datos.BaseDatosPropia;

/**
 * Servicio de venta. Su base es datos/venta.db y nadie mas la abre.
 *
 * Se arranca con -Desquema=2 para cambiar el formato de SU tabla.
 * Observar si alguien mas se entera.
 */
public class ServicioVenta {

    static final int PUERTO = 8081;
    static final BaseDatosPropia BD = new BaseDatosPropia("datos/venta.db", null);

    static int correlativo = 0;

    /** Cada consumidor nuevo agrega una linea aqui. Y una URL. Y un formato. */
    static void notificar(String url, String quien) {
        try {
            Http.llamar(url);
        } catch (Exception e) {
            System.out.println("[venta]    no pude avisar a " + quien + ": " + e.getClass().getSimpleName());
        }
    }

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
                    BD.insertar("ENTRADA;E-" + correlativo + ";2026-05-14;"
                            + eventoId + ";" + clienteId);
                } else {
                    BD.insertar("ENTRADA;E-" + correlativo + ";" + eventoId + ";" + clienteId);
                }
            }
            // Despues de vender hay que avisarle a los demas.
            // Venta tiene que conocer a cada uno, saber su URL y su formato.
            notificar("http://localhost:8086/facturar?cliente=" + clienteId + "&cantidad=" + cantidad, "facturacion");
            notificar("http://localhost:8087/sumar-puntos?cliente=" + clienteId + "&cantidad=" + cantidad, "fidelizacion");

            Http.responder(intercambio, "estado=OK;cliente=" + clienteId + ";cantidad=" + cantidad);
        });

        /**
         * La API publica. Es lo unico que los demas pueden usar.
         * Por dentro el formato puede cambiar; esta respuesta no.
         */
        servidor.createContext("/entradas", intercambio -> {
            String eventoId = Http.param(intercambio.getRequestURI().getQuery(), "evento");
            String esquemaActual = System.getProperty("esquema", "1");
            int columnaEvento = "2".equals(esquemaActual) ? 3 : 2;

            int n = 0;
            for (String[] f : BD.tabla("ENTRADA")) {
                if (f[columnaEvento].equals(eventoId)) n++;
            }
            Http.responder(intercambio, "evento=" + eventoId + ";cantidad=" + n);
        });

        servidor.createContext("/existe", intercambio -> {
            String id = Http.param(intercambio.getRequestURI().getQuery(), "entrada");
            boolean hay = false;
            for (String[] f : BD.tabla("ENTRADA")) {
                if (f[1].equals(id)) hay = true;
            }
            Http.responder(intercambio, "existe=" + hay);
        });

        servidor.start();
        System.out.println("[venta]    puerto " + PUERTO + " | base propia: datos/venta.db | esquema v" + esquema);
    }
}
