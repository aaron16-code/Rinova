# Rinova — Sistema de Librería con Microservicios

Sistema de gestión de librería construido con arquitectura de microservicios usando **Spring Boot 4.0.6** y **Java 25**.

---

## 📋 Contexto

Rinova es un sistema de librería en línea que permite gestionar autores, libros, usuarios, inventario, pedidos, pagos, reseñas y notificaciones. Cada dominio de negocio está encapsulado en su propio microservicio independiente con base de datos propia, comunicándose a través de un API Gateway centralizado.

---

## 👥 Créditos

| Integrante | Microservicios documentados y testeados |
|------------|----------------------------------------|
| Matias Valdebenito | autor-service, libro-service |
| Aaron Ojeda | usuario-service, pedido-service |

---

## 🏗️ Arquitectura

### Tecnologías

- **Java:** 25
- **Framework:** Spring Boot 4.0.6
- **Spring Cloud:** Netflix Eureka, Spring Cloud Gateway, Spring Cloud Config
- **Base de Datos:** MySQL 8.0 (esquema independiente por servicio)
- **Migraciones:** Flyway
- **Persistencia:** Hibernate / JPA (`ddl-auto: validate`)
- **Documentación:** SpringDoc OpenAPI 2.8.8 (Swagger)
- **Productividad:** Lombok

### Microservicios implementados

```
├── config-server/          → Configuración centralizada (Puerto 8888)
├── eureka-server/          → Descubrimiento de servicios (Puerto 8761)
├── api-gateway/            → Puerta de enlace única (Puerto 8090)
├── autor-service/          → Gestión de autores (Puerto dinámico)
├── libro-service/          → Gestión de libros y géneros (Puerto dinámico)
├── usuario-service/        → Gestión de usuarios (Puerto dinámico)
├── inventario-service/     → Control de stock (Puerto dinámico)
├── pedido-service/         → Gestión de pedidos (Puerto dinámico)
├── pago-service/           → Registro de pagos (Puerto dinámico)
├── resena-service/         → Reseñas de libros (Puerto dinámico)
└── notificacion-service/   → Notificaciones a usuarios (Puerto dinámico)
```

---

## 🌐 Networking — Rutas principales por el API Gateway

Todas las rutas están expuestas a través del **API Gateway en `http://localhost:8090`**.

| Servicio | Prefijo de ruta |
|----------|----------------|
| Autores | `/api/v1/autores/**` |
| Libros | `/api/v1/libros/**`, `/api/v1/generos/**` |
| Usuarios | `/api/v1/usuarios/**` |
| Inventario | `/api/v1/inventario/**` |
| Pedidos | `/api/v1/pedidos/**` |
| Pagos | `/api/v1/pagos/**` |
| Reseñas | `/api/v1/resenas/**` |
| Notificaciones | `/api/v1/notificaciones/**` |

---

## 📖 Accesos a Swagger

La documentación OpenAPI está centralizada en el API Gateway:

- **Swagger UI (local):** [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)
- **Swagger UI (Docker):** [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)

Desde el menú desplegable de Swagger puedes seleccionar cualquiera de los 8 servicios de negocio.

---

## 🚀 Guía de Despliegue

### Opción 1 — Entorno Local (Sin Docker)

**Requisitos previos:**
- Java 25 instalado
- Maven 3.9+
- MySQL activo en puerto 3306 (XAMPP u otro)

**Paso 1 — Crear bases de datos en MySQL:**
```sql
CREATE DATABASE IF NOT EXISTS bd_autores;
CREATE DATABASE IF NOT EXISTS bd_libros;
CREATE DATABASE IF NOT EXISTS bd_usuarios;
CREATE DATABASE IF NOT EXISTS bd_inventario;
CREATE DATABASE IF NOT EXISTS bd_pedidos;
CREATE DATABASE IF NOT EXISTS bd_pagos;
CREATE DATABASE IF NOT EXISTS bd_resenas;
CREATE DATABASE IF NOT EXISTS bd_notificaciones;
```

**Paso 2 — Levantar servicios en este orden:**

```bash
# 1. Config Server
cd config-server && ./mvnw spring-boot:run

# 2. Eureka Server
cd eureka-server && ./mvnw spring-boot:run

# 3. Microservicios de negocio (en cualquier orden, en terminales separadas)
cd autor-service && ./mvnw spring-boot:run
cd libro-service && ./mvnw spring-boot:run
cd usuario-service && ./mvnw spring-boot:run
cd inventario-service && ./mvnw spring-boot:run
cd pedido-service && ./mvnw spring-boot:run
cd pago-service && ./mvnw spring-boot:run
cd resena-service && ./mvnw spring-boot:run
cd notificacion-service && ./mvnw spring-boot:run

# 4. API Gateway (último)
cd api-gateway && ./mvnw spring-boot:run
```

---

### Opción 2 — Entorno Contenerizado (Docker)

**Requisitos previos:**
- Docker Desktop instalado y en ejecución

**Despliegue completo con un solo comando:**

```bash
docker compose up --build -d
```

La primera vez tarda varios minutos (descarga imágenes + compila 11 proyectos Maven). Las siguientes veces es mucho más rápido por caché de capas.

**Para detener todos los servicios:**
```bash
docker compose down
```

**Servicios y puertos expuestos:**

| Servicio | Puerto host |
|----------|------------|
| MySQL | 3309 |
| Eureka Server | 8761 |
| Config Server | 8888 |
| API Gateway | 8090 |
| libro-service | 8083 |
| autor-service | 8084 |
| usuario-service | 8085 |
| inventario-service | 8086 |
| pedido-service | 8087 |
| pago-service | 8088 |
| resena-service | 8089 |
| notificacion-service | 8091 |

---

## 🧪 Pruebas Unitarias

El proyecto incluye pruebas unitarias para las capas **Service** y **Controller** de los microservicios asignados a cada integrante.

Para ejecutar las pruebas de un servicio:

```bash
cd autor-service && ./mvnw test
cd libro-service && ./mvnw test
```
