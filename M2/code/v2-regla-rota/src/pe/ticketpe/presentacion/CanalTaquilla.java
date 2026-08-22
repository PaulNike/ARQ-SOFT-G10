package pe.ticketpe.presentacion;

import pe.ticketpe.dominio.Inventario;
import pe.ticketpe.infraestructura.InventarioRepositorioMemoria;

/**
 * CAPA DE PRESENTACION -- CON LA REGLA DE DEPENDENCIA ROTA.
 *
 * Fijense en los imports: esta clase de presentacion importa
 * INFRAESTRUCTURA y DOMINIO, saltandose la capa de aplicacion.
 *
 * El codigo compila. Los tests de las otras capas siguen pasando.
 * Nada avisa que algo se rompio... hasta el cierre del dia.
 */
public class CanalTaquilla {

    private final InventarioRepositorioMemoria repositorio;

    public CanalTaquilla(InventarioRepositorioMemoria repositorio) {
        this.repositorio = repositorio;
    }

    public void vender(String eventoId, String cliente) {
        Inventario inventario = repositorio.buscarPorEvento(eventoId);
        // Se salta VentaService y con el, la regla de aforo
        inventario.forzarVenta(cliente);
        System.out.println("[TAQUILLA] venta OK para " + cliente + "   <-- sin pasar por el servicio");
    }
}
