package pe.mercadotech.correo.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.mercadotech.correo.config.RabbitMQConfig;

@Component
public class PedidoListener {

    @RabbitListener(queues = RabbitMQConfig.COLA_CORREO)
    public void alRecibirPedidoConfirmado(PedidoConfirmadoEvent evento) {
        System.out.println("[correo] enviando confirmacion del pedido "
                + evento.getPedidoId() + " al cliente");
    }

    /*
     * EJERCICIO DE CIERRE: un fanout permite sumar otro consumidor sin cambiar
     * al publicador. Descomentar junto con la cola y binding de RabbitMQConfig.
     *
     * @RabbitListener(queues = "reportes.pedidos-confirmados")
     * public void actualizarReporteDeVentas(PedidoConfirmadoEvent evento) {
     *     System.out.println("[reportes] actualizando venta del pedido "
     *             + evento.getPedidoId());
     * }
     */
}
