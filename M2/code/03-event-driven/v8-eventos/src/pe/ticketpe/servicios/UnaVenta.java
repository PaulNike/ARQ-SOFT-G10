package pe.ticketpe.servicios;

/** Una sola venta, para contar sin ruido cuantas veces se procesa el evento. */
public class UnaVenta {
    public static void main(String[] args) throws Exception {
        System.out.println("   " + Http.llamar(
                "http://localhost:8081/venta?evento=EV-1&cliente=C-001&cantidad=2"));
    }
}
