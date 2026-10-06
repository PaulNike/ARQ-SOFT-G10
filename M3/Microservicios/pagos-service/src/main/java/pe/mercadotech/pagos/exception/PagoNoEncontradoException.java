package pe.mercadotech.pagos.exception;

public class PagoNoEncontradoException extends RuntimeException {
    public PagoNoEncontradoException(Long id) {
        super("Pago no encontrado: " + id);
    }
}
