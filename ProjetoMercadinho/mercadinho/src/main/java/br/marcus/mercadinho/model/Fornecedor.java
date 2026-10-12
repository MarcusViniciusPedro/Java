package br.marcus.mercadinho.model;

public class Fornecedor {
    private int id;
    private String Cnpj;
    private String NomeFantasia;
    private String Telefone;

    // Construtor com parâmetros (sem ID, pois é gerado pelo banco)
    public Fornecedor(String cnpj, String nomeFantasia, String telefone) {
        this.Cnpj = cnpj;
        this.NomeFantasia = nomeFantasia;
        this.Telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCnpj(String cnpj) {
        this.Cnpj = cnpj;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.NomeFantasia = nomeFantasia;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }
}