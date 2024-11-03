import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JogoAdivinhacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroMin, numeroMax, numeroSecreto, tentativas;
        String resposta;

        System.out.println("Jogo de Adivinhação de Números!");

        // Escolha do intervalo
        System.out.print("Digite o valor mínimo do intervalo: ");
        numeroMin = lerNumero(scanner);

        System.out.print("Digite o valor máximo do intervalo: ");
        numeroMax = lerNumero(scanner);

        // Verificar se o intervalo é válido
        while (numeroMin >= numeroMax) {
            System.out.println("O valor máximo deve ser maior que o mínimo. Tente novamente.");
            System.out.print("Digite o valor mínimo do intervalo: ");
            numeroMin = lerNumero(scanner);

            System.out.print("Digite o valor máximo do intervalo: ");
            numeroMax = lerNumero(scanner);
        }

        // Gerar o número secreto
        numeroSecreto = random.nextInt(numeroMax - numeroMin + 1) + numeroMin;
        tentativas = 0;

        System.out.println("Tente adivinhar o número entre " + numeroMin + " e " + numeroMax + ".");

        // Loop do jogo
        while (true) {
            System.out.print("Digite sua tentativa ou 'sair' para encerrar o jogo: ");
            resposta = scanner.next();

            // Opção de sair
            if (resposta.equalsIgnoreCase("sair")) {
                System.out.println("Você encerrou o jogo. O número secreto era " + numeroSecreto + ".");
                break;
            }

            // Tentar converter a resposta para número
            try {
                int tentativa = Integer.parseInt(resposta);
                tentativas++;

                // Validar se a tentativa está dentro do intervalo
                if (tentativa < numeroMin || tentativa > numeroMax) {
                    System.out.println("Por favor, insira um número dentro do intervalo especificado.");
                    continue;
                }

                // Fornecer dicas
                if (tentativa < numeroSecreto) {
                    System.out.println("O número secreto é maior!");
                } else if (tentativa > numeroSecreto) {
                    System.out.println("O número secreto é menor!");
                } else {
                    System.out.println("Parabéns! Você acertou o número em " + tentativas + " tentativas!");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    // Método para ler números inteiros
    private static int lerNumero(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next(); // Limpa a entrada inválida
            }
        }
        return numero;
    }
}
