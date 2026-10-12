public class Utilitarios {
    public static String padronizarPlaneta(String destino){
        return destino.trim().toUpperCase();
    }

    public static double calcularDistancia (int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
    }
}
