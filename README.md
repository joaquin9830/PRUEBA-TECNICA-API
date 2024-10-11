# Klyver - API de Gestión de Personas y Películas

Bienvenido al repositorio de Klyver, una API de gestión de personas y películas desarrollada con **Spring Boot**. Este proyecto tiene como objetivo facilitar el manejo de datos relacionados con personas y sus películas favoritas.

## Índice

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Dependencias](#dependencias)
- [Instalación y Configuración](#instalación-y-configuración)
- [Uso](#uso)


## Características

- CRUD completo para gestionar personas y películas.
- Búsqueda eficiente y filtrado de datos.
- API RESTful para interacción con clientes.
- Pruebas automatizadas para asegurar la calidad del código.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **JPA/Hibernate**
- **H2 Database**
- **Lombok**
- **JUnit y Mockito para pruebas**

## Dependencias

El proyecto utiliza las siguientes dependencias:

- `spring-boot-starter-data-jpa`: Para la gestión de la capa de persistencia.
- `spring-boot-starter-web`: Para crear la API RESTful.
- `spring-boot-devtools`: Para mejorar la experiencia de desarrollo.
- `h2`: Base de datos en memoria para desarrollo y pruebas.
- `lombok`: Para reducir el código boilerplate.
- `spring-boot-starter-test`: Para facilitar la escritura de pruebas.

## Instalación y Configuración

Para instalar y ejecutar el proyecto, sigue estos pasos:

1. **Clona el repositorio**:
    ```bash
    git clone https://github.com/tu_usuario
  
    ```

2. **Compila el proyecto**:
    ```bash
    ./mvnw clean install
    ```

3. **Ejecuta la aplicación**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Accede a la API**: Abre tu navegador y visita `http://localhost:8080`.

## Uso

- La API proporciona endpoints para gestionar personas y películas. Puedes utilizar herramientas como Postman o cURL para interactuar con los endpoints.
- Ejemplo de un endpoint para obtener todas las personas:
    ```
    GET http://localhost:8080/api/personas
