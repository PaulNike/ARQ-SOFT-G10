package pe.mercadotech.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del microservicio de Catálogo.
 *
 * <p>Este servicio no importa ninguna clase de pedidos-service: posee su propio
 * modelo, su propia base de datos y su propio contrato HTTP. Por eso se puede
 * levantar y probar por sí solo. Esa autonomía es la diferencia esencial entre
 * un microservicio y un módulo de un monolito, cuyos módulos se despliegan y
 * ejecutan juntos dentro del mismo proceso.</p>
 */
@SpringBootApplication
public class CatalogoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogoServiceApplication.class, args);
    }
}
