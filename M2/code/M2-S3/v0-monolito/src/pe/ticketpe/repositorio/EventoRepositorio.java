package pe.ticketpe.repositorio;

import java.util.LinkedHashMap;
import java.util.Map;
import pe.ticketpe.modelo.Evento;

public class EventoRepositorio {

    private static final Map<String, Evento> DATOS = new LinkedHashMap<>();

    static {
        DATOS.put("EV-1", new Evento("EV-1", "Concierto Estadio Nacional", 40000, 180.00));
    }

    public Evento buscar(String id) {
        return DATOS.get(id);
    }

    public void guardar(Evento evento) {
        DATOS.put(evento.id, evento);
    }
}
