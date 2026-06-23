CREATE TABLE IF NOT EXISTS pedidos (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      usuario_id BIGINT NOT NULL,
      libro_id BIGINT NOT NULL,
      cantidad INT NOT NULL,
      total DOUBLE,
      fecha DATE,
      estado VARCHAR(30)
    );