package pe.mercadotech.envios.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pe.mercadotech.envios.dto.CrearEnvioRequest;
import pe.mercadotech.envios.dto.EnvioResponse;
import pe.mercadotech.envios.service.EnvioService;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnvioResponse generar(@Valid @RequestBody CrearEnvioRequest request) {
        return envioService.generar(request);
    }
}
