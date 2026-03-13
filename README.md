# Microservicio de Biblioteca

Este microservicio permite la gestión de libros mediante un CRUD completo desarrollado con **Spring Boot 3.5** y **Java 21**.

## Características
- **GET**: Consulta de libros por ID.
- **POST**: Registro de nuevos ejemplares.
- **PUT**: Actualización de información existente.
- **DELETE**: Eliminación de registros por ID.

## Tecnologías Utilizadas
- **Base de Datos**: Oracle Autonomous Database (OCI).
- **Persistencia**: Spring Data JPA / Hibernate.
- **Conexión**: Oracle Wallet (TCPS).

## Configuración Local
Para ejecutar este proyecto, se requiere configurar el archivo `application.properties` con las credenciales de la base de datos y disponer del Wallet de Oracle en la ruta configurada en `TNS_ADMIN`.