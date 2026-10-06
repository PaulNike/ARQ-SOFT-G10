package pe.mercadotech.pedidos.exception;

public class CatalogoServiceCaidoException extends RuntimeException {

    public CatalogoServiceCaidoException(String mensaje) {
        super("Catálogo no está disponible: " + mensaje);
    }
}
