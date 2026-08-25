package pe.ticketpe.presentacion.vista;

import pe.ticketpe.presentacion.modelo.DisponibilidadModelo;

/** VISTA. Otro formato, el MISMO modelo. Imposible que discrepe de la web. */
public class VistaMovil {
    public void pintar(DisponibilidadModelo m) {
        System.out.println("[MOVIL]  " + m.etiqueta() + " (" + m.disponibles() + ") -- " + m.eventoId());
    }
}
