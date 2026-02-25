package Atividades;

import java.util.Scanner;

/*1. (3,0 pontos) Faça um programa em Java que apresente ao usuário as opções a seguir,
enquanto ele não digitar a opção 0 (zero). De acordo com o número da opção informado, o
programa deverá efetuar a operação, solicitando as informações necessárias ao usuário. Ao
final de cada operação, a aplicação deve apresentar o resultado ao usuário.
1) Arredondar número – Solicitar ao usuário o valor decimal.
2) Calcular raiz quadrada – Solicitar ao usuário um número maior ou igual a zero.
3) Calcular a potência – Solicitar ao usuário a base e o expoente.*/

public class exercicio1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
	    int cod;
	
		do {
			System.out.println("\t-----------------------\n\t\t|MENU|\n\t-----------------------\t");
			System.out.println("\n0. Sair do Programa\n1. Arredondar Numero\n2. Calcular Raiz Quadrada\n3. Calcular a Potencia\n\nEscolha uma opcao: ");
			cod = sc.nextInt();

			switch(cod){
				case 0:
					System.out.println("Finalizado");
					break;
				case 1: 
					System.out.println("Insira o valor em decimal:\n");
					float numero1 = sc.nextFloat();
					System.out.println("O resultado e:\n" + Math.round(numero1)); //round arredonda o valor para o mais próximo
					break;
				case 2: 
					System.out.println("Insira o valor a ser Radiciado:\n");
					double numero2 = sc.nextDouble();
					System.out.println("O resultado e:\n" + Math.sqrt(numero2)); //sqrt faz o processo de raiz quadrada
					break;
				case 3: 
					System.out.println("Insira o valor da base:\n"); //valor que será multiplicado por ele mesmo 'n' vezes
					double numero3 = sc.nextDouble();
					System.out.println("Insira o valor do expoente:\n"); //valor que decidirá quantas vezes a base será multiplicada
					double exp = sc.nextDouble();
					System.out.println("O resultado e:\n" + Math.pow(numero3, exp)); //pow realiza a potencialização sobre bases e expoentes
					break;
				default: 
					System.out.println("\nOpcao invalida!\n");	
			} 
	    } while (cod != 0);
	}
}