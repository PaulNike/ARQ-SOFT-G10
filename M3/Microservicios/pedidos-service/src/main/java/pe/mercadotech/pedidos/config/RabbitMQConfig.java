package pe.mercadotech.pedidos.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_PEDIDOS = "pedidos.eventos";

    @Bean
    public FanoutExchange pedidosExchange() {
        // Fanout expresa que el evento interesa a cero, uno o muchos consumidores.
        return new FanoutExchange(EXCHANGE_PEDIDOS, true, false);
    }

    @Bean
    public MessageConverter mensajeJsonConverter() {
        // JSON permite que cada microservicio conserve su propia clase del contrato.
        return new Jackson2JsonMessageConverter();
    }
}
