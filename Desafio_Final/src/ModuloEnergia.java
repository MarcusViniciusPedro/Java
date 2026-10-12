public class ModuloEnergia {
    private double nivel;

    public ModuloEnergia(double nivelInicial) {
        this.nivel = nivelInicial;
    }

    public double getNivel() { return nivel; }

    public void consumir(double quantidade) throws AlertaCriticoEnergiaException {
        if (nivel < quantidade) {
            throw new AlertaCriticoEnergiaException("Energia insuficiente para a operação!");
        }
        nivel -= quantidade;
    }
}

// Classe Utilitária [cite: 12, 13]
class BioHubUtils {
    public static final double TEMPERATURA_SEGURANCA = 22.5; // Atributo Estático e Final

    public static String formatarCodigo(String codigo) {
        return codigo.trim().toLowerCase(); // Manipulação de Strings
    }
}