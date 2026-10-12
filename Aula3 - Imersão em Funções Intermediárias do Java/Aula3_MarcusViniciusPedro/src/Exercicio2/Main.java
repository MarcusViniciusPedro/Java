package Exercicio2;

import java.util.Scanner;

/*2. (3,0 pontos) Faça um programa console em Java que implemente o seguinte
relacionamento entre as classes Conta e Cliente. Use os métodos set e get para receber e
imprimir os dados (não é necessário ler os dados do usuário). Por fim, faça testes para
verificar interdependência entre as duas classes.*/

public class Main {

	public static void main(String[] args) {
			Scanner sc = new Scanner (System.in);
			Cliente cl = new Cliente(null, null, null, null);
			Conta co = new Conta(0, 0, null, cl);
			
			System.out.println("--- Cadastro do Cliente ---");
		    System.out.print("Nome: ");
		    cl.setnome(sc.next());
		    System.out.print("CPF: ");
		    cl.setcpf(sc.next());

		    System.out.println("\n--- Cadastro da Conta ---");
		    System.out.print("ID da Conta: ");
		    co.setidConta(sc.nextInt());
		    
		    System.out.println("\n--- Resultado Final ---");
		    System.out.println("O titular da conta " + co.getidConta() + " é: " + co.getcliente().getnome());
	}
}
