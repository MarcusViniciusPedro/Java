package Atividades;

import java.util.Calendar;
import java.util.Scanner;

/*3. (3,0 pontos) Faça um programa em Java que leia uma data e, em seguida, apresente ao
usuário as opções a seguir, enquanto ele não digitar a opção 0 (zero). De acordo com o
número da opção informado, o programa deverá efetuar a operação, solicitando as
informações necessárias ao usuário. Ao final de cada operação, a aplicação deve
apresentar a nova data ao usuário.
1) Adicionar dias – Solicitar ao usuário quantos dias devem ser adicionados.
2) Adicionar meses – Solicitar ao usuário quantos meses devem ser adicionados.
3) Adicionar anos – Solicitar ao usuário quantos anos devem ser adicionados.
4) Subtrair dias – Solicitar ao usuário quantos dias devem ser subtraídos.
5) Subtrair meses – Solicitar ao usuário quantos meses devem ser subtraídos.
6) Subtrair anos – Solicitar ao usuário quantos anos devem ser subtraídos*/

public class exercicio3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int cod;
		Calendar calendario = Calendar.getInstance();
		
		do {
			System.out.println("\t-----------------------\n\t\t|MENU|\n\t-----------------------\t");
			System.out.println("\n0. Sair do Programa\n1. Adicionar Dias\n2. Adicionar Meses\n3. Adicionar Anos\n4. Subtrair Dias\n5. Subtrair Meses\n6. Subtrair Anos\n\nEscolha uma opcao: ");
			cod = sc.nextInt();

			switch(cod){
				case 0:
					System.out.println("Finalizado");
					break;
				case 1:
					System.out.println("Insira o Dia desejado a ser adicionado:\n");
					int dia = sc.nextInt();
					calendario.add(Calendar.DAY_OF_MONTH, dia); //permite adicionar dias no calendário
					break;
				case 2: 
					System.out.println("Insira o Mes desejado a ser adicionado:\n");
					int mes = sc.nextInt();
					calendario.add(Calendar.MONTH, mes); //permite adicionar meses
					break;
				case 3: 
					System.out.println("Insira o Dia desejado a ser adicionado:\n");
					int ano = sc.nextInt();
					calendario.add(Calendar.YEAR, ano); //permite adicionar anos
					break;
				case 4:
					System.out.println("Insira o Dia desejado para a subtração:\n");
					int dia1 = sc.nextInt();
					calendario.add(Calendar.DAY_OF_MONTH, -dia1); //subtrai dias com a operação de subtração (-)
					break;
				case 5:
					System.out.println("Insira o Mes desejado para a subtração:\n");
					int mes1 = sc.nextInt();
					calendario.add(Calendar.MONTH, -mes1); //subtrai meses
					break;
				case 6:
					System.out.println("Insira o Dia desejado para a subtração:\n");
					int ano1 = sc.nextInt();
					calendario.add(Calendar.YEAR, -ano1); //subtrai anos
					break;
				default: 
					System.out.println("\nOpcao invalida!\n");	
			} 		
			System.out.println("O calendário está, atualmente:\n\n" + "Dia: " + calendario.get(Calendar.DAY_OF_MONTH) + "\nMês: " + calendario.get(Calendar.MONTH) + "\nAno: " + calendario.get(Calendar.YEAR));
			//A cada modificação feita no calendário, o código apresenta o calendário modificado
	    } while (cod != 0);
	}

}
