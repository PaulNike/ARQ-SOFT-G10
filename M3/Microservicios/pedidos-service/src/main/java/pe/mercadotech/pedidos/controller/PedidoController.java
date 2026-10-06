package pe.mercadotech.pedidos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.mercadotech.pedidos.dto.CrearPedidoRequest;
import pe.mercadotech.pedidos.dto.PedidoResponse;
import pe.mercadotech.pedidos.service.PedidoService;
import pe.mercadotech.pedidos.saga.ConfirmarPedidoSaga;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ConfirmarPedidoSaga confirmarPedidoSaga;

    public PedidoController(PedidoService pedidoService, ConfirmarPedidoSaga confirmarPedidoSaga) {
        this.pedidoService = pedidoService;
        this.confirmarPedidoSaga = confirmarPedidoSaga;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse confirmar(@Valid @RequestBody CrearPedidoRequest request) {
        return PedidoResponse.desde(confirmarPedidoSaga.ejecutar(
                request.productoId(), request.cantidad(), request.monto()));
    }

    @GetMapping("/{id}")
    public PedidoResponse buscarPorId(@PathVariable Long id) {
        return PedidoResponse.desde(pedidoService.buscarPorId(id));
    }
}
