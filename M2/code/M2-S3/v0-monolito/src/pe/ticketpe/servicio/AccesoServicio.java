package pe.ticketpe.servicio;

import pe.ticketpe.modelo.Entrada;
import pe.ticketpe.repositorio.EntradaRepositorio;

public class AccesoServicio {

    private final EntradaRepositorio entradaRepositorio = new EntradaRepositorio();

    public String validar(String entradaId) {
        Entrada entrada = entradaRepositorio.buscar(entradaId);
        if (entrada == null) return "RECHAZADO (no existe)";
        if (entrada.usada) return "RECHAZADO (ya usada)";
        entrada.usada = true;
        return "VALIDO";
    }
}
