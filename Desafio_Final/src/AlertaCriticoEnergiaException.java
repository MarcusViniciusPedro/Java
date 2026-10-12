// Tratamento de Exceção Personalizada [cite: 14]
public class AlertaCriticoEnergiaException extends Exception {
    public AlertaCriticoEnergiaException(String mensagem) {
        super("ALERTA CRÍTICO: " + mensagem);
    }
}

// Interface/Contrato para localização [cite: 8]
interface Localizavel {
    String reportarLocalizacao();
}
