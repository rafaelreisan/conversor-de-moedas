# Conversor de Moedas em Java 💱

Este projeto é um **Conversor de Moedas** desenvolvido em Java que interage com o usuário via console. Ele consome dados de uma API de taxas de câmbio em tempo real e realiza conversões entre diferentes moedas.

## 🔧 Tecnologias utilizadas
- Java 11+
- Biblioteca [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)
- API [ExchangeRate-API](https://www.exchangerate-api.com/)
- `HttpClient`, `HttpRequest`, `HttpResponse` para requisições HTTP

## 📦 Funcionalidades
- Interface textual com menu interativo no console
- Conversão entre as seguintes moedas:
  - USD (Dólar Americano)
  - BRL (Real Brasileiro)
  - ARS (Peso Argentino)
  - CLP (Peso Chileno)
  - COP (Peso Colombiano)
  - BOB (Boliviano)
  - EUR (Euro)
  - JPY (Iene Japonês)
  - GBP (Libra Esterlina)
  - CAD (Dólar Canadense)
- Histórico de conversões exibido no console
- Salvamento automático do histórico em `historico.txt`
- Logs com data e hora de cada conversão

## ▶️ Como executar
1. Clone este repositório ou extraia o ZIP.
2. Abra o projeto em sua IDE (recomendado: IntelliJ).
3. Certifique-se de ter o Java 11 ou superior instalado.
4. Adicione a biblioteca Gson ao projeto.
5. Insira sua chave da ExchangeRate API no campo:
   ```java
   private static final String API_KEY = "SUA_API_KEY_AQUI";
   ```
6. Execute a classe `ConversorMoedas`.

## 📁 Estrutura
```
ConversorMoedas/
└── src/
    └── main/
        └── java/
            └── conversor/
                └── ConversorMoedas.java
historico.txt
README.md
```

## ✨ Melhorias sugeridas
- Interface gráfica (GUI)
- Suporte a criptomoedas com CoinGecko API
- Exportação para PDF ou Excel
- Suporte multilíngue

---

Projeto criado como desafio de aprendizado em Java. 🚀
