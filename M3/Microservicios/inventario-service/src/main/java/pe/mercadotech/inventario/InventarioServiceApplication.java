package pe.mercadotech.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * No conoce la dirección del Gateway ni de otros servicios. Al registrarse en
 * Eureka publica su ubicación actual y puede cambiarla sin modificar clientes.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InventarioServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
    }
}
