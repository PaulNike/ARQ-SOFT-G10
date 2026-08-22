package pe.ticketpe.infraestructura;

import java.util.HashMap;
import java.util.Map;
import pe.ticketpe.aplicacion.InventarioRepositorio;
import pe.ticketpe.dominio.Inventario;

/**
 * CAPA DE INFRAESTRUCTURA.
 *
 * Hoy guarda en memoria. Manana podria ser PostgreSQL o Redis:
 * ninguna otra capa se entera del cambio.
 */
public class InventarioRepositorioMemoria implements InventarioRepositorio {

    private final Map<String, Inventario> datos = new HashMap<>();

    public void registrarEvento(String eventoId, int aforo) {
        datos.put(eventoId, new Inventario(eventoId, aforo));
    }

    @Override
    public Inventario buscarPorEvento(String eventoId) {
        return datos.get(eventoId);
    }
}
