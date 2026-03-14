# 📚 API REST de Biblioteca - Spring Boot & Oracle Cloud

Este proyecto consiste en un microservicio robusto para la gestión de libros, desarrollado bajo estándares. Implementa un CRUD completo con validaciones de lógica de negocio, normalización de datos y un sistema centralizado de manejo de excepciones.

## 🚀 Tecnologías y Herramientas

* **Java 17 / Spring Boot 3**: Framework principal del proyecto.
* **Spring Data JPA**: Gestión de persistencia y consultas automatizadas.
* **Oracle Cloud (Autonomous Database)**: Almacenamiento de datos en la nube.
* **Maven**: Gestión de dependencias y ciclo de vida del proyecto.
* **Postman**: Suite de pruebas para los endpoints de la API.

---

## 🛠️ Arquitectura y Mejores Prácticas

### 1. Manejo Global de Excepciones (`@RestControllerAdvice`)
Se ha implementado una capa de interceptación de errores. Cualquier fallo en la aplicación (ID no encontrado, duplicados, errores de servidor) devuelve un objeto JSON estandarizado:
* **statusCode**: Código HTTP preciso.
* **message**: Mensaje amigable en español.
* **timestamp**: Registro exacto del error.
* **description**: URI del endpoint afectado.

### 2. Normalización y Validación de Datos
Para garantizar la integridad de la base de datos en **Oracle Cloud**, el sistema aplica:
* **Estandarización**: Los campos de texto se procesan con `.trim().toUpperCase()` antes de la persistencia.
* **Integridad**: Validación de existencia previa en operaciones de `PUT` y `DELETE`.
* **Unicidad**: Control de lógica de negocio para evitar duplicados por combinación de Título + Autor.

### 3. Seguridad de Infraestructura
* Uso de **Oracle Wallet** para conexiones seguras (mantenido fuera del repositorio mediante `.gitignore`).
* Mapeo de entidades optimizado con `@Table` y `@Column` para transparencia con el motor de base de datos.

---

## 📡 Endpoints de la API

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/libros/{id}` | Recupera la información de un libro específico. |
| **POST** | `/api/libros` | Registra un nuevo libro (Normaliza y valida duplicados). |
| **PUT** | `/api/libros/{id}` | Actualiza datos de un libro (Verifica existencia). |
| **DELETE** | `/api/libros/{id}` | Elimina un registro de la base de datos. |

---

## ⚙️ Instrucciones de Ejecución

1. **Requisitos**: Tener Java 17 y Maven instalados.
2. **Configuración de Base de Datos**: 
   * Colocar el Wallet de Oracle en la ruta configurada en `application.properties`.
3. **Arranque**:
   ```bash
   mvn spring-boot:run