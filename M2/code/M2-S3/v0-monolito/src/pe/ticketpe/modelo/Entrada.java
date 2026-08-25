package pe.ticketpe.modelo;

public class Entrada {
    public final String id;
    public final String eventoId;
    public final String clienteId;
    public boolean usada = false;

    public Entrada(String id, String eventoId, String clienteId) {
        this.id = id;
        this.eventoId = eventoId;
        this.clienteId = clienteId;
    }
}
