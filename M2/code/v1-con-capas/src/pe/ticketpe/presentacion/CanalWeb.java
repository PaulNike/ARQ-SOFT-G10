package pe.ticketpe.presentacion;

import pe.ticketpe.aplicacion.VentaService;

/** CAPA DE PRESENTACION. Solo formatea y delega. Cero reglas de negocio. */
public class CanalWeb {

    private final VentaService servicio;

    public CanalWeb(VentaService servicio) { this.servicio = servicio; }

    public void reservar(String eventoId, String cliente) {
        boolean ok = servicio.reservar(eventoId, cliente);
        System.out.println("[WEB]      reserva " + (ok ? "OK" : "RECHAZADA") + " para " + cliente);
    }

    public void vender(String eventoId, String cliente) {
        boolean ok = servicio.vender(eventoId, cliente);
        System.out.println("[WEB]      venta " + (ok ? "OK" : "RECHAZADA") + " para " + cliente);
    }
}
