package pe.mercadotech.correo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_PEDIDOS = "pedidos.eventos";
    public static final String COLA_CORREO = "correo.pedidos-confirmados";

    @Bean
    public FanoutExchange pedidosExchange() {
        return new FanoutExchange(EXCHANGE_PEDIDOS, true, false);
    }

    @Bean
    public Queue correoQueue() {
        // La cola durable conserva pendientes aunque Correo se reinicie.
        return new Queue(COLA_CORREO, true);
    }

    @Bean
    public Binding correoBinding(Queue correoQueue, FanoutExchange pedidosExchange) {
        return BindingBuilder.bind(correoQueue).to(pedidosExchange);
    }

    @Bean
    public MessageConverter mensajeJsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*
     * EJERCICIO DE CIERRE: descomentar junto con el segundo @RabbitListener.
     *
     * @Bean
     * public Queue reporteVentasQueue() {
     *     return new Queue("reportes.pedidos-confirmados", true);
     * }
     *
     * @Bean
     * public Binding reporteVentasBinding(
     *         Queue reporteVentasQueue, FanoutExchange pedidosExchange) {
     *     return BindingBuilder.bind(reporteVentasQueue).to(pedidosExchange);
     * }
     */
}
