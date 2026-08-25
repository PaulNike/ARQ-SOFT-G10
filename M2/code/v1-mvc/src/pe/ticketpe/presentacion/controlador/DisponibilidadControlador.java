package pe.ticketpe.presentacion.controlador;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.dominio.Inventario;
import pe.ticketpe.presentacion.modelo.DisponibilidadModelo;

/**
 * CONTROLADOR.
 *
 * Recibe la peticion, pide los datos a la capa de aplicacion y arma
 * el modelo que la vista va a pintar. La decision de la etiqueta vive
 * aca UNA sola vez, y todas las vistas la reciben ya resuelta.
 */
public class DisponibilidadControlador {

    private final VentaService servicio;

    public DisponibilidadControlador(VentaService servicio) { this.servicio = servicio; }

    public DisponibilidadModelo consultar(String eventoId) {
        Inventario inv = servicio.consultar(eventoId);
        int disponibles = inv.aforo() - inv.comprometidas();
        int porcentaje = (disponibles * 100) / inv.aforo();

        String etiqueta;
        if (disponibles == 0)      etiqueta = "AGOTADO";
        else if (porcentaje <= 20) etiqueta = "ULTIMAS ENTRADAS";
        else                       etiqueta = "DISPONIBLE";

        return new DisponibilidadModelo(eventoId, disponibles, etiqueta);
    }
}
