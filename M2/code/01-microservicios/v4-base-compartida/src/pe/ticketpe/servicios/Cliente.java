package pe.ticketpe.servicios;

/** Recorre el flujo: vende y luego pide el reporte. */
public class Cliente {
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("1. Venta de 2 entradas");
        System.out.println("   " + Http.llamar("http://localhost:8081/venta?evento=EV-1&cliente=C-001&cantidad=2"));
        System.out.println();
        System.out.println("2. Venta de 4 entradas");
        System.out.println("   " + Http.llamar("http://localhost:8081/venta?evento=EV-1&cliente=C-002&cantidad=4"));
        System.out.println();
        System.out.println("3. Reporte (lo calcula OTRO equipo, leyendo la misma base)");
        System.out.println("   " + Http.llamar("http://localhost:8084/reporte"));
        System.out.println();
    }
}
