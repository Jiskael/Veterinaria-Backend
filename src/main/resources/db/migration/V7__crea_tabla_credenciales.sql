-- contrasenia VARCHAR(100): guarda el hash BCrypt (60 caracteres), no la clave en texto plano.
CREATE TABLE credenciales (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
