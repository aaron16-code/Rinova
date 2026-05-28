CREATE TABLE IF NOT EXISTS autores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL,
    anio_nacimiento INT NOT NULL
    );