package pe.mercadotech.pedidos.event;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pe.mercadotech.pedidos.config.RabbitMQConfig;
import pe.mercadotech.pedidos.model.Pedido;

@Component
public class EventoPublicador {

    private final RabbitTemplate rabbitTemplate;

    public EventoPublicador(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicarPedidoConfirmado(Pedido pedido) {
        PedidoConfirmadoEvent evento = new PedidoConfirmadoEvent(
                pedido.getId(), pedido.getProductoId(), pedido.getCantidad());
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_PEDIDOS, "", evento);
        } catch (AmqpException e) {
            /*
             * El pedido ya fue confirmado. Una falla del canal de notificaciones no
             * debe convertir una operación exitosa del cliente en un error HTTP.
             */
            System.err.println("[pedidos] RabbitMQ no disponible; evento no publicado: "
                    + e.getMessage());
        }
    }
}
