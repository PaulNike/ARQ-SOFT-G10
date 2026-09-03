package pe.ticketpe.servicios;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/** Utilitario minimo de HTTP. Todo del JDK, igual que en la sesion anterior. */
public final class Http {

    private static final HttpClient CLIENTE = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2)).build();

    private Http() { }

    public static HttpServer servidor(int puerto) throws IOException {
        return HttpServer.create(new InetSocketAddress(puerto), 0);
    }

    public static String llamar(String url) throws IOException, InterruptedException {
        HttpRequest p = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(2)).GET().build();
        return CLIENTE.send(p, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static String param(String query, String clave) {
        if (query == null) return null;
        for (String par : query.split("&")) {
            String[] kv = par.split("=", 2);
            if (kv.length == 2 && kv[0].equals(clave)) return kv[1];
        }
        return null;
    }

    public static void responder(HttpExchange intercambio, String cuerpo) throws IOException {
        byte[] bytes = cuerpo.getBytes(StandardCharsets.UTF_8);
        intercambio.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = intercambio.getResponseBody()) {
            os.write(bytes);
        }
    }
}
