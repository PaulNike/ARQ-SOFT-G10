package pe.mercadotech.catalogo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.mercadotech.catalogo.dto.ActualizarStockRequest;
import pe.mercadotech.catalogo.dto.CrearProductoRequest;
import pe.mercadotech.catalogo.dto.DisponibilidadResponse;
import pe.mercadotech.catalogo.dto.ProductoResponse;
import pe.mercadotech.catalogo.exception.ProductoNoEncontradoException;
import pe.mercadotech.catalogo.model.Producto;
import pe.mercadotech.catalogo.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repositorio;

    public ProductoService(ProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public ProductoResponse crear(CrearProductoRequest request) {
        Producto producto = new Producto(request.nombre(), request.precio(), request.stock());
        return ProductoResponse.desde(repositorio.save(producto));
    }

    @Transactional(readOnly = true)
    public ProductoResponse buscarPorId(Long id) {
        return ProductoResponse.desde(buscarEntidad(id));
    }

    @Transactional(readOnly = true)
    public List<ProductoResponse> listar() {
        return repositorio.findAll().stream().map(ProductoResponse::desde).toList();
    }

    @Transactional
    public ProductoResponse actualizarStock(Long id, ActualizarStockRequest request) {
        Producto producto = buscarEntidad(id);
        producto.actualizarStock(request.stock());
        return ProductoResponse.desde(producto);
    }

    @Transactional(readOnly = true)
    public DisponibilidadResponse verificarDisponibilidad(Long id) {
        Producto producto = buscarEntidad(id);
        return new DisponibilidadResponse(producto.getStock() > 0);
    }

    private Producto buscarEntidad(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }
}
