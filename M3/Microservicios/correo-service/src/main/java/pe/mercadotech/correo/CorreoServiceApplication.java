package pe.mercadotech.correo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

/**
 * Consumidor sin API HTTP: su única responsabilidad es reaccionar a eventos.
 * Esto evita una dependencia directa desde pedidos-service hacia Correo.
 */
@EnableRabbit
@SpringBootApplication
public class CorreoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CorreoServiceApplication.class, args);
    }
}
