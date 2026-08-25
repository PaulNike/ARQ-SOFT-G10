package pe.ticketpe.catalogo.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import pe.ticketpe.catalogo.EventoDto;

public class EventoAlmacen {

    private static final Map<String, EventoInterno> DATOS = new LinkedHashMap<>();

    static {
        DATOS.put("EV-1", new EventoInterno("EV-1", "Concierto Estadio Nacional", 40000, 180.00));
    }

    public EventoDto aDto(String id) {
        EventoInterno e = DATOS.get(id);
        return e == null ? null : new EventoDto(e.id, e.nombre, e.aforo, e.precioUnitario, e.vendidas);
    }

    public boolean hayCupo(String id, int cantidad) {
        EventoInterno e = DATOS.get(id);
        return e != null && e.vendidas + cantidad <= e.aforo;
    }

    public void sumarVendidas(String id, int cantidad) {
        EventoInterno e = DATOS.get(id);
        if (e != null) e.vendidas += cantidad;
    }
}
