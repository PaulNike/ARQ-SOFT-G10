package pe.ticketpe.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * CAPA DE DOMINIO.
 *
 * Unico dueno de la regla de aforo. No existe otra copia en todo el sistema.
 * No sabe que hay una base de datos, ni una pantalla, ni canales de venta.
 */
public class Inventario {

    private final String eventoId;
    private final int aforo;
    private final List<String> vendidas = new ArrayList<>();
    private final List<String> reservas = new ArrayList<>();

    public Inventario(String eventoId, int aforo) {
        this.eventoId = eventoId;
        this.aforo = aforo;
    }

    /** LA regla, escrita una sola vez. */
    private boolean hayCupo() {
        return (vendidas.size() + reservas.size()) < aforo;
    }

    public boolean reservar(String cliente) {
        if (!hayCupo()) return false;
        reservas.add(cliente);
        return true;
    }

    public boolean vender(String cliente) {
        if (!hayCupo()) return false;
        vendidas.add(cliente);
        return true;
    }

    /**
     * ATAJO agregado "temporalmente" para que taquilla no dependa del servicio.
     * Escribe la venta SIN pasar por hayCupo().
     * Nadie lo puso con mala intencion: solo querian que fuera mas rapido.
     */
    public void forzarVenta(String cliente) {
        vendidas.add(cliente);
    }














































































}
