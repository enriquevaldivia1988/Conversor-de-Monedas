
# Conversor de Monedas

¡Bienvenido/a al proyecto **Conversor de Monedas**! Este programa fue desarrollado como parte de un desafío técnico propuesto en el curso de ONE (Oracle Next Education). El propósito del proyecto es realizar conversiones entre distintas monedas utilizando una API de tasas de cambio en tiempo real.

## 🚀 Funcionalidades

- Conversión de Dólar estadounidense (USD) a varias monedas latinoamericanas y viceversa.
- Conversión entre:
    - Dólar a Peso argentino
    - Peso argentino a Dólar
    - Dólar a Real brasileño
    - Real brasileño a Dólar
    - Dólar a Peso colombiano
    - Peso colombiano a Dólar
    - Dólar a Sol peruano
    - Sol peruano a Dólar
- Interfaz de usuario en la consola, permitiendo la selección de opciones a través de un menú interactivo.

## 🛠️ Tecnologías utilizadas

- **Java 11+**: Para la lógica del programa.
- **HttpClient**: Para consumir la API de tasas de cambio.
- **Gson**: Para analizar y manipular el JSON obtenido de la API.
- **API de tasas de cambio**: [ExchangeRate-API](https://www.exchangerate-api.com/), que proporciona los valores de conversión en tiempo real.

## 💡 Requisitos

Antes de ejecutar el proyecto, asegúrate de cumplir con los siguientes requisitos:

- Tener instalado **Java 11** o superior.
- Acceso a internet para realizar consultas a la API.
- Tener Maven configurado para manejar dependencias (si lo estás utilizando).

## 🚀 Instalación y ejecución

Sigue estos pasos para instalar y ejecutar el proyecto:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/enriquevaldivia1988/Conversor-de-Monedas-.git
   ```
2. Dirígete al directorio del proyecto:
   ```bash
   cd conversor-monedas
   ```
3. Compila y ejecuta el proyecto:
   ```bash
   javac ConversorDeMonedas.java
   java ConversorDeMonedas
   ```

## 📋 Instrucciones de uso

El programa presenta un menú interactivo con opciones para convertir entre las siguientes monedas:

1. **Dólar => Peso argentino**
2. **Peso argentino => Dólar**
3. **Dólar => Real brasileño**
4. **Real brasileño => Dólar**
5. **Dólar => Peso colombiano**
6. **Peso colombiano => Dólar**
7. **Dólar => Sol peruano**
8. **Sol peruano => Dólar**
9. **Salir**

### Ejemplo de uso:

Al ejecutar el programa, el usuario verá un menú en la consola. Para realizar una conversión:

1. Selecciona una opción del menú (por ejemplo, "1" para convertir de Dólar a Peso argentino).
2. Introduce el valor que deseas convertir.
3. El programa mostrará el resultado de la conversión utilizando las tasas de cambio más recientes obtenidas de la API.

**Ejemplo de salida:**

```plaintext
Ingrese el valor que deseas convertir: 100
El valor 100.0 [USD] corresponde al valor final de => 20293.75 [ARS]
```

## 🧩 API de tasas de cambio

La API utilizada en este proyecto es [ExchangeRate-API](https://www.exchangerate-api.com/), que proporciona tasas de cambio actualizadas en tiempo real para múltiples monedas.

URL de ejemplo utilizada:
```plaintext
https://v6.exchangerate-api.com/v6/tu-api-key/latest/USD
```

## 🤖 Testing

Asegúrate de probar el programa con diferentes cantidades y conversiones entre monedas. Aquí algunos ejemplos de pruebas que puedes realizar:

- **Dólar a Peso argentino**: Verifica que el valor de 100 USD sea correctamente convertido.
- **Real brasileño a Dólar**: Verifica que los valores sean coherentes al convertir desde una moneda sudamericana hacia el USD.

## 🏆 Desafíos superados

Durante el desarrollo de este proyecto, se abordaron varios retos, tales como:

- Consumo de una API externa utilizando **HttpClient**.
- Análisis de la respuesta JSON con **Gson** para obtener las tasas de cambio.
- Implementación de un menú interactivo que permite al usuario seleccionar diferentes conversiones.

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo [LICENSE](LICENSE).

## ✨ Autor

Desarrollado por [Enrique Valdivia](https://github.com/enriquevaldivia1988).
