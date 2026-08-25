package pe.ticketpe.venta.internal;

public class EntradaInterna {
    public final String id;
    public final String eventoId;
    public final String clienteId;

    public EntradaInterna(String id, String eventoId, String clienteId) {
        this.id = id;
        this.eventoId = eventoId;
        this.clienteId = clienteId;
    }
}
