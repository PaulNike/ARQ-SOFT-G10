package pe.mercadotech.catalogo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.*;
import pe.mercadotech.catalogo.dto.ActualizarStockRequest;
import pe.mercadotech.catalogo.dto.CrearProductoRequest;
import pe.mercadotech.catalogo.dto.DisponibilidadResponse;
import pe.mercadotech.catalogo.dto.ProductoResponse;
import pe.mercadotech.catalogo.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalogo")
public class ProductoController {

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

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
        log.info("[{}] Verificando disponibilidad del producto {}", MDC.get("traceId"), id);
        return productoService.verificarDisponibilidad(id);
    }

    @GetMapping("/buscar")
    public List<ProductoResponse> buscarPorNombre(@RequestParam  String nombre) {
        return productoService.buscarPorNombre(nombre).stream()
                .map(ProductoResponse::desde)
                .collect(Collectors.toList());
    }
}
