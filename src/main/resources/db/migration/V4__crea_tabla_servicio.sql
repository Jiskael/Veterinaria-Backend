CREATE TABLE servicio (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(50) NOT NULL,
    descripcion TEXT NOT NULL,
    precio INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
