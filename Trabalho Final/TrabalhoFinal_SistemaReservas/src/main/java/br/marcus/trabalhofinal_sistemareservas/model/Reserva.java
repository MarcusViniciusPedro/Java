package br.marcus.trabalhofinal_sistemareservas.model;

public class Reserva {
    private int id;
    private String dataReserva;
    private String tipoEvento;
    private int prefMesa;
    private int qtdPessoa;
    private String horaReserva;
    private String observacao;

    public Reserva(String dataReserva, String tipoEvento, int prefMesa, int qtdPessoa, String horaReserva, String observacao) {
        this.dataReserva = dataReserva;
        this.tipoEvento = tipoEvento;
        this.prefMesa = prefMesa;
        this.qtdPessoa = qtdPessoa;
        this.horaReserva = horaReserva;
        this.observacao = observacao;
    }

    public Reserva() {

    }

    public int getId() {
        return id;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public int getPrefMesa() {
        return prefMesa;
    }

    public int getQtdPessoa() {
        return qtdPessoa;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public void setPrefMesa(int prefMesa) {
        this.prefMesa = prefMesa;
    }

    public void setQtdPessoa(int qtdPessoa) {
        this.qtdPessoa = qtdPessoa;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}