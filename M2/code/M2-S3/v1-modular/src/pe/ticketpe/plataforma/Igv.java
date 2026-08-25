package pe.ticketpe.plataforma;

/**
 * La tasa de IGV vive aqui. En una sola linea. Para todo el sistema.
 */
public final class Igv {

    public static final double TASA = 0.18;

    private Igv() { }

    public static double sobre(double base) {
        return base * TASA;
    }

    public static int porcentaje() {
        return (int) Math.round(TASA * 100);
    }
}
