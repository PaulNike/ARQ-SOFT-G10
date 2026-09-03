package pe.ticketpe.bus;

import java.util.List;
import java.util.function.Consumer;

/**
 * Base para escuchar un tipo de evento.
 *
 * Un consumidor nuevo se escribe extendiendo esto. No hace falta
 * tocar a quien publica, ni conocer su URL, ni pedirle permiso.
 */
public class Consumidor implements Runnable {

    private final String nombre;
    private final String tipoEvento;
    private final Consumer<String[]> accion;
    private int posicion = 0;

    public Consumidor(String nombre, String tipoEvento, Consumer<String[]> accion) {
        this.nombre = nombre;
        this.tipoEvento = tipoEvento;
        this.accion = accion;
    }

    public void arrancar() {
        Thread hilo = new Thread(this);
        hilo.setDaemon(true);
        hilo.start();
        System.out.println("[" + nombre + "] escuchando eventos de tipo " + tipoEvento);
    }

    @Override
    public void run() {
        while (true) {
            List<String> nuevos = Bus.desde(posicion);
            posicion += nuevos.size();
            for (String linea : nuevos) {
                String[] campos = linea.split(";");
                if (campos[0].equals(tipoEvento)) accion.accept(campos);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
