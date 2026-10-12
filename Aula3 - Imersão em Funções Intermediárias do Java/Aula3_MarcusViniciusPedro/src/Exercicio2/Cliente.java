package Exercicio2;

/*2. (3,0 pontos) Faça um programa console em Java que implemente o seguinte
relacionamento entre as classes Conta e Cliente. Use os métodos set e get para receber e
imprimir os dados (não é necessário ler os dados do usuário). Por fim, faça testes para
verificar interdependência entre as duas classes.*/

public class Cliente {

	private String nome, cpf, endereco, email;

	
	public Cliente (String nome, String cpf, String endereco, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
	}
	
	public String getnome() {
		return nome;
	}
	public String getcpf() {
		return cpf;
	}
	public String getendereco() {
		return endereco;
	}
	public String getemail() {
		return email;
	}
	
	public void setnome (String nome) {
		this.nome = nome;
	}
	public void setcpf (String cpf) {
		this.cpf = cpf;
	}
	public void setendereco(String endereco) {
		this.endereco = endereco;
	}
	public void setemail (String email) {
		this.email = email;
	}

}