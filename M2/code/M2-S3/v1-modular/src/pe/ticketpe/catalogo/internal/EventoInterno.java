package pe.ticketpe.catalogo.internal;

/** Representacion interna del evento. Fuera del modulo nadie sabe que existe. */
public class EventoInterno {
    public final String id;
    public final String nombre;
    public final int aforo;
    public final double precioUnitario;
    public int vendidas = 0;

    public EventoInterno(String id, String nombre, int aforo, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.aforo = aforo;
        this.precioUnitario = precioUnitario;
    }
}
