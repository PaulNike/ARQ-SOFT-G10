package pe.ticketpe.catalogo;

/** Lo que el modulo catalogo deja ver hacia afuera. Nada mas. */
public class EventoDto {
    public final String id;
    public final String nombre;
    public final int aforo;
    public final double precioUnitario;
    public final int vendidas;

    public EventoDto(String id, String nombre, int aforo, double precioUnitario, int vendidas) {
        this.id = id;
        this.nombre = nombre;
        this.aforo = aforo;
        this.precioUnitario = precioUnitario;
        this.vendidas = vendidas;
    }
}
