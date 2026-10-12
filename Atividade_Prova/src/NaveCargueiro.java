import java.util.HashMap;
import java.util.Map;

public class NaveCargueiro extends Nave{
    private Map <String, Double> suprimentos;

    public NaveCargueiro(String nome, String codigoDeRegistro, double qtdCombustivel, Motor motor) {
        super(nome, codigoDeRegistro, qtdCombustivel, motor);
        this.suprimentos = new HashMap<>();
    }

    public void addSuprimento (String nome, Double peso){
        this.suprimentos.put (nome, peso);
    }

    public Double buscaPesoSuprimentos(String nome){
        return this.suprimentos.get(nome);
    }

    @Override
    public void viajar(double distancia){
        double consumo = distancia * 2;
        super.motor.gastarCombustivel(consumo, this);

        System.out.println("A nave chegou ao destino e teve um consumo de: " + consumo + "unidades de combustível.");
    }
}
