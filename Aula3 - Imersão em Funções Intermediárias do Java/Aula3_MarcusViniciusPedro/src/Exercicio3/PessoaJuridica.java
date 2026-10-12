package Exercicio3;

public class PessoaJuridica extends Cliente {

	private String razaoSocial;
	private String cnpj;
	
	public String getrazaoSocial() {
		return razaoSocial;
	}
	public String getcnpj() {
		return cnpj;
	}
	
	public void setrazaoSocial (String razaoSocial) {
			this.razaoSocial = razaoSocial;
	}
	public void setcnpj (String cnpj) {
		this.cnpj = cnpj;
	}
}