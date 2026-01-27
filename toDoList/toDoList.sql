CREATE DATABASE IF NOT EXISTS taskdb;
USE taskdb;

CREATE TABLE IF NOT EXISTS tasks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  completed BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insertar datos de ejemplo
INSERT INTO tasks (title, description, completed) VALUES
('Completar práctica de Cloud Computing', 'Desarrollar aplicación full stack con Docker Compose', false),
('Estudiar orquestación de contenedores', 'Aprender sobre Docker Compose y Kubernetes', false),
('Preparar informe técnico', 'Documentar todo el proceso de desarrollo', false);


select * from tasks;