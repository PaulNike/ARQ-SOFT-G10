package pe.mercadotech.pedidos.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mercadotech.pedidos.client.CatalogoClient;
import pe.mercadotech.pedidos.client.DisponibilidadDTO;
import pe.mercadotech.pedidos.exception.CatalogoServiceCaidoException;
import pe.mercadotech.pedidos.exception.PedidoNoEncontradoException;
import pe.mercadotech.pedidos.event.EventoPublicador;
import pe.mercadotech.pedidos.model.Pedido;
import pe.mercadotech.pedidos.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository repositorio;
    private final CatalogoClient catalogoClient;
    private final EventoPublicador eventoPublicador;

    public PedidoService(
            PedidoRepository repositorio,
            CatalogoClient catalogoClient,
            EventoPublicador eventoPublicador) {
        this.repositorio = repositorio;
        this.catalogoClient = catalogoClient;
        this.eventoPublicador = eventoPublicador;
    }

    @Transactional
    public Pedido confirmar(Long productoId, int cantidad) {
        // La excepción se deja subir: sin verificación, la versión base no acepta el pedido.
        DisponibilidadDTO d = catalogoClient.verificarDisponibilidad(productoId); // puede lanzar
        if (!d.disponible()) {
            throw new IllegalStateException("Producto sin stock: " + productoId);
        }
        Pedido pedido = repositorio.save(new Pedido(productoId, cantidad, true));
        eventoPublicador.publicarPedidoConfirmado(pedido);
        return pedido;
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new PedidoNoEncontradoException(id));
    }

    /**
     * VERSIÓN DEL EJERCICIO DE CIERRE (el controller no la usa en la versión base).
     * Aquí una caída de Catálogo cambia la política: se guarda el pedido para
     * reconciliarlo después, dejando explícito que el stock no fue verificado.
     */
    @Transactional
    public Pedido confirmarConDegradacion(Long productoId, int cantidad) {
        boolean verificado;
        try {
            DisponibilidadDTO d = catalogoClient.verificarDisponibilidad(productoId);
            verificado = d.disponible();
        } catch (CatalogoServiceCaidoException e) {
            verificado = false; // se degrada: se guarda igual, pendiente de confirmar
        }
        return repositorio.save(new Pedido(productoId, cantidad, verificado));
    }
}
