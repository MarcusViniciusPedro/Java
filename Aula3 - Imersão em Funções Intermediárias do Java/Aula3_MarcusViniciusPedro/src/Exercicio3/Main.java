package Exercicio3;

import java.util.Scanner;

/*3. (5,0 pontos) Faça um programa em Java que implemente o relacionamento a seguir entre
as classes Cliente, PessoaFisica e PessoaJuridica.
O programa deve apresentar ao usuário as opções a seguir, enquanto ele não digitar a
opção 0 (zero). De acordo com o número da opção informada, o programa deverá efetuar a
operação, solicitando as informações ao usuário quando necessário. Os dados dos clientes
devem ser armazenados em dois arrays de objetos, um do tipo PessoaFisica[10], que
armazenará objetos contendo código, endereço, telefone, nome, CPF dos clientes, e um
array do tipo PessoaJuridica[10], que armazenará objetos contendo código, endereço,
telefone, razaoSocial e CNPJ dos clientes. Seguem as opções:
1) Inserir cliente – Primeiramente, pergunte ao usuário se o cliente é pessoa física (PF) ou
jurídica (PJ). Para pessoa física, solicite o nome, o CPF, o endereço e o telefone. Para
pessoa jurídica, solicite a razão social, o CNPJ, o endereço e o telefone. O código do
cliente não deve ser informado pelo usuário, deve ser gerado automaticamente a partir
do número 1. Além disso, a sequência de códigos deve ser única para pessoas físicas e
jurídicas, ou seja, não devem existir clientes PF e PJ com um mesmo código. Informe ao
usuário se houve sucesso ou não na inserção.
2) Remover cliente – Primeiramente, pergunte ao usuário se o cliente é pessoa física (PF)
ou jurídica (PJ). Solicite então o código do cliente. Na remoção, deve ser atribuído o
valor “null” à posição do array relacionada ao cliente informado. Informe ao usuário se
houve sucesso ou não na remoção (por exemplo: código inexistente).
3) Consultar clientes – A consulta deve apresentar ao usuário os dados de todos os
clientes cadastrados, separados em pessoas físicas e pessoas jurídicas.
A aplicação deve possuir as seguintes classes:
• Cliente – Classe que contém os atributos privados int codigo, String endereco e String
telefone, além dos métodos protegidos get e set necessários.
• PessoaFisica – Classe filha da classe Cliente. Contém os atributos privados String nome e
String cpf, além dos métodos públicos get e set necessários.
• PessoaJuridica – Classe filha da classe Cliente. Contém os atributos privados String
razaoSocial e String cnpj, além dos métodos públicos get e set necessários.
• Inicio – Classe que contém o método main, onde são instanciadas as classes
PessoaFisica e PessoaJuridica. Na opção 1, devem ser usados os métodos set para criar
um objeto contendo os dados da pessoa física ou da pessoa jurídica, dependendo do
tipo de cliente informado. Este objeto deve ser inserido então no array de pessoas
físicas ou pessoas jurídicas. Por fim, o código deve ser incrementado para inserção do
próximo cliente. Na opção 2, deve-se usar o método getCodigo para percorrer os arrays
de pessoas físicas ou pessoas jurídicas e buscar o cliente que tem o código informado
para remoção. Na opção 3, devem ser chamados os métodos get para recuperar os
dados de cada objeto armazenado nos arrays e apresentá-los ao usuário.*/

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		PessoaFisica[] LisPF = new PessoaFisica[10];
        PessoaJuridica[] LisPJ = new PessoaJuridica[10];
	    int cod, prox = 1;
	
		do {
			System.out.println("\t-----------------------\n\t\t|MENU|\n\t-----------------------\t");
			System.out.println("\n0. Sair do Programa\n1. Inserir Cliente\n2. Remover Cliente\n3. Consultar Cliente\n\nEscolha uma opcao: ");
			cod = sc.nextInt();

			switch(cod){
				case 0:
					System.out.println("Finalizado");
					break;
				case 1: 
					System.out.println("Insira o tipo de Pessoa (1 - PF / 2 - PJ): ");
					int tipo = sc.nextInt();
					sc.nextLine();
				
					if(tipo == 1) {
						PessoaFisica pf = new PessoaFisica();
						pf.setcodigo(prox++); //Para que, a cada novo registro seja salvo um novo código, atrelado, tanto a pessoa física quanto a jurídica, impedindo repetição de valores.
						
						System.out.println("----------|Pessoa Física|----------");
						System.out.println("Insira o nome: ");
						pf.setnome(sc.next());
						System.out.println("Insira o CPF: ");
						pf.setcpf(sc.next());
						System.out.println("Insira o endereço: ");
						pf.setendereco(sc.next());
						System.out.println("Insira o telefone: ");
						pf.settelefone(sc.next());
						
						boolean in = false; //Função para verificar se foi criado com sucesso ou não
						for (int i = 0; i < 10; i++) {
							if (LisPF[i] == null) {
								LisPF[i] = pf;
								in = true;
								break;
							}
						}
						System.out.println(in ? "\nSucesso! O cadastro foi registrado" : "Lista Cheia, remova um cadastro para adicionar outro!");
						break; //Questão que, a partir do valor em boolean 'in' define se foi ou não criado
					} 
					if (tipo == 2) {
						PessoaJuridica pj = new PessoaJuridica();
						pj.setcodigo(prox++);
						
						System.out.println("----------|Pessoa Jurídica|----------");
						System.out.println("Insira a razão social: ");
						pj.setrazaoSocial(sc.next());
						System.out.println("Insira o CNPJ: ");
						pj.setcnpj(sc.next());
						System.out.println("Insira o endereço: ");
						pj.setendereco(sc.next());
						System.out.println("Insira o telefone: ");
						pj.settelefone(sc.next());
						
						boolean in = false;
						for (int i = 0; i < 10; i++) {
							if (LisPJ[i] == null) {
								LisPJ[i] = pj;
								in = true;
								break;
							}
						}
						System.out.println(in ? "\nSucesso! O cadastro foi registrado." : "Lista Cheia, remova um cadastro para adicionar outro!");
						break;
					} 
					else 
						System.out.println("Insira um número válido! (1 - PF / 2 - PJ)");
					    break;
				case 2: 
					System.out.println("Insira a Pessoa (1 - PF / 2 - PJ) a ser excluída: ");
					int exc = sc.nextInt();
					
					if (exc > 2) { //Função que impede a falha do programa ao classificar todos os valores acima de 2 como inválidos
						System.out.println("Insira um número válido! (1 - PF / 2 - PJ)");
						break;
					}
					else {
					System.out.println("Insira o código do cliente a ser deletado: ");
					int excod = sc.nextInt();
					
					boolean remov = false; //Usa-se boolean para verificar se o cadastro existe ou não
					
					if (exc == 1) {
						for (int i = 0; i < 10; i++) { //Função que verifica as posições do array até encontrar aquela requerida pelo usuário
							if (LisPF[i] != null && LisPF[i].getcodigo() == excod) {
								LisPF[i] = null;
								remov = true;
								break;
							}
						}
					}
					if (exc == 2) { //Mesma coisa da anterior, porém para pessoa jurídica
						for (int i = 0; i < 10; i++) {
							if (LisPJ[1] != null && LisPJ[i].getcodigo() == excod) {
								LisPJ = null;
								remov = true;
								break;
							}
						}
					}
					System.out.println(remov ? "O cadastro foi removido com Sucesso" : "O cadastro não foi encontrado. Insira um valor válido!");
					break;
				}
				case 3: 
					System.out.println("----------|Pessoa Física|----------");
					for (PessoaFisica f : LisPF) { //Usado como um substituto ao 'for', para fazer a pesquisa dentro do array e mostrar todos os resultados contidos no mesmo
						if (f != null) {
							System.out.println("Código: " + f.getcodigo());
							System.out.println("Nome: " + f.getnome());
							System.out.println("CPF: " + f.getcpf());
						}
					}
					System.out.println("----------|Pessoa Jurídica|----------");
					for (PessoaJuridica j : LisPJ) {
						if (j != null) {
							System.out.println("Código: " + j.getcodigo());
							System.out.println("Razão Social: " + j.getrazaoSocial());
							System.out.println("CNPJ: " + j.getcnpj());
						}
					}
					break;
				default: 
					System.out.println("\nOpcao invalida!\n");	
			} 
	    } while (cod != 0);
	}
}
