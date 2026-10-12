package Exercicio1;

import java.util.Scanner;

/*1. (2,0 pontos) Faça um programa console em Java que utilize os seguintes conceitos de
programação orientada a objetos: classes, objetos, atributos e métodos. A aplicação deve
possuir as seguintes classes:
• Inicio – Classe que contém o método Main. Deve ser criada uma instância Produto que
chamará os métodos get e set da classe Produto. Use os métodos set para atribuir os
valores informados pelo usuário aos atributos da classe Produto, e os métodos get para
apresentar seus novos valores ao usuário.*/

public class Inicio {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		Produto p = new Produto(0, null, 0, false);
		
		int codigo = p.getcodigo();
		String descricao = p.getdescricao();
		float preco = p.getpreco();
		boolean ativo = p.getativo();
		
		System.out.println("Insira o código (com até 9 caracteres):\n");
		codigo = sc.nextInt();
		
		System.out.println("Insira a descrição/nome do produto:\n");
		descricao = sc.next();
		
		System.out.println("Insira o preço do produto:\n");
		preco = sc.nextFloat();
		
		System.out.println("Insira o estado do produto (true ou false):\n");
		ativo = sc.nextBoolean();
		
		p.setcodigo(codigo);
		p.setdescricao(descricao);
		p.setpreco(preco);
		p.setativo(ativo);
	}
}
