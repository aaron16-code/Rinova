CREATE TABLE IF NOT EXISTS libros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(50),
    genero VARCHAR(100),
    anio_publicacion INT,
    precio DOUBLE,
    autor_id BIGINT
    );