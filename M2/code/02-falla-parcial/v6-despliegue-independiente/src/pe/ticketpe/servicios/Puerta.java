package pe.ticketpe.servicios;

/**
 * Simula al personal de puerta: valida entradas cada segundo, sin parar.
 * Se queda corriendo para que se vea que pasa mientras otro servicio se reinicia.
 */
public class Puerta {
    public static void main(String[] args) throws Exception {
        int veces = args.length > 0 ? Integer.parseInt(args[0]) : 10;
        for (int i = 1; i <= veces; i++) {
            String entrada = "E-" + i;
            String r;
            try {
                r = Http.llamar("http://localhost:8083/acceso?entrada=" + entrada);
            } catch (Exception e) {
                r = "resultado=SIN SERVICIO DE ACCESO";
            }
            System.out.printf("   t+%02ds  puerta valida %-5s -> %s%n", i, entrada, r);
            Thread.sleep(1000);
        }
    }
}
