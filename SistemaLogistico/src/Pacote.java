public abstract class Pacote implements ICalculavel {

    // Matriz de distâncias 10x10, a partir de distancias aproximadas entre essas cidades
    public static final int[][] Matriz_Distancias = {
            // SP   RJ    BH    PR    RS    BA    DF    CE    AM    PE
            {   0,  400,  580,  400, 1100, 1900, 1000, 2800, 3800, 2600}, // 0: SP
            { 400,    0,  440,  800, 1500, 1600, 1100, 2500, 4200, 2300}, // 1: RJ
            { 580,  440,    0, 1000, 1700, 1400,  700, 2500, 3900, 2000}, // 2: BH
            { 400,  800, 1000,    0,  700, 2300, 1400, 3200, 4000, 3000}, // 3: PR
            {1100, 1500, 1700,  700,    0, 3000, 2000, 3900, 4500, 3700}, // 4: RS
            {1900, 1600, 1400, 2300, 3000,    0, 1400, 1100, 4900,  800}, // 5: BA
            {1000, 1100,  700, 1400, 2000, 1400,    0, 2100, 3400, 2100}, // 6: DF
            {2800, 2500, 2500, 3200, 3900, 1100, 2100,    0, 5800,  800}, // 7: CE
            {3800, 4200, 3900, 4000, 4500, 4900, 3400, 5800,    0, 5500}, // 8: AM
            {2600, 2300, 2000, 3000, 3700,  800, 2100,  800, 5500,    0}  // 9: PE
    };

    private static int contadorId = 1;

    private final int id;
    private Cliente cliente;
    private int origem;
    private int destino;
    protected double peso;

    public Pacote(Cliente cliente, int origem, int destino, double peso) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Pacote(Cliente cliente, int origem, int destino) {
        this(cliente, origem, destino, 1.0);
    }

    public int getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public int getOrigem() {
        return origem;
    }
    public void setOrigem(int origem) {
        this.origem = origem;
    }
    public int getDestino() {
        return destino;
    }
    public void setDestino(int destino) {
        this.destino = destino;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Cálculo do desconto para o pacote cadastrado
    protected final double calcularDescontoPolinomial() {
        double desconto = (0.05 * Math.pow(this.peso, 2)) + (0.2 * this.peso) + 5;
        return Math.min(desconto, 50.0);
    }
}