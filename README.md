# Spring Boot AI with Text2SQL Tool

A Spring Boot application that integrates with Ollama for AI-powered Text2Sql chat functionality, using PostgreSQL for data persistence.

## Prerequisites

The following must be installed to run this project:

- Java 21 https://www.oracle.com/java/technologies/downloads/#java21
- Docker https://docs.docker.com/get-started/get-docker/
- Ollama https://ollama.com/download

## Technology Stack

- Spring Boot 3.4.5
- Java 21
- Ollama
- Llama3.2
- PostgreSQL

## Setup

1. **Build the application**

   ```bash
   ./mvnw clean package
   ```

2. **Run the application**

   > [!NOTE]
   > PostgreSQL db will automatically be started since Spring Docker Compose support is enabled

   ```bash
   ./mvnw spring-boot:run
   ```
3. **Accessing Rest API endpoint**

   The application exposes the following REST API endpoint:

   ```
   GET /api/ollama/query?question={your_question}
   ```

   Example Request using CURL:

   ```bash
   curl "http://localhost:8080/api/ollama/query?question=wwhat%20stocks%20were%20bought%20in%202017"
   ```

   Example Response:

   ```
   {"result":{"stock_symbol":["COF^D","CST","EMD","IBKCP","DD^B","NBLX","NCR","ARI^A","COR^A","FIG","XL","JHB","ARE"]},"error":null}
   ```


## Features

- AI-powered Text2SQL Tool calling using Ollama
- PostgreSQL database integration
- Database migration management with Flyway
- Spring Boot integration with Ollama via Spring AI module

## Development

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/guadgarcia/ollama/
│   │       ├── config/          # Configuration classes
│   │       ├── domain/          # Domain models and entities
│   │       ├── repository/      # Data access layer
│   │       ├── service/         # Business logic layer
│   │       ├── controller/      # REST API endpoints
│   │       └── OllamaApplication.java
│   └── resources/
│       ├── db/
│       │   └── migration/       # Database migration scripts
│       └── application.yml      # Application configuration
└── test/
    └── java/
        └── com/guadgarcia/ollama/
            └── OllamaApplicationTests.java
```

## Useful Resources

- Spring AI: https://spring.io/projects/spring-ai


## License

This project is licensed under the MIT License - see the LICENSE file for details.
