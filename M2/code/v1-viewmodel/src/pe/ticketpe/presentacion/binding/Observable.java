package pe.ticketpe.presentacion.binding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * BINDING minimo -- 20 lineas.
 *
 * En WPF, Android o Vue esto lo pone el framework. Aca lo escribimos
 * a mano para que se vea que no hay magia: es una lista de interesados
 * a los que se avisa cuando el valor cambia.
 */
public class Observable<T> {

    private T valor;
    private final List<Consumer<T>> suscriptores = new ArrayList<>();

    public Observable(T inicial) { this.valor = inicial; }

    public T get() { return valor; }

    public void set(T nuevo) {
        if (nuevo != null && nuevo.equals(valor)) return;   // sin cambio, sin aviso
        this.valor = nuevo;
        for (Consumer<T> s : suscriptores) s.accept(nuevo);  // aca ocurre el "binding"
    }

    /** La vista se suscribe una vez y se olvida: nadie tiene que llamarla despues. */
    public void alCambiar(Consumer<T> accion) {
        suscriptores.add(accion);
        accion.accept(valor);
    }
}
