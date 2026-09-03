package pe.ticketpe.datos;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LA base de datos. Una sola, compartida por todos los servicios.
 *
 * Es un archivo de texto para que se pueda abrir y leer en clase.
 * Cada linea es una fila:
 *
 *   EVENTO;EV-1;Concierto Estadio Nacional;40000;180.00
 *   ENTRADA;E-1;EV-1;C-001
 *
 * Cualquier servicio que conozca este formato puede leer cualquier tabla.
 * Eso es comodo. Y es exactamente el problema.
 */
public class BaseDatos {

    private final Path archivo;

    public BaseDatos(String ruta) {
        this.archivo = Paths.get(ruta);
        try {
            if (archivo.getParent() != null) Files.createDirectories(archivo.getParent());
            if (!Files.exists(archivo)) {
                Files.writeString(archivo,
                        "EVENTO;EV-1;Concierto Estadio Nacional;40000;180.00\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo preparar la base: " + e.getMessage());
        }
    }

    public synchronized void insertar(String linea) {
        try {
            Files.writeString(archivo, linea + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Devuelve todas las filas de una tabla. Sin permisos, sin restricciones. */
    public synchronized List<String[]> tabla(String nombre) {
        List<String[]> filas = new ArrayList<>();
        try {
            for (String linea : Files.readAllLines(archivo)) {
                if (linea.isBlank()) continue;
                String[] c = linea.split(";");
                if (c[0].equals(nombre)) filas.add(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filas;
    }
}
