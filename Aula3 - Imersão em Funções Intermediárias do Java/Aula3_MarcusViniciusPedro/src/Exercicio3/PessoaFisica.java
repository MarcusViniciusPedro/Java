package Exercicio3;

public class PessoaFisica extends Cliente { //Para que a pessoa física e jurídica estejam atreladas diretamente a class cliente
	
	private String nome;
	private String cpf;
	
	public String getnome() {
		return nome;
	}
	public String getcpf() {
		return cpf;
	}
	
	public void setnome (String nome) {
			this.nome = nome;
	}
	public void setcpf (String cpf) {
		this.cpf = cpf;
	}
}