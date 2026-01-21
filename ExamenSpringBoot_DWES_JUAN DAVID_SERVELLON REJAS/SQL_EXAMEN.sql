-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS examen_spring_dwes
 CHARACTER SET utf8mb4
 COLLATE utf8mb4_unicode_ci;
USE examen_spring_dwes;

-- Tabla EQUIPOS
CREATE TABLE IF NOT EXISTS equipos (
 id BIGINT NOT NULL AUTO_INCREMENT,
 nombre_equipo VARCHAR(100) NOT NULL,
 sede VARCHAR(100) NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB;

INSERT INTO equipos (id, nombre_equipo, sede) 
VALUES (1, 'Racing', 'Santander'),
(2, 'Real Madrid', 'Madrid'),
(3, 'Barcelona', 'Barcelona'),
(4, 'Alianza Lima', 'Lima');

-- Tabla JUGADORES
CREATE TABLE IF NOT EXISTS jugadores (
 id BIGINT NOT NULL AUTO_INCREMENT,
 dorsal INT NOT NULL,
 nombre VARCHAR(100) NOT NULL,
 apellido1 VARCHAR(100) NOT NULL,
 apellido2 VARCHAR(100) NOT NULL,
 posiciones VARCHAR(255) NOT NULL,
 equipo_id BIGINT NOT NULL,
 PRIMARY KEY (id),
 INDEX idx_jugadores_equipo (equipo_id),
 CONSTRAINT fk_jugadores_equipos
 FOREIGN KEY (equipo_id)
 REFERENCES equipos(id)
 ON UPDATE CASCADE
 ON DELETE RESTRICT,
 UNIQUE KEY uk_jugadores_equipo_dorsal (equipo_id, dorsal)
) ENGINE=InnoDB;

INSERT INTO jugadores (dorsal, nombre, apellido1, apellido2, posiciones, equipo_id) 
VALUES (10, 'Sergio', 'Marin', 'Perez', 'DEFENSA', 1),
(5, 'Enrique', 'Sainz', 'Sainz', 'DEFENSA', 1),
(11, 'Juan', 'Gonzales', 'Noriega', 'MEDIO', 2),
(19, 'Pedro', 'Salaverry', 'Guzman', 'DEFENSA', 2),
(2, 'Manuel', 'Rejas', 'Pinto', 'DELANTERO', 3),
(15, 'Alex', 'Argumosa', 'Salazar', 'MEDIO', 3),
(4, 'Andres', 'Picanto', 'Kia', 'DEFENSA', 4),
(12, 'Gonzalo', 'Servellon', 'Bastidas', 'DELANTERO', 4),
(20, 'Felix', 'Quispe', 'Mamani', 'MEDIO', 4);

-- Tabla ÁRBITROS
CREATE TABLE IF NOT EXISTS arbitros (
 id BIGINT NOT NULL AUTO_INCREMENT,
 nombre VARCHAR(100) NOT NULL,
 apellido1 VARCHAR(100) NOT NULL,
 apellido2 VARCHAR(100) NOT NULL,
 rol ENUM('PRINCIPAL', 'ASISTENTE') NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB;

INSERT INTO arbitros (nombre, apellido1, apellido2, rol)
VALUES ('Carlos', 'Martinez', 'Lopez', 'PRINCIPAL'),
('Juan', 'Garcia', 'Fernandez', 'ASISTENTE'),
('Roberto', 'Sanchez', 'Torres', 'PRINCIPAL'),
('Miguel', 'Diaz', 'Romero', 'ASISTENTE');

-- Tabla PARTIDOS
CREATE TABLE IF NOT EXISTS partidos (
 id BIGINT NOT NULL AUTO_INCREMENT,
 equipo1_id BIGINT NOT NULL,
 equipo2_id BIGINT NOT NULL,
 arbitro1_id BIGINT NOT NULL,
 arbitro2_id BIGINT NOT NULL,
 PRIMARY KEY (id),
 INDEX idx_partidos_equipo1 (equipo1_id),
 INDEX idx_partidos_equipo2 (equipo2_id),
 INDEX idx_partidos_arbitro1 (arbitro1_id),
 INDEX idx_partidos_arbitro2 (arbitro2_id),
 CONSTRAINT fk_partidos_equipo1
 FOREIGN KEY (equipo1_id)
 REFERENCES equipos(id)
 ON UPDATE CASCADE
 ON DELETE RESTRICT,
 CONSTRAINT fk_partidos_equipo2
 FOREIGN KEY (equipo2_id)
 REFERENCES equipos(id)
 ON UPDATE CASCADE
 ON DELETE RESTRICT,
 CONSTRAINT fk_partidos_arbitro1
 FOREIGN KEY (arbitro1_id)
 REFERENCES arbitros(id)
 ON UPDATE CASCADE
 ON DELETE RESTRICT,
 CONSTRAINT fk_partidos_arbitro2
 FOREIGN KEY (arbitro2_id)
 REFERENCES arbitros(id)
 ON UPDATE CASCADE
 ON DELETE RESTRICT
) ENGINE=InnoDB;
