import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean[] statusPoltrona = new boolean[10];
        int escolha = 0;

        System.out.println("Bem vindo ao Cinema Cinemático!");
        System.out.println("Aqui você poderá adquirir um ingresso para uma de nossas 10 poltronas VIPs");

        while (true) {
            System.out.println("Indique qual poltrona deseja adquirir: ");

            try {
                escolha = sc.nextInt();

                if (escolha == -1){
                    System.out.println("Saindo do sistema. Agradeçemos sua preferência!");
                    break;
                }

                if (statusPoltrona[escolha]) {
                    throw new IllegalArgumentException("A poltrona " + escolha + " já se encontrar ocupada");
                }
                statusPoltrona[escolha] = true;
                System.out.println("A poltrona " + escolha + " foi reservada com sucesso!");
            }
            catch (InputMismatchException e) {
                System.out.println("Erro! Digite apenas números inteiros!");
                sc.nextLine();
            }
            catch (IllegalArgumentException e) {
                System.out.println("Operação inválidada por: " + e.getMessage());
                sc.nextLine();
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro! Essa poltrona não existe. Escolha uma que encontre-se no intervalo de 0 a 9");
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage() + " tente novamente.");
                sc.nextLine();
            }
            finally {
                System.out.println("Tentativa de reserva processada!");
            }
        }

        sc.close();
        System.out.println("Programa encerrado com sucesso!");
    }
}