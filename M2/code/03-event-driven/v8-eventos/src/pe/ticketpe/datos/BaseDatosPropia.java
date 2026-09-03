package pe.ticketpe.datos;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La base de datos de UN servicio. Cada servicio tiene la suya.
 *
 * Misma clase, distinto archivo: venta abre datos/venta.db y
 * catalogo abre datos/catalogo.db. Ningun servicio conoce el archivo del otro.
 *
 * Esa es la diferencia con SOA, y es la unica que importa.
 */
public class BaseDatosPropia {

    private final Path archivo;

    public BaseDatosPropia(String ruta, String filaInicial) {
        this.archivo = Paths.get(ruta);
        try {
            if (archivo.getParent() != null) Files.createDirectories(archivo.getParent());
            if (!Files.exists(archivo)) {
                Files.writeString(archivo, filaInicial == null ? "" : filaInicial + "\n");
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
