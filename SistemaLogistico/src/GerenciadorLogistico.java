import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorLogistico {
    private List<Pacote> bancoPacotes; // Para guardar os dados dos cadastros
    private Scanner sc; // Escaneando as entradas

    // Função que verifica a situação do banco
    public GerenciadorLogistico(Scanner scan) {
        this.bancoPacotes = new ArrayList<>();
        this.sc = scan;
    }

    // Sistema de cadastramento
    public void cadastrarPacote() {
        limparTerminal();

        try {
            System.out.println("|--------------------------------------|");
            System.out.println("                CADASTRO                ");
            System.out.println("|--------------------------------------|\n\n");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();

            Cliente cliente = new Cliente(nome, cpf);

            System.out.println("Cidades: 0-SP, 1-RJ, 2-BH, 3-PR, 4-RS, 5-BA, 6-DF, 7-CE, 8-AM, 9-PE");
            System.out.print("Código Origem (0 a 9): ");
            int origem = sc.nextInt();
            System.out.print("Código Destino (0 a 9): ");
            int destino = sc.nextInt();

            System.out.print("Peso do pacote (kg): ");
            double peso = sc.nextDouble();

            System.out.print("Tipo (1 - Padrão, 2 - Expresso): ");
            int tipo = sc.nextInt();
            sc.nextLine();

            Pacote pacote;
            if (tipo == 2) { // Para definir em que classe será classifico os dados cadastrados
                pacote = new PacoteExpresso(cliente, origem, destino, peso);
            }
            else {
                pacote = new PacotePadrao(cliente, origem, destino, peso);
            }

            bancoPacotes.add(pacote);
            System.out.println("\nPacote cadastrado seu ID é " + pacote.getId());
        }
        catch (Exception e) {
            System.out.println("\nOcorreu um erro no preenchimento dos dados! Tente novamente.");
            sc.nextLine();
        }
        pausar();
    }

    // Permite uma verificação básica de todos os pacotes que estão salvos no sistema
    public void listarPacotes() {
        limparTerminal();

        System.out.println("|--------------------------------------|");
        System.out.println("                LISTAGEM                ");
        System.out.println("|--------------------------------------|\n\n");

        if (bancoPacotes.isEmpty()) { // Verifica se tem algum dado salvo
            System.out.println("Nenhum pacote foi registrado até o momento no sistema.");
        }
        else {

            for (Pacote p : bancoPacotes) { // Pega os dados salvos na lista

                try { // Calcula o frete
                    double valor = p.calcularFrete();
                    System.out.printf("%s | Frete: R$ %.2f\n", p.toString(), valor);
                }
                catch (RotaInvalidaException e) { // Caso ocorra algum erro na tentativa de calculo ele retorna a falha
                    System.out.printf("%s | Frete: (Erro: %s)\n", p.toString(), e.getMessage());
                }
            }
        }
        pausar();
    }

    // Permite ver todas as informações detalhadas de um cadastro registrado
    public void verDetalhesPacote() {
        limparTerminal();

        System.out.println("|--------------------------------------|");
        System.out.println("           PACOTES DETALHADOS           ");
        System.out.println("|--------------------------------------|\n\n");
        System.out.print("Digite o ID do pacote que deseja verificar: ");

        try {
            int idBusca = sc.nextInt();
            sc.nextLine();

            Pacote p = buscarPorId(idBusca); // Chama a função para identificar o ID correspondente (função mas a frente no código)

            if (p != null) {
                System.out.println("|--------------------------------------|");
                System.out.println("        INFORMAÇÕES DETALHADAS          ");
                System.out.println("|--------------------------------------|\n\n");
                System.out.println("ID           : " + p.getId());
                System.out.println("Cliente      : " + p.getCliente().getNome() + " (CPF: " + p.getCliente().getCpf() + ")");
                System.out.println("Origem       : " + obterNomeCidade(p.getOrigem()));
                System.out.println("Destino      : " + obterNomeCidade(p.getDestino()));
                System.out.println("Peso         : " + p.getPeso() + " kg");

                // Pegando a distância diretamente da matriz estática da classe abstrata (Pacote)
                int distancia = Pacote.Matriz_Distancias[p.getOrigem()][p.getDestino()];
                System.out.println("Distância    : " + distancia + " km");

                // Verifica o tipo com 'instanceof'
                String tipo = (p instanceof PacoteExpresso) ? "Expresso" : "Padrão";
                System.out.println("Tipo de Envio: " + tipo);

                // Polimorfismo para exibir o frete final (já com descontos aplicados)
                try {
                    System.out.printf("Custo Frete  : R$ %.2f\n", p.calcularFrete());
                }
                catch (RotaInvalidaException e) {
                    System.out.println("Custo Frete  : Rota Inválida");
                }
            }
            else {
                System.out.println("Pacote com ID " + idBusca + " não encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("Formato numérico inválido.");
            sc.nextLine();
        }
        pausar();
    }

    public void atualizarPacote() {
        limparTerminal();

        System.out.println("|--------------------------------------|");
        System.out.println("          ATUALIZAÇÃO DE DADOS          ");
        System.out.println("|--------------------------------------|\n\n");
        System.out.print("Digite o ID do pacote a ser atualizado: ");

        try {
            int idBusca = sc.nextInt();
            sc.nextLine();

            Pacote p = buscarPorId(idBusca);

            if (p != null) {
                System.out.println("\nPacote encontrado! Insira os novos dados:");

                System.out.print("Novo Nome: ");
                String novoNome = sc.nextLine();

                System.out.print("Novo CPF: ");
                String novoCpf = sc.nextLine();

                System.out.println("\nCidades: 0-SP, 1-RJ, 2-BH, 3-PR, 4-RS, 5-BA, 6-DF, 7-CE, 8-AM, 9-PE");
                System.out.print("Nova Origem (0 a 9): ");
                int novaOrigem = sc.nextInt();

                System.out.print("Novo Destino (0 a 9): ");
                int novoDestino = sc.nextInt();

                System.out.print("Novo Peso (kg): ");
                double novoPeso = sc.nextDouble();
                sc.nextLine();

                // Aplicando as atualizações através do encapsulamento
                p.getCliente().setNome(novoNome);
                p.getCliente().setCpf(novoCpf);
                p.setOrigem(novaOrigem);
                p.setDestino(novoDestino);
                p.setPeso(novoPeso);

                System.out.println("\nTodos os dados do pacote " + idBusca + " foram atualizados.");
            }
            else {
                System.out.println("Pacote com ID " + idBusca + " não encontrado no sistema.");
            }
        }
        catch (Exception e) {
            System.out.println("Formato inválido inserido durante a atualização.");
            sc.nextLine();
        }
        pausar();
    }

    public void excluirPacote() {
        limparTerminal();

        System.out.print("Digite o ID do pacote a excluir: ");

        try {
            int idBusca = sc.nextInt();
            Pacote p = buscarPorId(idBusca); // Busca pelo ID

            if (p != null) {
                bancoPacotes.remove(p); // Encontrando os dados referentes aquele ID, ele remove
                System.out.println("Pacote removido com sucesso!");
            }
            else {
                System.out.println("Pacote com ID " + idBusca + " não encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("Formato numérico inválido! Digite um ID válido.");
            sc.nextLine();
        }
        pausar();
    }

    // Função de auxílio para realizar a busca pelo ID quando necessário
    private Pacote buscarPorId(int id) {
        for (Pacote p : bancoPacotes) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // Traduz os códigos da matriz para o nome das cidades e a sigla de seus estados
    private String obterNomeCidade(int codigo) {
        switch (codigo) {
            case 0: return "São Paulo (SP)";
            case 1: return "Rio de Janeiro (RJ)";
            case 2: return "Belo Horizonte (BH)";
            case 3: return "Curitiba (PR)";
            case 4: return "Porto Alegre (BA)";
            case 5: return "Salvador (BA)";
            case 6: return "Brasília (DF)";
            case 7: return "Fortaleza (CE)";
            case 8: return "Manaus (AM)";
            case 9: return "Recife (PE)";
            default: return "Rota Desconhecida";
        }
    }

    // Tentando simular uma GUI, pensei em colocar um sistema para limpar o terminal com se estivesse atualizando a tela
    public void limparTerminal() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    // Espera que seja pressionado o ENTER
    public void pausar() {
        System.out.println("\nPressione ENTER para continuar");
        sc.nextLine();
    }
}