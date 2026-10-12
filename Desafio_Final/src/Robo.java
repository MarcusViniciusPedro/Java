public abstract class Robo implements Localizavel {
    private String nome; // Encapsulamento [cite: 6]
    private String id;
    protected ModuloEnergia moduloEnergia; // Composição
    protected int posX = 0, posY = 0;

    public Robo(String nome, String id, double energiaInicial) {
        this.nome = nome;
        this.id = id;
        this.moduloEnergia = new ModuloEnergia(energiaInicial);
    }

    // Sobrecarga de Métodos [cite: 15]
    public void mover(int x, int y) throws AlertaCriticoEnergiaException {
        this.posX = x;
        this.posY = y;
        moduloEnergia.consumir(10); // Consumo padrão
    }

    public void mover(int x, int y, int velocidade) throws AlertaCriticoEnergiaException {
        this.posX = x;
        this.posY = y;
        moduloEnergia.consumir(10 * (velocidade / 2.0));
    }

    @Override
    public String reportarLocalizacao() {
        return "Robô " + nome + " [" + id + "] em (" + posX + "," + posY + ")";
    }

    public String getId() { return id; }
}

// Especialização: Transportador [cite: 9]
class RoboTransportador extends Robo {
    public RoboTransportador(String nome, String id, double energia) {
        super(nome, id, energia);
    }

    @Override
    public void mover(int x, int y) throws AlertaCriticoEnergiaException {
        this.posX = x;
        this.posY = y;
        moduloEnergia.consumir(30); // Triplo da energia padrão [cite: 10]
    }
}

// Especialização: Coletor
class RoboColetor extends Robo {
    public RoboColetor(String nome, String id, double energia) {
        super(nome, id, energia);
    }

    private void esterilizarPinças() {
        System.out.println("Limpando pinças...");
    }

    @Override
    public void mover(int x, int y) throws AlertaCriticoEnergiaException {
        esterilizarPinças(); // Protocolo obrigatório
        super.mover(x, y); // Uso do super para lógica base
    }
}