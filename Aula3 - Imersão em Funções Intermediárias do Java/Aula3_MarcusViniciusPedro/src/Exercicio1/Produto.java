package Exercicio1;

/*1. (2,0 pontos) Faça um programa console em Java que utilize os seguintes conceitos de
programação orientada a objetos: classes, objetos, atributos e métodos. A aplicação deve
possuir as seguintes classes:
• Produto – Classe que contém os atributos privados: codigo (int), descricao (String),
preco (float) e ativo (boolean), além dos métodos públicos get e set. Os métodos get
devem retornar os respectivos valores dos atributos da classe Produto. Os métodos set
devem atribuir os valores recebidos como argumento aos respectivos atributos da
classe. Inclua um construtor na classe Produto, para inicializar seus atributos.*/

public class Produto {
	private int codigo;
	private String descricao;
	private float preco;
	private boolean ativo;
	
	public Produto(int codigo, String descricao, float preco, boolean ativo) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.ativo = ativo;
	}
	
	public int getcodigo() {
		return codigo;
	}
	public String getdescricao() {
		return descricao;
	}
	public float getpreco() {
		return preco;
	}
	public boolean getativo() {
		return ativo;
	}
	
	public void setcodigo (int codigo) {
		if (codigo <= 999999999) {
			this.codigo = codigo;
			System.out.println("O novo código é:\n" + this.codigo);
		} else
			System.out.println("O código excede o limite de caracteres!");
	}
	public void setdescricao (String descricao) {
		this.descricao = descricao;
		System.out.println("A descrição do produto é:\n" + this.descricao);
	}
	public void setpreco(float preco) {
		this.preco = preco;
		System.out.println("O preço é:\n" + this.preco);
	}
	public void setativo (boolean ativo) {
		this.ativo = ativo;
		System.out.println("O estado da operação é:\n" + this.ativo);
	}
}
