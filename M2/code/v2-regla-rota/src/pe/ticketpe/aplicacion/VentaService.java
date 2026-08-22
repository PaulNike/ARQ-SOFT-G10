package pe.ticketpe.aplicacion;

import pe.ticketpe.dominio.Inventario;

/**
 * CAPA DE APLICACION.
 *
 * Orquesta el caso de uso. Todos los canales pasan por aca, asi que
 * ninguno puede tener su propia version de la regla.
 */
public class VentaService {

    private final InventarioRepositorio repositorio;

    public VentaService(InventarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean reservar(String eventoId, String cliente) {
        Inventario inventario = repositorio.buscarPorEvento(eventoId);
        return inventario.reservar(cliente);
    }

    public boolean vender(String eventoId, String cliente) {
        Inventario inventario = repositorio.buscarPorEvento(eventoId);
        return inventario.vender(cliente);
    }

    public Inventario consultar(String eventoId) {
        return repositorio.buscarPorEvento(eventoId);
    }
}
