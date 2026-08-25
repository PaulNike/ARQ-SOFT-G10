package pe.ticketpe.acceso.internal;

import java.util.HashSet;
import java.util.Set;
import pe.ticketpe.venta.VentaApi;

public class ControlPuerta {

    private static final Set<String> YA_USADAS = new HashSet<>();

    // Acceso le pregunta a Venta por la API publica. Nunca por dentro.
    private final VentaApi venta = new VentaApi();

    public String validar(String entradaId) {
        if (!venta.existeEntrada(entradaId)) return "RECHAZADO (no existe)";
        if (YA_USADAS.contains(entradaId)) return "RECHAZADO (ya usada)";
        YA_USADAS.add(entradaId);
        return "VALIDO";
    }
}
