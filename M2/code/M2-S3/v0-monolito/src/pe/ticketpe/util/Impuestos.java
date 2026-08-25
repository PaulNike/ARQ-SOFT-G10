package pe.ticketpe.util;

/** Utilitario de impuestos. Existe... pero no todos lo usan. */
public class Impuestos {

    // Copia 1 de 4 de la tasa de IGV en este proyecto.
    public static final double IGV = 0.18;

    public static double calcular(double base) {
        return base * IGV;
    }
}
