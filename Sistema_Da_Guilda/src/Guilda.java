import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Guilda {
    private Set<String> cpfsRegistrados;
    private Map<Integer, Cacador> mapaCacadores;

    public Guilda() {
        cpfsRegistrados = new HashSet<>();
        mapaCacadores = new HashMap<>();
    }

    public <T extends Cacador> void registrarCacador(T cacador, Integer idRegistro, String cpf) {
        if (cpfsRegistrados.add(cpf)) {
            mapaCacadores.put(idRegistro, cacador);
            System.out.println("Registro aceito: " + cacador.getNome() + " ingressou na guilda.");
        } else {
            System.out.println("Operação Negada: O CPF " + cpf + " já possui cadastro");
        }
    }
    public Map<Integer,Cacador> getMapaCacadores() {
        return mapaCacadores;
    }
}
