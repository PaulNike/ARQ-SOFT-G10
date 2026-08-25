package pe.ticketpe.presentacion.viewmodel;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.dominio.Inventario;
import pe.ticketpe.presentacion.binding.Observable;

/**
 * VIEW MODEL.
 *
 * Expone el ESTADO de la pantalla como propiedades observables.
 * No conoce ninguna vista: no la llama, no la nombra, no la importa.
 * Cuando el estado cambia, quien se haya suscrito se entera solo.
 *
 * Esa es la diferencia con MVC: alli el controlador entrega el modelo
 * a la vista; aca el ViewModel solo actualiza su estado.
 */
public class DisponibilidadViewModel {

    private final VentaService servicio;
    private final String eventoId;

    public final Observable<Integer> disponibles = new Observable<>(0);
    public final Observable<String>  etiqueta    = new Observable<>("");

    public DisponibilidadViewModel(VentaService servicio, String eventoId) {
        this.servicio = servicio;
        this.eventoId = eventoId;
        refrescar();
    }

    /** Comando: la vista lo dispara sin saber que pasa despues. */
    public void comprar(String cliente) {
        servicio.vender(eventoId, cliente);
        refrescar();
    }

    private void refrescar() {
        Inventario inv = servicio.consultar(eventoId);
        int libres = inv.aforo() - inv.comprometidas();
        int porcentaje = (libres * 100) / inv.aforo();

        String texto;
        if (libres == 0)           texto = "AGOTADO";
        else if (porcentaje <= 20) texto = "ULTIMAS ENTRADAS";
        else                       texto = "DISPONIBLE";

        disponibles.set(libres);
        etiqueta.set(texto);
    }
}
