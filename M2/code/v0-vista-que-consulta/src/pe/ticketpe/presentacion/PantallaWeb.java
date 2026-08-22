package pe.ticketpe.presentacion;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.dominio.Inventario;

/**
 * PRESENTACION -- vista que ademas DECIDE.
 *
 * Esta clase hace tres cosas a la vez:
 *   1. consulta los datos,
 *   2. decide que etiqueta corresponde (eso es una decision, no un dibujo),
 *   3. pinta.
 *
 * La capa de negocio esta bien: la regla de aforo sigue en el dominio.
 * El problema aca es de OTRA escala: dentro de la presentacion.
 */
public class PantallaWeb {

    private final VentaService servicio;

    public PantallaWeb(VentaService servicio) { this.servicio = servicio; }

    public void mostrar(String eventoId) {
        Inventario inv = servicio.consultar(eventoId);
        int disponibles = inv.aforo() - inv.comprometidas();
        int porcentaje = (disponibles * 100) / inv.aforo();

        // DECISION metida en la vista - copia 1 (actualizada)
        String etiqueta;
        if (disponibles == 0)        etiqueta = "AGOTADO";
        else if (porcentaje <= 20)   etiqueta = "ULTIMAS ENTRADAS";
        else                         etiqueta = "DISPONIBLE";

        System.out.println("[WEB]    " + eventoId + " | quedan " + disponibles + " | " + etiqueta);
    }
}
