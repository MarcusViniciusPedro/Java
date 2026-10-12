package Atividade1;

/* Crie uma classe Main para simular um dia de trabalho.
1. Instancie 1 Caminhão (ex: Tanque de 300L, consumo base de 6 km/l,
carregando 5 toneladas) e 1 Motocicleta (ex: Tanque de 15L, consumo
base de 35 km/l, 250 cilindradas).
2. Crie um array (ou lista) de distâncias representando as entregas do dia:
[50.0, 120.0, 30.0, 90.0, 40.0].
3. A Lógica de Simulação: Utilizando um laço de repetição (for ou while),
tente fazer com que os dois veículos percorram todas as rotas em
sequência.
4. Regra de Parada: Durante o laço, verifique o nível do tanque após cada
viagem. Se o combustível de um veículo cair para menos de 15% da
capacidade máxima, chame o método abastecer para encher o tanque
completamente antes da próxima viagem.
5. Saída esperada no Console: O programa deve imprimir um relatório
dinâmico, mostrando cada viagem, se ela foi bem-sucedida, o
combustível restante de cada veículo após a viagem e quantas vezes
cada veículo precisou parar para abastecer ao longo do dia. */

public class Main {
    public static void main(String[] args) {

        Caminhao caminhao = new Caminhao("ABC-1234", 300.0, 6.0, 5.0);
        Motocicleta moto = new Motocicleta("MOTO-999", 15.0, 35.0, 250);

        // 2. Distâncias [cite: 28]
        double[] entregas = {50.0, 120.0, 30.0, 90.0, 40.0};

        // 3. Laço de repetição [cite: 29]
        for (int i = 0; i < entregas.length; i++) {
            double d = entregas[i];
            System.out.println("--- Viagem " + (i + 1) + ": " + d + "km ---");

            executarLogica(caminhao, d);
            executarLogica(moto, d);
        }

        // 5. Relatório Final
        System.out.println("\n=== RELATÓRIO FINAL ===");
        System.out.println("Caminhão: Parou para abastecer " + caminhao.totalAbastecimentos + " vezes.");
        System.out.println("Moto: Parou para abastecer " + moto.totalAbastecimentos + " vezes.");
    }

    private static void executarLogica(Veiculo v, double d) {
        if (v.realizarViagem(d)) {
            System.out.printf("Sucesso! Tanque de %s: %.2fL\n", v.placa, v.combustivelAtual);
        }

        // 4. Regra de Parada: Menos de 15% [cite: 30, 31]
        if (v.getPercentualCombustivel() < 15.0) {
            System.out.println("   > Abastecendo " + v.placa + " (Nível Crítico)");
            v.abastecer(v.capacidadeTanque); // Enche o tanque
        }
    }
}