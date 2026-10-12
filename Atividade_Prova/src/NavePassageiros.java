public class NavePassageiros extends Nave{
    public NavePassageiros(String nome, String codigoDeRegistro, double qtdCombustivel, Motor motor) {
        super(nome, codigoDeRegistro, qtdCombustivel, motor);
    }

    @Override
    public void viajar (double distancia){
        System.out.println("Aviso: Escudos ativados com sucesso! Velocidade máxima ativada");
        double consumo = distancia * 1; // Gastam menos que o cargueiro
        super.motor.gastarCombustivel(consumo, this);
        System.out.println(getNome() + " (Passageiros) consumiu: " + String.format("%.2f", consumo) + " unidades de combustível.");
    }
}
