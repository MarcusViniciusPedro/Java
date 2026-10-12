public class Main {
    public static void main(String[] args) {
        Guilda guilda = new Guilda();

        Arma arco = new Arma("Arco Longo", 25.5);
        Arma cajado = new Arma("Cajado das Sombras", 40.0);

        Arqueiro legolas = new Arqueiro(" Legolas ", 10, arco, 30);
        Mago gandalf = new Mago(" Gandalf ", 5, cajado, 5);

        System.out.println("--- REGISTRO DE CAÇADORES ---");
        guilda.registrarCacador(legolas, 101, "123.456.789-00");
        guilda.registrarCacador(gandalf, 102, "987.654.321-00");

        Arqueiro impostor = new Arqueiro(" Impostor ", 5, arco, 10);
        guilda.registrarCacador(impostor, 103, "123.456.789-00");

        System.out.println("--- INICIANDO MISSÕES ---");

        for (Cacador cacador : guilda.getMapaCacadores().values()) {
            System.out.println("\n>>> Turno de: " + cacador.getNome());

            try{
                cacador.iniciarMissao(12);

                double dano = cacador.atacar();
                System.out.println(cacador.getNome() + " causou " + dano + " pontos de dano!");

            } catch (MissaoInvalidaException e) {
                System.out.println("ALERTA DE MISSÃO: " + e.getMessage());

            } catch (ManaInsuficienteException e) {
                System.out.println("FALHA DE COMBATE: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("ERRO INESPERADO: " + e.getMessage());

            } finally {
                System.out.println("Turno encerrado para o caçador.");
            }
        }
    }
}