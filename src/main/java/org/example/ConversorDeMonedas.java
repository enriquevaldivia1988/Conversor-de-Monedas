package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.logging.Logger;

public class ConversorDeMonedas {

    private static final Logger logger = Logger.getLogger(ConversorDeMonedas.class.getName());

    public static void main(String[] args) {
        String apiKey = System.getenv("EXCHANGE_RATE_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            logger.severe("Error: No se encontró la clave de API en la variable de entorno 'EXCHANGE_RATE_API_KEY'");
            return;
        }

        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject rates = obtenerTasas(response.body());

            Scanner scanner = new Scanner(System.in);
            int opcion;

            try {
                do {
                    mostrarMenu();

                    try {
                        opcion = scanner.nextInt();

                        if (opcion >= 1 && opcion <= 8) {
                            realizarConversion(opcion, rates, scanner);
                        } else if (opcion == 9) {
                            System.out.println("Saliendo del programa...");
                        } else {
                            System.out.println("Opción inválida. Intente de nuevo.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Por favor, ingrese una opción válida.");
                        scanner.next(); // limpiar el scanner
                        opcion = -1; // Para evitar salir del bucle
                    }
                } while (opcion != 9);
            } finally {
                scanner.close();
            }

        } catch (IOException | InterruptedException e) {
            logger.severe("Se encontró un error al enviar la solicitud de tasa de cambio: " + e.getMessage());
        }
    }

    public static JsonObject obtenerTasas(String respuestaJson) {
        JsonObject jsonObject = JsonParser.parseString(respuestaJson).getAsJsonObject();
        if (jsonObject.has("conversion_rates")) {
            return jsonObject.getAsJsonObject("conversion_rates");
        } else {
            throw new IllegalArgumentException("Formato de respuesta JSON no válido");
        }
    }

    public static void mostrarMenu() {
        System.out.println("************************************************");
        System.out.println("** Sea bienvenido/a al Conversor de Moneda =] **");
        System.out.println("************************************************");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Sol Peruano => Dólar");
        System.out.println("8) Dólar => Sol Peruano");
        System.out.println("9) Salir");
        System.out.print("Elija una opción válida: ");
    }

    public static void realizarConversion(int opcion, JsonObject rates, Scanner scanner) {
        double cantidad;
        double resultado;
        String monedaOrigen = "", monedaDestino = "";

        switch (opcion) {
            case 1: // Dólar a Peso argentino
                monedaOrigen = "USD";
                monedaDestino = "ARS";
                break;
            case 2: // Peso argentino a Dólar
                monedaOrigen = "ARS";
                monedaDestino = "USD";
                break;
            case 3: // Dólar a Real brasileño
                monedaOrigen = "USD";
                monedaDestino = "BRL";
                break;
            case 4: // Real brasileño a Dólar
                monedaOrigen = "BRL";
                monedaDestino = "USD";
                break;
            case 5: // Dólar a Peso colombiano
                monedaOrigen = "USD";
                monedaDestino = "COP";
                break;
            case 6: // Peso colombiano a Dólar
                monedaOrigen = "COP";
                monedaDestino = "USD";
                break;
            case 7: // Sol Peruano a Dólar
                monedaOrigen = "PEN";
                monedaDestino = "USD";
                break;
            case 8: // Dólar a Sol Peruano
                monedaOrigen = "USD";
                monedaDestino = "PEN";
                break;
        }

        try {
            System.out.print("Ingrese el valor que deseas convertir: ");
            cantidad = scanner.nextDouble();

            resultado = convertirMoneda(cantidad, monedaOrigen, monedaDestino, rates);
            System.out.printf("El valor %.2f [%s] corresponde al valor final de => %.2f [%s]%n", cantidad, monedaOrigen, resultado, monedaDestino);

        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número válido.");
            scanner.next(); // limpiar el scanner en caso de error
        } catch (NullPointerException e) {
            System.out.println("Error: No se encontraron las tasas de cambio para la conversión seleccionada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double convertirMoneda(double cantidad, String monedaOrigen, String monedaDestino, JsonObject rates) {
        if (!rates.has(monedaOrigen) || !rates.has(monedaDestino)) {
            throw new IllegalArgumentException("Las tasas para la moneda origen o destino no están disponibles.");
        }
        double tasaOrigen = rates.get(monedaOrigen).getAsDouble();
        double tasaDestino = rates.get(monedaDestino).getAsDouble();
        return cantidad * (tasaDestino / tasaOrigen);
    }
}
