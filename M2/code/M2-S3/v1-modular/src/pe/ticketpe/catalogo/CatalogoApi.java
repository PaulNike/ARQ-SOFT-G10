package pe.ticketpe.catalogo;

import pe.ticketpe.catalogo.internal.EventoAlmacen;

/**
 * Puerta de entrada al modulo catalogo.
 * Todo lo que esta bajo internal/ es asunto interno: nadie de afuera lo toca.
 */
public class CatalogoApi {

    private final EventoAlmacen almacen = new EventoAlmacen();

    public EventoDto buscar(String eventoId) {
        return almacen.aDto(eventoId);
    }

    public boolean hayCupo(String eventoId, int cantidad) {
        return almacen.hayCupo(eventoId, cantidad);
    }

    public void registrarVendidas(String eventoId, int cantidad) {
        almacen.sumarVendidas(eventoId, cantidad);
    }
}
