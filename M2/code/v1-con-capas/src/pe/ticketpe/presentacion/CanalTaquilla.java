package pe.ticketpe.presentacion;

import pe.ticketpe.aplicacion.VentaService;

/** CAPA DE PRESENTACION. Otro canal, el MISMO servicio, la MISMA regla. */
public class CanalTaquilla {

    private final VentaService servicio;

    public CanalTaquilla(VentaService servicio) { this.servicio = servicio; }

    public void vender(String eventoId, String cliente) {
        boolean ok = servicio.vender(eventoId, cliente);
        System.out.println("[TAQUILLA] venta " + (ok ? "OK" : "RECHAZADA") + " para " + cliente);
    }
}
