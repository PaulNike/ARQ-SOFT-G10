package pe.mercadotech.pedidos.exception;

public class EnviosServiceCaidoException extends RuntimeException {
    public EnviosServiceCaidoException(String mensaje) {
        super("Envíos no está disponible: " + mensaje);
    }
}
