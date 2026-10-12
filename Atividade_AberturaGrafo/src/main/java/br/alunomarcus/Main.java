package br.alunomarcus;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        try {
            File arquivo = new File("grafo.txt"); // Abre o arquivo que se encontra na pasta do projeto
            Scanner scanner = new Scanner(arquivo);

            int nVertices = scanner.nextInt(); // Lê as linhas e ignora os dados após "//"
            int nArestas = scanner.nextInt(); // Lê as linhas e ignora os dados após "//"
            scanner.nextLine();

            int[][] matriz = new int[nVertices][nVertices]; // Para criar a matriz visual

            // Looping para ler cada uma das arestas
            for (int i = 0; i < nArestas; i++) {
                // Pula linhas vazias ou comentários antes de tentar ler números
                while (scanner.hasNext() && !scanner.hasNextInt()) {
                    scanner.nextLine();
                }

                if (scanner.hasNextInt()) {
                    int u = scanner.nextInt(); // Coletará os dados de origem
                    int v = scanner.nextInt(); // Coletará os dados de destino
                    int w = scanner.nextInt(); // Coletará os dados de peso

                    // Registra a conexão em ambos os sentidos aplicados
                    matriz[u][v] = w;
                    matriz[v][u] = w;

                    // Pula o comentário que estiver no final desta linha
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }
                }
            }

            // Impressão da matriz
            System.out.println("Matriz de Adjacência (" + nVertices + "x" + nVertices + "):");
            for (int i = 0; i < nVertices; i++) {
                for (int j = 0; j < nVertices; j++) {
                    System.out.print(matriz[i][j] + "\t");
                }
                System.out.println();
            }
            scanner.close();

        } catch (FileNotFoundException e) { // Variável para o caso do arquivo não ser encontrado
            System.out.println("Erro: Arquivo grafo.txt não encontrado!");
        }
    }
}