# Rinova — Dockerización

Este proyecto ya está dockerizado siguiendo el mismo patrón usado en `sistema-duoc`:
build multi-stage con Maven + JDK 25, un contenedor MySQL con todas las bases de
datos, y los microservicios obteniendo su configuración (puertos, datasource,
Eureka) desde el `config-server` (perfil `native`).

## Cómo levantar todo

```bash
docker compose up --build
```

La primera vez tardará varios minutos (descarga imágenes base + compila 11
proyectos Maven). Las siguientes veces será mucho más rápido por el cache de
capas de Docker.

## Servicios y puertos expuestos

| Servicio              | Puerto host | Notas                          |
|------------------------|------------|----------------------------------|
| mysql-db               | 3309       | usuario root / pass root         |
| eureka-server           | 8761       | http://localhost:8761            |
| config-server           | 8888       | http://localhost:8888            |
| api-gateway              | 8090       | punto de entrada único           |
| libro-service             | 8083      |                                  |
| autor-service              | 8084      |                                 |
| usuario-service              | 8085    |                                 |
| inventario-service             | 8086  |                                 |
| pedido-service                   | 8087|                                 |
| pago-service                       | 8088|                               |
| resena-service                       | 8089|                             |
| notificacion-service                   | 8091|                         |

Todo el tráfico externo debería entrar por el `api-gateway` (puerto 8090) usando
las rutas documentadas en el `README.txt` original, pero cada microservicio
también queda accesible directamente en su puerto por si quieres probarlo o
depurarlo de forma aislada.

## Qué se modificó para poder dockerizar

1. **Dockerfile por servicio** (11 en total): build multi-stage idéntico al de
   `sistema-duoc` (`maven:3.9.11-eclipse-temurin-25` para compilar,
   `eclipse-temurin:25-jre` para ejecutar).
2. **`config-server/config-microservicios/*.properties`**: los valores de
   `spring.datasource.url/username/password` y
   `eureka.client.service-url.defaultZone` estaban hardcodeados a `localhost`.
   Se cambiaron por placeholders (`${SPRING_DATASOURCE_URL}`, etc.) que cada
   contenedor resuelve con sus propias variables de entorno — exactamente como
   ya lo hace `sistema-duoc`.
3. **`init-db/init.sql`**: crea las 8 bases de datos necesarias
   (`bd_autores`, `bd_libros`, `bd_usuarios`, `bd_inventario`, `bd_pedidos`,
   `bd_pagos`, `bd_resenas`, `bd_notificaciones`) al iniciar el contenedor MySQL.
4. **Bug real encontrado y corregido**: `pedido-service` llamaba a
   `libro-service` y `usuario-service` vía Feign con URLs hardcodeadas
   (`http://localhost:8083` / `:8085`). Eso funcionaba en local pero rompía la
   comunicación entre contenedores Docker (cada contenedor tiene su propio
   `localhost`). Se cambió a `${libro-service.url}` / `${usuario-service.url}`,
   configurables por variable de entorno (`LIBRO_SERVICE_URL`,
   `USUARIO_SERVICE_URL`), con fallback a `localhost` para que el modo local
   sin Docker siga funcionando igual que antes.
5. **`docker-compose.yml`**: orquesta los 11 servicios + MySQL, con
   `depends_on`/healthcheck para evitar condiciones de carrera al iniciar.

## Nota menor (sin impacto funcional)

`autor-service`, `libro-service`, `usuario-service`, `inventario-service` y
`pedido-service` tienen su carpeta de migraciones Flyway llamada
`db.migration` (con punto) en vez de `db/migration` (con barra), que es lo que
espera `spring.flyway.locations=classpath:db/migration`. Como
`spring.jpa.hibernate.ddl-auto=update` ya crea las tablas automáticamente vía
Hibernate, y esas migraciones solo contienen `CREATE TABLE` (sin datos
semilla), esto no rompe nada — pero si en el futuro agregan migraciones con
datos, conviene renombrar la carpeta a `db/migration`.

## Variables de entorno usadas (resumen)

Cada microservicio de negocio recibe:

- `SPRING_CONFIG_IMPORT=configserver:http://config-server:8888`
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://eureka-server:8761/eureka/`
- `SPRING_DATASOURCE_URL` / `SPRING_DATASOURCE_USERNAME` / `SPRING_DATASOURCE_PASSWORD`

`pedido-service` además recibe `LIBRO_SERVICE_URL` y `USUARIO_SERVICE_URL`.

## Apagar y limpiar

```bash
docker compose down          # detener
docker compose down -v       # detener y borrar también el volumen de MySQL
```
