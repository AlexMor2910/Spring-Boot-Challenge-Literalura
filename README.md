# Desafio Literalura 📚

Este es un proyecto Java desarrollado con Spring Boot, inspirado en el desafío de la plataforma LiterAlura. Su objetivo es consumir y procesar información de la API de Gutendex (basada en el Proyecto Gutenberg) para explorar libros, autores y almacenarlos en una base de datos PostgreSQL.

## 🧰 Tecnologías y dependencias

- Java 17
- Spring Boot 3.2.3
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-webflux`
  - `spring-boot-devtools` (opcional)
  - `spring-boot-starter-test` (pruebas)
- PostgreSQL Driver 42.6.0
- Jackson Databind 2.16.0

## 📁 Estructura de archivos

- `main.java`: Clase principal que lanza la aplicación Spring Boot.
- `Servicios.java`: Lógica de servicios para consumo de la API de Gutendex.
- `repositorios.java`: Interfaces de repositorio JPA para persistencia de datos.
- `author.java`: Modelo de datos para representar autores.
- `pom.xml`: Archivo de configuración Maven con dependencias y plugins necesarios.

## ⚙️ Requisitos previos

- JDK 17
- Maven 3.8+
- PostgreSQL corriendo localmente o remotamente (y configurado en `application.properties` o `application.yml`)

## ▶️ Cómo ejecutar

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/desafio-gutenberg.git
   cd desafio-gutenberg

2. Configura la base de datos en tu archivo application.properties:

   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/gutenberg
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   spring.jpa.hibernate.ddl-auto=update

3. Compila y ejecuta la aplicación. La aplicación estará disponible en https://localhost:8080:

  ```bash
  ./mvnw spring-boot:run
  ```

##📡 Funcionalidades esperadas
Consulta y parseo de libros desde la API de Gutendex.

Almacenamiento de autores y libros en base de datos.

Endpoints REST para explorar y buscar libros.

Uso de WebClient (reactivo) para consumir la API de forma eficiente.

## 🧪 Pruebas
Para ejecutar pruebas unitarias y de integración:

  ```bash
  ./mvnw test
  ```

## 📚 Créditos
Desarrollado como parte del desafío de Java y Spring Boot de Alura Latam.



