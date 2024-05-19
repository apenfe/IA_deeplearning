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

-- Crear la tabla data_set
CREATE TABLE data_set (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Crear la tabla numbers
CREATE TABLE numbers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_set_id INT,
    value DECIMAL(10, 5),
    FOREIGN KEY (data_set_id) REFERENCES data_set(id)
);

-- Insertar un conjunto de datos
INSERT INTO data_set (name, description) VALUES ('DataSet 1', 'Descripción del DataSet 1');

-- Insertar números asociados con este conjunto de datos
INSERT INTO numbers (data_set_id, value) VALUES (1, 123.45);
INSERT INTO numbers (data_set_id, value) VALUES (1, 678.90);
INSERT INTO numbers (data_set_id, value) VALUES (1, 234.56);
-- Continúa insertando más números según sea necesario