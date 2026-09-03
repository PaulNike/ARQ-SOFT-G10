package pe.ticketpe.servicios;

import pe.ticketpe.bus.Consumidor;

/**
 * Los consumidores de eventos, todos juntos para que se lean de un vistazo.
 *
 * Ninguno conoce a venta. Ninguno tiene una URL de venta.
 * Solo saben que existe un evento llamado VENTA_REALIZADA.
 *
 * Formato del evento:  VENTA_REALIZADA ; clienteId ; cantidad ; eventoId
 */
public class Consumidores {

    public static void main(String[] args) throws Exception {

        new Consumidor("facturacion", "VENTA_REALIZADA", c ->
                System.out.println("   [facturacion]  comprobante emitido para " + c[1]
                        + " (" + c[2] + " entradas)")
        ).arrancar();

        new Consumidor("fidelizacion", "VENTA_REALIZADA", c ->
                System.out.println("   [fidelizacion] " + (Integer.parseInt(c[2]) * 10)
                        + " puntos para " + c[1])
        ).arrancar();

        // ---------------------------------------------------------------
        // AQUI se agrega uno nuevo. Nada mas. Sin tocar a venta.
        // Descomentar en clase y volver a correr:
        //
        // new Consumidor("notificaciones", "VENTA_REALIZADA", c ->
        //         System.out.println("   [notificaciones] correo enviado a " + c[1])
        // ).arrancar();
        // ---------------------------------------------------------------

        Thread.sleep(60000);
    }
}
