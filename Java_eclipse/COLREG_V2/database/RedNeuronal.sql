-- Creación de la base de datos
CREATE DATABASE redes_neuronales;
USE redes_neuronales;

-- Tabla network (redes neuronales)
CREATE TABLE red (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    numCapas INT NOT NULL,
    descripcion TEXT
);

-- Tabla layer (capas)
CREATE TABLE capa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    red_id INT,
    ordenCapa INT NOT NULL,
    numNeuronas INT,
    numNeuronasCapaAnterior INT,
    funcion INT,
    FOREIGN KEY (red_id) REFERENCES red(id)
);

-- Tabla neuron (neuronas)
CREATE TABLE neurona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    capa_id INT,
    ordenNeurona INT NOT NULL,
    FOREIGN KEY (capa_id) REFERENCES capa(id)
);