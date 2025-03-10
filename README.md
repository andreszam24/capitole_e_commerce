# Proyecto Capitole - Tarifa Aplicable

Este proyecto es una implementación de una prueba técnica para el cargo de Senior Java Software Developer. Se ha desarrollado aplicando principios de Clean Code, Arquitectura Hexagonal y DDD (Domain Driven Design), y utiliza el patrón CQRS con un Query Bus para la consulta de tarifas.

## Índice

- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Instalación y Configuración](#instalación-y-configuración)
- [Pruebas de Integración](#pruebas-de-integración)
- [Uso del API](#uso-del-api)

## Descripción

La aplicación gestiona las tarifas (rates) para productos de una cadena. Se dispone de una tabla de precios (PRICES) que almacena, para cada producto y marca, el rango de fechas, la tarifa, prioridad, precio final y moneda. Se selecciona la tarifa a aplicar en función de la fecha de aplicación, el producto, la marca y la prioridad.

## Arquitectura

- **Dominio:** Modela las entidades y objetos de valor (por ejemplo, `Price`, `Product`, `Brand`, `Money`, etc.).
- **Aplicación:** Implementa el Query Bus, Query Handlers y casos de uso para orquestar la lógica de negocio sin acoplarse a la infraestructura.
- **Infraestructura:** Implementa la persistencia utilizando Spring Data JPA y H2 Database.
- **API:** Implementa el controlador y expone el API REST.

## Tecnologías

- **Java 17**
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **H2 Database**
- **JUnit 5 y Mockito** para pruebas
- **Gradle** para gestión de dependencias

## Requisitos

- Java 17 instalado y configurado.
- Gradle para compilar y ejecutar el proyecto.

## Instalación y Configuración

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/andreszam24/capitole_e_commerce.git
   cd inditex
   
2. **Configurar la base de datos:**
   La aplicación usa H2 en memoria. Puedes modificar la configuración en src/main/resources/application.properties

3. **Poblar la base de datos:**
   El archivo data.sql en src/main/resources contiene los scripts para poblar con datos de ejemplo.

## Pruebas de Integración
Se han implementado pruebas de integración usando TestRestTemplate para validar los 5 casos de uso del endpoint /api/rate. Estas pruebas se pueden ejecutar con:

```bash
./gradlew :com.capitole.application:test :com.capitole.domain:test test
```
## Uso del API
El endpoint para consultar la tarifa es:
```bash 
GET /api/rate 
```

Parámetros de consulta:

- brandId: ID de la marca (ej. 1)
- productId: ID del producto (ej. 35455)
- applicationDate: Fecha y hora de aplicación en formato ISO-8601 (ej. 2020-06-14T16:00:00)

Ejemplo usando cURL
```bash 
curl --request GET \
--url 'http://localhost:8080/api/rate?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00'
```
La respuesta será un JSON con los siguientes campos:

- productId
- brandId
- priceList
- startDate
- endDate
- price

