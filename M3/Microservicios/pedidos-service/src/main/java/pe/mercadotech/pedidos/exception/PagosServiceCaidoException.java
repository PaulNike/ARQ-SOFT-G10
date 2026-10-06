package pe.mercadotech.pedidos.exception;

public class PagosServiceCaidoException extends RuntimeException {
    public PagosServiceCaidoException(String mensaje) {
        super("Pagos no está disponible: " + mensaje);
    }
}
