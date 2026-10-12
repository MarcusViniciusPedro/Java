import java.util.*;

/* Requisitos do Sistema
1. Mapeamento do Laboratório: O laboratório deve ser representado por uma grade tabular de
10x10 (Matriz). Cada célula deve armazenar uma String indicando o estado do setor: "Vazio",
"Ocupado"ou "Contaminado".
2. A Entidade Robô:
• Não deve ser possível instanciar um objeto genérico do tipo "Robô"(Classe Abstrata).
• Todo robô possui nome, identificador único e um nível de energia (Atributos com Encapsulamento).
• Todo robô deve possuir um Módulo de Energia acoplado (Composição), que é uma classe
à parte responsável por gerir o consumo.
• Todos os robôs devem ser capazes de reportar sua localização no formato texto (Interface/Contrato).
3. Especialização da Frota (Herança e Polimorfismo):
• Robô Transportador: Focado em movimentar carga. Ao se mover, consome o triplo da
energia padrão.
• Robô Coletor: Antes de cada movimento, deve executar obrigatoriamente um protocolo
de esterilização das pinças (Sobreposição de Método utilizando super ).
4. Regras de Negócio e Segurança:
• Deve existir uma constante acessível globalmente para a "Temperatura de Segurança"do
laboratório (Atributo Estático e Final).
• O sistema deve possuir uma classe utilitária com um método para formatar os códigos das
amostras: removendo espaços e forçando letras minúsculas (Método Estático e Manipulação
de Strings).
• Falhas: Se um robô tentar uma ação sem energia suficiente, o sistema deve interromper o
fluxo e disparar um alerta crítico (Tratamento de Exceção Personalizada).
• Navegação: O comando de movimentação deve aceitar tanto apenas a coordenada final
quanto a coordenada final acompanhada de uma velocidade específica (Sobrecarga de Métodos).
5. Gestão de Dados (Coleções Genéricas):
• Manifesto de Amostras: Um robô não pode coletar a mesma amostra ID duas vezes (Uso
de Set).
1
IFSP - Campus São Paulo Bacharelado em Sistemas de Informação Linguagem de Programação
• Estoque do Hub: O sistema deve mapear o nome da substância à sua quantidade em
miligramas (Uso de Map).
• Monitoramento: Todos os robôs ativos devem ser gerenciados em uma estrutura única
para processamento em lote (Uso de List).
Desafio de Implementação
Crie uma classe principal (Main) que simule o ciclo de vida do BioHub:
a) Inicialize a matriz do laboratório usando laços de repetição.
b) Instancie robôs dos dois tipos, cada um com seu módulo de energia.
c) Realize tentativas de coleta de amostras forçando IDs duplicados para testar a restrição da
coleção.
d) Percorra a lista de robôs e execute comandos de movimentação, utilizando blocos try-catch
para capturar possíveis falhas de energia sem travar o sistema.
Boa sorte, Desenvolvedor! O sucesso da pesquisa depende do seu código. */

public class Main {
    public static void main(String[] args) {
        // a) Matriz do Laboratório [cite: 3, 4, 20]
        String[][] grade = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grade[i][j] = "Vazio";
            }
        }

        // b) Instanciar robôs [cite: 21]
        List<Robo> frota = new ArrayList<>(); // List para processamento em lote [cite: 19]
        frota.add(new RoboTransportador("Atlas", "T-01", 100));
        frota.add(new RoboColetor("Wall-E", "C-01", 15)); // Pouca energia propositalmente

        // c) Testar restrição de Set (Amostras) [cite: 16, 22]
        Set<String> manifesto = new HashSet<>();
        String amostra1 = BioHubUtils.formatarCodigo("  BIO-123  ");
        String amostra2 = BioHubUtils.formatarCodigo("bio-123");

        manifesto.add(amostra1);
        if (!manifesto.add(amostra2)) {
            System.out.println("Erro: ID de amostra duplicado (" + amostra2 + ")!");
        }

        // Estoque do Hub (Map) [cite: 18]
        Map<String, Integer> estoque = new HashMap<>();
        estoque.put("Soro Alfa", 500);

        // d) Percorrer frota e mover [cite: 23]
        System.out.println("Iniciando movimentação da frota:");
        for (Robo r : frota) {
            try {
                r.mover(5, 5);
                System.out.println(r.reportarLocalizacao() + " - Sucesso!");
            } catch (AlertaCriticoEnergiaException e) {
                System.err.println("Falha no robô: " + e.getMessage());
            }
        }

        System.out.println("Temperatura de Segurança: " + BioHubUtils.TEMPERATURA_SEGURANCA + "°C");
    }
}