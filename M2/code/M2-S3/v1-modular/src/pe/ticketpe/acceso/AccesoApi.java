package pe.ticketpe.acceso;

import pe.ticketpe.acceso.internal.ControlPuerta;

/** Puerta de entrada al modulo acceso. */
public class AccesoApi {

    private final ControlPuerta control = new ControlPuerta();

    public String validar(String entradaId) {
        return control.validar(entradaId);
    }
}
