package pe.ticketpe.controlador;

import pe.ticketpe.servicio.AccesoServicio;

public class AccesoControlador {

    private final AccesoServicio accesoServicio = new AccesoServicio();

    public void validar(String entradaId) {
        System.out.println("Acceso " + entradaId + ": " + accesoServicio.validar(entradaId));
    }
}
