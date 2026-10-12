public class PacotePadrao extends Pacote {

    // O tipo padrão demora mais, porém é mais barato
    public PacotePadrao(Cliente cliente, int origem, int destino, double peso) {
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
        double custoBase = distancia * 0.5; // R$ 0.50 por km
        double desconto = calcularDescontoPolinomial();

        return Math.max(0, custoBase - desconto);
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Padrão " + getCliente().getNome() + " | " + getPeso() + "kg";
    }
}