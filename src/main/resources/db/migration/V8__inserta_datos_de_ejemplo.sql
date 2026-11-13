-- Usuario admin: email admin@gmail.com / clave 123456 (guardada con BCrypt)
INSERT INTO credenciales (nombre, email, contrasenia) VALUES
    ('admin', 'admin@gmail.com', '$2a$10$DbDXLlLB.snrxMLSF3d3f.0cGNHJhjgNyzrM99SXlyjk6YJ1XgYhm');

INSERT INTO duenio (rut, nombre, apellido, email, telefono) VALUES
    ('12345678-9', 'María', 'González', 'maria.gonzalez@gmail.com', 912345678),
    ('9876543-2', 'Pedro', 'Soto', 'pedro.soto@gmail.com', 987654321);

INSERT INTO mascota (nombre, especie) VALUES
    ('Rocky', 'Perro'),
    ('Mishi', 'Gato'),
    ('Toby', 'Perro');

INSERT INTO consulta (id_duenio, id_mascota, id_servicio, motivo) VALUES
    (1, 1, 2, 'Vacuna anual'),
    (1, 2, 1, 'Decaimiento y poco apetito'),
    (2, 3, 4, 'Desparasitación trimestral');
