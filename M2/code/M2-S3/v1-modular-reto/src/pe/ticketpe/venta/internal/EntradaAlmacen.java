package pe.ticketpe.venta.internal;

import java.util.ArrayList;
import java.util.List;

public class EntradaAlmacen {

    private static final List<EntradaInterna> DATOS = new ArrayList<>();
    private static int correlativo = 0;

    public String emitir(String eventoId, String clienteId) {
        correlativo++;
        String id = "E-" + correlativo;
        DATOS.add(new EntradaInterna(id, eventoId, clienteId));
        return id;
    }

    public boolean existe(String entradaId) {
        for (EntradaInterna e : DATOS) {
            if (e.id.equals(entradaId)) return true;
        }
        return false;
    }

    public int contarPorEvento(String eventoId) {
        int n = 0;
        for (EntradaInterna e : DATOS) {
            if (e.eventoId.equals(eventoId)) n++;
        }
        return n;
    }
}
