package pe.mercadotech.envios.exception;

public class SinStockRealException extends RuntimeException {
    public SinStockRealException() {
        super("sin stock real en almacen");
    }
}
