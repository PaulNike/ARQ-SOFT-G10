package pe.ticketpe.contrato;

import java.util.LinkedHashMap;
import java.util.Map;

/** Formato de mensaje compartido: clave=valor;clave=valor. El mismo de la sesion anterior. */
public class Mensaje {

    private final Map<String, String> campos = new LinkedHashMap<>();

    public static Mensaje leer(String cuerpo) {
        Mensaje m = new Mensaje();
        for (String par : cuerpo.split(";")) {
            String[] kv = par.split("=", 2);
            if (kv.length == 2) m.campos.put(kv[0], kv[1]);
        }
        return m;
    }

    public String get(String clave) { return campos.get(clave); }

    public boolean tiene(String clave) { return campos.containsKey(clave); }
}
