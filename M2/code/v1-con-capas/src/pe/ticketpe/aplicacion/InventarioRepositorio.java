package pe.ticketpe.aplicacion;

import pe.ticketpe.dominio.Inventario;

/**
 * CAPA DE APLICACION.
 *
 * La interfaz vive aca, del lado de quien la usa; la implementacion vive
 * en infraestructura. Eso es DIP: la logica no depende de la tecnologia.
 */
public interface InventarioRepositorio {
    Inventario buscarPorEvento(String eventoId);
}
