package pe.ticketpe.presentacion.vista;

import pe.ticketpe.presentacion.viewmodel.DisponibilidadViewModel;

/**
 * VISTA.
 *
 * Se suscribe una vez en el constructor y despues nadie vuelve a
 * llamarla: se repinta sola cuando el ViewModel cambia.
 */
public class VistaConsola {

    public VistaConsola(DisponibilidadViewModel vm) {
        vm.disponibles.alCambiar(n -> System.out.println("   [pantalla] quedan: " + n));
        vm.etiqueta.alCambiar(t   -> System.out.println("   [pantalla] estado: " + t));
    }
}
