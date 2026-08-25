package pe.ticketpe.controlador;

import java.util.Locale;
import pe.ticketpe.servicio.PrecioServicio;
import pe.ticketpe.servicio.VentaServicio;
import pe.ticketpe.util.Impuestos;

public class VentaControlador {

    // Copia 4 de 4 de la tasa de IGV en este proyecto.
    private static final double IGV_PANTALLA = 0.18;

    private final VentaServicio ventaServicio = new VentaServicio();
    private final PrecioServicio precioServicio = new PrecioServicio();

    public void comprar(String eventoId, String clienteId, int cantidad) {
        ventaServicio.vender(eventoId, clienteId, cantidad);

        double subtotal = precioServicio.subtotal(eventoId, cantidad);
        double igv = subtotal * IGV_PANTALLA;
        double total = subtotal + igv;

        System.out.printf(Locale.US, "Venta %s: %d entradas%n", clienteId, cantidad);
        System.out.printf(Locale.US, "  Subtotal: S/ %.2f%n", subtotal);
        System.out.printf(Locale.US, "  IGV %.0f%%:  S/ %.2f%n", IGV_PANTALLA * 100, igv);
        System.out.printf(Locale.US, "  Total:    S/ %.2f%n", total);

        // Impuestos existe, pero aqui no se usa. Nadie lo noto.
        if (false) System.out.println(Impuestos.calcular(subtotal));
    }
}
