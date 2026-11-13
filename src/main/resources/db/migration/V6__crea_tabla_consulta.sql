CREATE TABLE consulta (
    id INT NOT NULL AUTO_INCREMENT,
    id_duenio INT NOT NULL,
    id_mascota INT NOT NULL,
    id_servicio INT NOT NULL,
    motivo TEXT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
