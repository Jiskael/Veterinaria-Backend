-- Indices y llaves foráneas de la tabla "consulta"
ALTER TABLE consulta
    ADD KEY fk_consulta_duenio (id_duenio),
    ADD KEY fk_consulta_mascota (id_mascota),
    ADD KEY fk_consulta_servicio (id_servicio);

ALTER TABLE consulta
    ADD CONSTRAINT consulta_ibfk_1 FOREIGN KEY (id_duenio)
    REFERENCES duenio (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT consulta_ibfk_2 FOREIGN KEY (id_mascota)
    REFERENCES mascota (id) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT consulta_ibfk_3 FOREIGN KEY (id_servicio)
    REFERENCES servicio (id) ON DELETE CASCADE ON UPDATE CASCADE;

-- El email de login no se puede repetir
ALTER TABLE credenciales
    ADD UNIQUE KEY uk_credenciales_email (email);
