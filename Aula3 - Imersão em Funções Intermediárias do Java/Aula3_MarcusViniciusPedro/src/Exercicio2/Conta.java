package Exercicio2;

/*2. (3,0 pontos) Faça um programa console em Java que implemente o seguinte
relacionamento entre as classes Conta e Cliente. Use os métodos set e get para receber e
imprimir os dados (não é necessário ler os dados do usuário). Por fim, faça testes para
verificar interdependência entre as duas classes.*/

public class Conta {

	private int idConta, agencia;
	private String numeroConta;
	private Cliente cliente;

	
	public Conta (int idConta, int agencia, String numeroConta, Cliente cliente) {
		this.idConta = idConta;
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.cliente = cliente;
	}
	
	public int getidConta() {
		return idConta;
	}
	public int getagencia() {
		return agencia;
	}
	public String getnumeroConta() {
		return numeroConta;
	}
	public Cliente getcliente() {
		return cliente;
	}
	
	public void setidConta (int idConta) {
			this.idConta = idConta;
	}
	public void setagencia (int agencia) {
		this.agencia = agencia;
	}
	public void setnumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public void setcliente (Cliente cliente) {
		this.cliente = cliente;
	}
}