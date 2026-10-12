import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    // 6. MÉTODO GENÉRICO COM BOUNDED WILDCARD LIMITADO
    // Aceita qualquer lista desde que seja de Naves ou filhas de Nave
    public static <T extends Nave> void registrarNaFrota(List<T> frota, T nave) {
        frota.add(nave);
        System.out.println(nave.nome + " registrada com sucesso.");
    }

    public static void main(String[] args) {
        // 7. COLLECTIONS E GENERICS (List e Map)
        // Programando para interfaces (List) e usando o ArrayList como implementação
        List<Nave> listaFrota = new ArrayList<>();
        Map<Integer, Nave> mapaFrota = new HashMap<>();

        Piloto p1 = new Piloto("Luke");
        NaveCombate n1 = new NaveCombate(" x-wing ", 101, p1, 100);
        NaveCombate n2 = new NaveCombate("tie fighter", 202, new Piloto("Vader"), 95);

        // Adicionando na Lista usando o método genérico
        registrarNaFrota(listaFrota, n1);
        registrarNaFrota(listaFrota, n2);

        // Adicionando no Map (Chave = ID, Valor = Objeto Nave)
        mapaFrota.put(n1.getIdRegistro(), n1);
        mapaFrota.put(n2.getIdRegistro(), n2);

        System.out.println("\n--- Iniciando Procedimentos ---");

        // Iterando a coleção com for-each genérico
        for (Nave nave : listaFrota) {
            // 8. TRY-CATCH-FINALLY (Tratamento de Exceções)
            try {
                nave.realizarCheckup();
                nave.viajar();

            } catch (NaveInoperanteException e) {
                // Catch específico primeiro
                System.out.println("ALERTA VERMELHO: " + e.getMessage());
            } catch (Exception e) {
                // Catch genérico (boa prática deixar por último)
                System.out.println("Erro inesperado no sistema: " + e.getMessage());
            } finally {
                System.out.println("Procedimento finalizado para ID: " + nave.getIdRegistro() + "\n");
            }
        }

        // Exemplo de uso de busca quase instantânea do Map (HashMap)
        System.out.println("Buscando nave pelo ID 101: " + mapaFrota.get(101).nome);
    }
}