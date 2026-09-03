package pe.ticketpe.bus;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * El bus de eventos. Cuarenta lineas, sin Kafka ni RabbitMQ.
 *
 * Es un archivo de texto donde los productores agregan lineas
 * y los consumidores leen desde donde se quedaron.
 *
 * Lo importante no es la implementacion, es la forma:
 *   - quien publica NO sabe quien lee
 *   - quien lee NO le avisa a quien publica
 */
public class Bus {

    private static final Path REGISTRO = Paths.get("datos/eventos.log");

    public static synchronized void publicar(String evento) {
        try {
            Files.createDirectories(REGISTRO.getParent());
            Files.writeString(REGISTRO, evento + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Devuelve los eventos que aparecieron despues de la posicion dada. */
    public static synchronized List<String> desde(int posicion) {
        List<String> nuevos = new ArrayList<>();
        try {
            if (!Files.exists(REGISTRO)) return nuevos;
            List<String> todos = Files.readAllLines(REGISTRO);
            for (int i = posicion; i < todos.size(); i++) {
                if (!todos.get(i).isBlank()) nuevos.add(todos.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nuevos;
    }
}
