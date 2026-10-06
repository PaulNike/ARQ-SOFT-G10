package pe.mercadotech.pedidos.exception;

public class SinStockRealException extends RuntimeException {
    public SinStockRealException(String mensaje) {
        super(mensaje);
    }
}
