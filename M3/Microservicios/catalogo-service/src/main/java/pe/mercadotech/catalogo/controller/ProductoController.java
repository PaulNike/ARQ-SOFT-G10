package pe.mercadotech.catalogo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.mercadotech.catalogo.dto.ActualizarStockRequest;
import pe.mercadotech.catalogo.dto.CrearProductoRequest;
import pe.mercadotech.catalogo.dto.DisponibilidadResponse;
import pe.mercadotech.catalogo.dto.ProductoResponse;
import pe.mercadotech.catalogo.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponse crear(@Valid @RequestBody CrearProductoRequest request) {
        return productoService.crear(request);
    }

    @GetMapping("/{id}")
    public ProductoResponse buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @GetMapping
    public List<ProductoResponse> listar() {
        return productoService.listar();
    }

    @PatchMapping("/{id}/stock")
    public ProductoResponse actualizarStock(
            @PathVariable Long id, @Valid @RequestBody ActualizarStockRequest request) {
        return productoService.actualizarStock(id, request);
    }

    @GetMapping("/{id}/disponibilidad")
    public DisponibilidadResponse verificarDisponibilidad(@PathVariable Long id) {
        return productoService.verificarDisponibilidad(id);
    }
}
