package pe.ticketpe;

import pe.ticketpe.aplicacion.VentaService;
import pe.ticketpe.infraestructura.InventarioRepositorioMemoria;
import pe.ticketpe.presentacion.viewmodel.DisponibilidadViewModel;
import pe.ticketpe.presentacion.vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        InventarioRepositorioMemoria repositorio = new InventarioRepositorioMemoria();
        repositorio.registrarEvento("CONCIERTO-EN", 5);
        VentaService servicio = new VentaService(repositorio);

        DisponibilidadViewModel vm = new DisponibilidadViewModel(servicio, "CONCIERTO-EN");

        System.out.println("=== TicketPe :: disponibilidad (MVVM) ===");
        System.out.println();
        System.out.println("La vista se suscribe (aca se pinta el estado inicial):");
        new VistaConsola(vm);

        System.out.println();
        System.out.println("Ahora compran 3 personas. Nadie llama a la vista:");
        vm.comprar("ana");
        vm.comprar("beto");
        vm.comprar("carla");

        System.out.println();
        System.out.println("Compra una mas y se agota:");
        vm.comprar("diana");
        vm.comprar("elias");

        System.out.println();
        System.out.println("Nota: 'quedan' solo se repinto cuando cambio de verdad,");
        System.out.println("y 'estado' solo cuando la etiqueta cambio de valor.");
    }
}
