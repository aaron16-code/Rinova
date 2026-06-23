CREATE TABLE IF NOT EXISTS generos (
    codigo VARCHAR(10) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT
    );

CREATE TABLE IF NOT EXISTS libros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(50),
    genero_id VARCHAR(10),
    anio_publicacion INT,
    precio DOUBLE,
    autor_id BIGINT,
    FOREIGN KEY (genero_id) REFERENCES generos(codigo)
    );