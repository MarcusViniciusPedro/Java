class NaveCombate extends Nave {
    private Piloto piloto; // Composição em ação
    private int nivelArmamento;

    public NaveCombate(String nome, int idRegistro, Piloto piloto, int nivelArmamento) {
        super(nome, idRegistro); // Chamando construtor da superclasse
        this.piloto = piloto;
        this.nivelArmamento = nivelArmamento;
    }

    // Polimorfismo: Sobreposição (Override) do método da classe abstrata
    @Override
    public void viajar() {
        System.out.println("Nave de Combate " + nome + " viajando na velocidade de dobra!");
    }

    // Polimorfismo: Sobreposição do método da Interface
    @Override
    public void realizarCheckup() throws NaveInoperanteException {
        // Uso da API Math para simular um teste de dano aleatório
        double integridade = Math.random() * 100;

        if (integridade < 20.0) {
            // Lançando exceção manualmente (Throw)
            throw new NaveInoperanteException("Falha crítica no motor da nave " + nome);
        }
        System.out.println("Checkup aprovado para a nave: " + nome + " pilotada por " + piloto.getNome());
    }
}
