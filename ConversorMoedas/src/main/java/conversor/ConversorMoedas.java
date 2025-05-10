package conversor;

import java.net.http.*;
import java.net.URI;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.*;

public class ConversorMoedas {
    private static final String API_KEY = "cb0cfd53db31e14923ab9d16"; // Insira sua chave da API aqui
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private static final String HISTORICO_ARQUIVO = "historico.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] moedas = { "USD", "BRL", "ARS", "CLP", "COP", "BOB", "EUR", "JPY", "GBP", "CAD" };

        while (true) {
            System.out.println("\n*** Conversor de Moedas ***");
            for (int i = 0; i < moedas.length; i++) {
                System.out.printf("%d - Converter de %s para outra moeda\n", i + 1, moedas[i]);
            }
            System.out.println("11 - Ver histórico de conversões");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 0) break;
            if (opcao == 11) {
                exibirHistorico();
                continue;
            }
            if (opcao < 1 || opcao > moedas.length) {
                System.out.println("Opção inválida.");
                continue;
            }

            String base = moedas[opcao - 1];
            System.out.print("Digite o valor em " + base + ": ");
            double valor = scanner.nextDouble();

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + base))
                    .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject rates = json.getAsJsonObject("conversion_rates");

                for (String moeda : moedas) {
                    if (!moeda.equals(base)) {
                        double taxa = rates.get(moeda).getAsDouble();
                        double convertido = valor * taxa;
                        String log = formatarLog(base, moeda, valor, convertido);
                        System.out.println(log);
                        salvarNoArquivo(log);
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro ao obter dados da API: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    private static String formatarLog(String de, String para, double valor, double convertido) {
        String horario = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.format("[%s] %s -> %s = %.2f", horario, de, para, convertido);
    }

    private static void salvarNoArquivo(String log) {
        try (FileWriter writer = new FileWriter(HISTORICO_ARQUIVO, true)) {
            writer.write(log + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    private static void exibirHistorico() {
        File file = new File(HISTORICO_ARQUIVO);
        if (!file.exists()) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }

        System.out.println("\n*** Histórico de Conversões ***");
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o histórico.");
        }
    }
}
