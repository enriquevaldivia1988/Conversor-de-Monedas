package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonObject;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

class ConversorDeMonedasTest {

    @Test
    void testObtenerTasas_JsonValido() {
        String jsonRespuesta = "{ \"conversion_rates\": { \"USD\": 1.0, \"ARS\": 95.0, \"PEN\": 4.0 } }";
        JsonObject tasas = ConversorDeMonedas.obtenerTasas(jsonRespuesta);

        assertNotNull(tasas);
        assertTrue(tasas.has("USD"));
        assertEquals(1.0, tasas.get("USD").getAsDouble());
        assertEquals(95.0, tasas.get("ARS").getAsDouble());
        assertEquals(4.0, tasas.get("PEN").getAsDouble());
    }

    @Test
    void testObtenerTasas_JsonInvalido() {
        String jsonRespuesta = "{ \"invalid_rates\": { \"USD\": 1.0 } }";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ConversorDeMonedas.obtenerTasas(jsonRespuesta);
        });

        assertEquals("Formato de respuesta JSON no válido", exception.getMessage());
    }

    @Test
    void testConvertirMoneda_ConversionExitosa() {
        String jsonRespuesta = "{ \"conversion_rates\": { \"USD\": 1.0, \"PEN\": 4.0, \"ARS\": 95.0 } }";
        JsonObject tasas = ConversorDeMonedas.obtenerTasas(jsonRespuesta);

        double resultado = ConversorDeMonedas.convertirMoneda(100, "USD", "PEN", tasas);
        assertEquals(400.0, resultado);

        resultado = ConversorDeMonedas.convertirMoneda(100, "PEN", "USD", tasas);
        assertEquals(25.0, resultado);
    }

    @Test
    void testConvertirMoneda_TasaInvalida() {
        String jsonRespuesta = "{ \"conversion_rates\": { \"USD\": 1.0 } }";
        JsonObject tasas = ConversorDeMonedas.obtenerTasas(jsonRespuesta);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ConversorDeMonedas.convertirMoneda(100, "USD", "PEN", tasas);
        });

        assertEquals("Las tasas para la moneda origen o destino no están disponibles.", exception.getMessage());
    }

    @Test
    void testRealizarConversion_Exitosa() {
        String jsonRespuesta = "{ \"conversion_rates\": { \"USD\": 1.0, \"PEN\": 4.0 } }";
        JsonObject tasas = ConversorDeMonedas.obtenerTasas(jsonRespuesta);

        // Simulamos la entrada del usuario
        String input = "100.0"; // Simular que el usuario ingresa "100.0"
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Reemplazar la entrada estándar con nuestro InputStream

        Scanner scanner = new Scanner(System.in);

        assertDoesNotThrow(() -> {
            ConversorDeMonedas.realizarConversion(8, tasas, scanner);
        });

        scanner.close();
    }

}
