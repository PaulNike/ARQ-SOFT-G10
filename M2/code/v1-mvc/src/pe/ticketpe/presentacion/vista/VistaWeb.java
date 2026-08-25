package pe.ticketpe.presentacion.vista;

import pe.ticketpe.presentacion.modelo.DisponibilidadModelo;

/** VISTA. Solo pinta. Cero condicionales de negocio. */
public class VistaWeb {
    public void pintar(DisponibilidadModelo m) {
        System.out.println("[WEB]    " + m.eventoId() + " | quedan " + m.disponibles() + " | " + m.etiqueta());
    }
}
