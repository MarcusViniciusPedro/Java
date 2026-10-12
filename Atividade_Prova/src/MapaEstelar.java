public class MapaEstelar {
    private char[][] grid;
    private static final int TAMANHO = 5;

    public MapaEstelar() {
        grid = new char[TAMANHO][TAMANHO];
        // Inicializa o mapa com espaço vazio ('.')
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grid[i][j] = '.';
            }
        }
        // Adicionando alguns obstáculos (Asteroides = 'A')
        grid[1][2] = 'A';
        grid[3][4] = 'A';
        grid[4][1] = 'A';
    }

    public void exibirMapa() {
        System.out.println("--- MAPA ESTELAR ---");
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    public void moverNave(Nave nave, int destX, int destY) {
        System.out.println("\nTentando mover nave " + nave.getNome() + " de (" + nave.getX() + "," + nave.getY() + ") para (" + destX + "," + destY + ")");

        // 1. Verifica limites do mapa
        if (destX < 0 || destX >= TAMANHO || destY < 0 || destY >= TAMANHO) {
            System.out.println("ERRO: Destino fora dos limites do mapa estelar!");
            return;
        }

        // 2. Verifica se o destino tem interrupções
        if (grid[destX][destY] != '.') {
            System.out.println("ERRO: Colisão iminente! Existe um obstáculo ('" + grid[destX][destY] + "') nas coordenadas (" + destX + ", " + destY + ").");
            return;
        }

        // 3. Calcula distância e tenta viajar
        double distancia = Utilitarios.calcularDistancia(nave.getX(), nave.getY(), destX, destY);
        try {
            nave.viajar(distancia);

            // Se não lançou exception, a viagem ocorreu com sucesso.
            grid[nave.getX()][nave.getY()] = '.'; // Limpa a posição antiga
            nave.setPosicao(destX, destY);
            grid[destX][destY] = 'N'; // Marca a nave no mapa

            System.out.println("SUCESSO: A nave chegou ao destino! " + nave.obterPosicaoAtual());
        } catch (CombustivelInsuficienteException e) {
            System.out.println("FALHA CRÍTICA: " + e.getMessage());
        }
    }
}