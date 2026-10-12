import java.util.InputMismatchException;
import java.util.Scanner;

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    GerenciadorLogistico gerenciador = new GerenciadorLogistico(sc);

    int opcao = -1;

    // Menu
    while (opcao != 0) {
        System.out.println("|---------------------------------------|");
        System.out.println("      SISTEMA LOGÍSTICO - MARMAZON       ");
        System.out.println("|---------------------------------------|");
        System.out.println("1. Cadastro um Pacote");
        System.out.println("2. Listar os Pacotes");
        System.out.println("3. Atualizar um Pacote");
        System.out.println("4. Excluir um Pacote");
        System.out.println("5. Detalhes de Pacote");
        System.out.println("0. Finalizar Sistema");
        System.out.print("\nEscolha uma opção: ");

        try {
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    gerenciador.cadastrarPacote();
                    break;
                case 2:
                    gerenciador.listarPacotes();
                    break;
                case 3:
                    gerenciador.atualizarPacote();
                    break;
                case 4:
                    gerenciador.excluirPacote();
                    break;
                case 5:
                    gerenciador.verDetalhesPacote();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    gerenciador.pausar();
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números no menu!");
            sc.nextLine();
            gerenciador.pausar();
        }
    }
    sc.close();
}