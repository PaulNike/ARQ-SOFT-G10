package pe.ticketpe.modelo;

public class Evento {
    public final String id;
    public final String nombre;
    public final int aforo;
    public final double precioUnitario;
    public int vendidas = 0;

    public Evento(String id, String nombre, int aforo, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.aforo = aforo;
        this.precioUnitario = precioUnitario;
    }
}
