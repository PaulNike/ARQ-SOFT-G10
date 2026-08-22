package pe.ticketpe.presentacion;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.dominio.Inventario;

/**
 * PRESENTACION -- la misma decision, copiada y pegada.
 *
 * Cuando el negocio cambio el umbral de "ultimas entradas" de 50% a 20%,
 * alguien actualizo la web y se olvido del movil.
 * Mismo error del ciclo 1, otra escala.
 */
public class PantallaMovil {

    private final VentaService servicio;

    public PantallaMovil(VentaService servicio) { this.servicio = servicio; }

    public void mostrar(String eventoId) {
        Inventario inv = servicio.consultar(eventoId);
        int disponibles = inv.aforo() - inv.comprometidas();
        int porcentaje = (disponibles * 100) / inv.aforo();

        // DECISION metida en la vista - copia 2 (DESACTUALIZADA: umbral viejo de 50%)
        String etiqueta;
        if (disponibles == 0)        etiqueta = "AGOTADO";
        else if (porcentaje <= 50)   etiqueta = "ULTIMAS ENTRADAS";
        else                         etiqueta = "DISPONIBLE";

        System.out.println("[MOVIL]  " + eventoId + " | quedan " + disponibles + " | " + etiqueta);
    }
}
