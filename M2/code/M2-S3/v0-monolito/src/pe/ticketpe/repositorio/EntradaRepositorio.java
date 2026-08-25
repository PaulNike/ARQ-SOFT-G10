package pe.ticketpe.repositorio;

import java.util.ArrayList;
import java.util.List;
import pe.ticketpe.modelo.Entrada;
import pe.ticketpe.servicio.AforoServicio;

/**
 * El repositorio consulta al servicio antes de guardar.
 * Parecio practico en su momento: crea un ciclo repositorio <-> servicio.
 */
public class EntradaRepositorio {

    private static final List<Entrada> DATOS = new ArrayList<>();
    private final AforoServicio aforoServicio = new AforoServicio();

    public void guardar(Entrada entrada) {
        if (!aforoServicio.puedeRegistrarUnaMas(entrada.eventoId)) {
            throw new IllegalStateException("Aforo completo");
        }
        DATOS.add(entrada);
    }

    public Entrada buscar(String id) {
        for (Entrada e : DATOS) {
            if (e.id.equals(id)) return e;
        }
        return null;
    }

    public int contarPorEvento(String eventoId) {
        int n = 0;
        for (Entrada e : DATOS) {
            if (e.eventoId.equals(eventoId)) n++;
        }
        return n;
    }

    public List<Entrada> todas() {
        return DATOS;
    }
}
