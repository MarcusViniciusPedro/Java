public class PacoteExpresso extends Pacote {

    // o expresso é mais rápido, porém mais caro
    public PacoteExpresso(Cliente cliente, int origem, int destino, double peso) {
        super(cliente, origem, destino, peso);
    }

    @Override
    public double calcularFrete() throws RotaInvalidaException {
        // Validação Dinâmica: Adaptável a qualquer tamanho de matriz
        int limiteMatriz = Matriz_Distancias.length;
        if (getOrigem() < 0 || getOrigem() >= limiteMatriz || getDestino() < 0 || getDestino() >= limiteMatriz) {
            throw new RotaInvalidaException("Rota inexistente na matriz.");
        }

        int distancia = Matriz_Distancias[getOrigem()][getDestino()];
        double custoBase = distancia * 0.8; // R$ 0.80 por km
        double desconto = calcularDescontoPolinomial();

        return Math.max(0, custoBase - (desconto / 2)) + 50.0;
    }

    @Override
    public String toString() {
        return "[ID: " + getId() + " | Expresso] " + getCliente().getNome() + " | " + getPeso() + "kg";
    }
}