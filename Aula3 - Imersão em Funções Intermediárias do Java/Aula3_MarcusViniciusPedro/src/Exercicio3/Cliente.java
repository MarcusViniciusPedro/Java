package Exercicio3;

public class Cliente {

	private int codigo;
	private String endereco;
	private String telefone;
	
	public Cliente() {}

	protected int getcodigo() {
		return codigo;
	}
	protected String getendereco() {
		return endereco;
	}
	protected String gettelefone() {
		return telefone;
	}
	
	protected void setcodigo (int codigo) {
		this.codigo = codigo;
	}
	protected void setendereco (String endereco) {
		this.endereco = endereco;
	}
	protected void settelefone(String telefone) {
		this.telefone = telefone;
	}
}