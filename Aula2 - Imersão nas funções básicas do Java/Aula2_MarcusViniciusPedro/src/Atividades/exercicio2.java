package Atividades;

import java.util.Scanner;

/*2. (4,0 pontos) Faça um programa em Java que execute as operações a seguir no texto
abaixo. Nas operações que alteram o texto, a aplicação deve reapresentá-lo ao usuário
com as modificações realizadas.
a) Recupere/extraia do texto a frase “E tossia com algum custo” e retorne-a ao usuário.
Não modifique o texto. Sugestão: use os métodos substring e indexOf.
b) Substitua as ocorrências de “alienista” por “psiquiatra”.
c) Altere as ocorrências de “Crispim Soares” para letras maiúsculas. Sugestão: use os
métodos replace e toUpperCase (o 2º parâmetro do método replace não pode ser
simplesmente “CRISPIM SOARES”).
d) Retorne se o texto contém as palavras “vila”, “custo” e “receio”.
e) Retorne quantas vezes a palavra “não” aparece no texto. Sugestão: use o método
indexOf.
f) Retorne quantos caracteres o texto possui.
g) Retorne quantos espaços o texto possui. Sugestão: remova todos os espaços do texto,
em seguida veja a diferença de caracteres entre o texto original e o texto sem espaços.*/

public class exercicio2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int cod;
		
		String texto = "Uma ideia traz outra; o boticário imaginou que, uma vez preso o alienista, viriam também buscá-lo\n"
		+ "a ele, na qualidade de cúmplice. Esta ideia foi o melhor dos vesicatórios. Crispim Soares ergueu-se,\n"
		+ "disse que estava bom, que ia sair; e apesar de todos os esforços e protestos da consorte vestiu-se e\n"
		+ "saiu. Os velhos cronistas são unânimes em dizer que a certeza de que o marido ia colocar-se\n"
		+ "nobremente ao lado do alienista consolou grandemente a esposa do boticário; e notam com muita\n"
		+ "perspicácia, o imenso poder moral de uma ilusão; porquanto, o boticário caminhou resolutamente\n"
		+ "ao palácio do governo, não à casa do alienista. Ali chegando, mostrou-se admirado de não ver o\n"
		+ "barbeiro, a quem ia apresentar os seus protestos de adesão, não o tendo feito desde a véspera por\n"
		+ "enfermo. E tossia com algum custo. Os altos funcionários que lhe ouviam esta declaração,\n"
		+ "sabedores da intimidade do boticário com o alienista, compreenderam toda a importância da\n"
		+ "adesão nova e trataram a Crispim Soares com apurado carinho; afirmaram-lhe que o barbeiro não\n"
		+ "tardava; Sua Senhoria tinha ido à Casa Verde, a negócio importante, mas não tardava. Deram-lhe\n"
		+ "cadeira, refrescos, elogios; disseram-lhe que a causa do ilustre Porfírio era a de todos os patriotas;\n"
		+ "ao que o boticário ia repetindo que sim, que nunca pensara outra coisa, que isso mesmo mandaria\n"
		+ "declarar a Sua Majestade.\n";
	
		do {
			System.out.println("\t-----------------------\n\t\t|MENU|\n\t-----------------------\t");
			System.out.println("\n0. Sair do Programa\n1. Recuperação de frase\n2. Substituição de Ocorrência\n3. Alteração de Ocorrência\n4. Retorno de Palavras\n5. Retorno da palavra 'não'\n6. Quantidade de Caracteres\n7. Espaçamentos do Texto\n\nEscolha uma opcao: ");
			cod = sc.nextInt();

			switch(cod){
				case 0:
					System.out.println("\nFinalizado\n");
					break;
				case 1: 
					int frase1 = texto.indexOf("Os altos funcionários"); //indexOf localizando a frase exatamente após a frase deseja para ser apresentada 'E tossia com algum custo.'
					String frasedotexto = texto.substring(783, frase1); //O valor 783 é proposital, pois o indice do caracter que se relaciona com a frase requerida, encontra-se nessa posição
					System.out.println("A frase é: " + frasedotexto);
					break;
				case 2: 
					String novaFrase = texto.replace("alienista", "psiquiatra"); //replace permite modificar uma palavra na string original
					System.out.println("O texto, com a devida mudança ficou:\n" + novaFrase); //para apresentação do texto com essa modificação
					break;
				case 3: 
					String original = "Crispim Soares";
					String maiusculo = original.toUpperCase(); //deixando o nome 'Crispim Soares' em maiusculo
					String palavraMaiuscula = texto.replace("Crispim Soares", maiusculo); //modificando no texto base
					System.out.println("A palavra em letras maiúsculas ficará, dentro do texto:\n" + palavraMaiuscula); //Apresentação do texto com essa nova modificação
					break;
				case 4:
					boolean palavra1 = texto.contains("vila"); //utilização do contains para identificar se essas palavras se encontram no texto 
					boolean palavra2 = texto.contains("custo");
					boolean palavra3 = texto.contains("receio");
					System.out.println("Os respectivos termos pesquisados (definidos com 'true' ou 'false') ficaram:\n\n" + "vila: " + palavra1 + "\n" + "custo: " + palavra2 + "\n" + "receio: " + palavra3);
					break;
				case 5:
					int count = 0;
					int frase2 = texto.indexOf("não");
					while(frase2 != -1) { //impedindo que valores negativos sejam apresentados
						count++; //somando cada vez que o 'não' aparece no texto
						frase2 = texto.indexOf("não", frase2 + "não".length()); //pesquisando o não e usando o length para encontrar cada indice do 'não' para que o código não repita o mesmo 'não' mais de uma vez
					}
					System.out.println("A palavra 'não' aparece " + count + " vezes no texto.");
					break;
				case 6:
					int caracteres = texto.length(); //leitura de quantos caracteres totais o texto possui
					System.out.println("O texto possui " + caracteres + " caracteres.");
					break;
				case 7:
					int tamanhoOriginal = texto.length(); //leitura dos caracteres totais
					int tamanhoSemEspaco = texto.replace(" ", "").length(); //leitura dos caracteres, porém sem os espaçamentos entre eles
					int Resultado = tamanhoOriginal - tamanhoSemEspaco; //subtração do total pelo sem os espaços
					System.out.println("A quantidade de espaços do texto é:\n" + Resultado);
					break;
				default: 
					System.out.println("\nOpcao invalida!\n");	
			} 
	    } while (cod != 0);
	}
}