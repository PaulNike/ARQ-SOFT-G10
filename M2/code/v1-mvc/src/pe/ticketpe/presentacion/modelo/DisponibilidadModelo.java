package pe.ticketpe.presentacion.modelo;

/**
 * MODELO (de la vista).
 *
 * Lo que la pantalla necesita mostrar, ya resuelto. Ninguna vista
 * tiene que volver a decidir nada: solo lee estos campos y los pinta.
 */
public record DisponibilidadModelo(String eventoId, int disponibles, String etiqueta) { }
