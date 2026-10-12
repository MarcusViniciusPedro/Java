public class Main {
    public static final double velocidadeLuz = 300000;
    private static Nave NaveCargueiro;
    private static Nave NavePassageiros;

    public static void main(String[] args) {
        MapaEstelar mapa = new MapaEstelar();

        var c = new NaveCargueiro ("Igor", "123", 10000, new Motor(200, 1000));
        var n = new  NavePassageiros("Dutra", "4151", 46000, new Motor (100, 500));

        // Registrando tripulantes
        c.adicionarTripulante("111.222.333-44");
        n.adicionarTripulante("999.888.777-66");

        // Setando posição inicial das naves no mapa
        c.setPosicao(0, 0);
        n.setPosicao(0, 1);

        mapa.exibirMapa();

        // Cenário 1: Cargueiro tentando ir para uma posição sem combustível suficiente
        // Distância de (0,0) para (4,4) = 5.65. Consumo do cargueiro = 5.65 * 2 = 11.3
        // Combustível do cargueiro = 10 (Vai faltar combustível)
        mapa.moverNave(c, 4, 4);

        // Cenário 2: Nave de Passageiros tentando ir para um asteroide
        // Posição (1,2) é um Asteroide no nosso MapaEstelar
        mapa.moverNave(n, 1, 2);

        // Cenário 3: Nave de Passageiros fazendo uma viagem com sucesso
        mapa.moverNave(n, 2, 2);

        System.out.println("\nMapa Atualizado após movimentações:");
        mapa.exibirMapa();
    }
}