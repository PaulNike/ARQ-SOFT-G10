package pe.mercadotech.inventario.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
public class PingController {

    // El valor por defecto hace visible si Config Server no aportó la propiedad.
    @Value("${mensaje.bienvenida:sin config}")
    private String mensaje;

    @GetMapping("/ping")
    Map<String, String> ping() {
        return Map.of("servicio", "inventario", "estado", "vivo", "mensaje", mensaje);
    }

    /*
     * EJERCICIO DE CIERRE (no forma parte del código inicial):
     *
     * @GetMapping("/version")
     * String version() {
     *     return "1.0.0";
     * }
     */
}
