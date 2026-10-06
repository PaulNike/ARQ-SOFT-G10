package pe.mercadotech.pagos.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.mercadotech.pagos.dto.CrearPagoRequest;
import pe.mercadotech.pagos.dto.PagoResponse;
import pe.mercadotech.pagos.service.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagoResponse cobrar(@Valid @RequestBody CrearPagoRequest request) {
        return PagoResponse.desde(pagoService.cobrar(request));
    }

    @PostMapping("/{id}/reversar")
    public PagoResponse reversar(@PathVariable Long id) {
        return PagoResponse.desde(pagoService.reversar(id));
    }
}
