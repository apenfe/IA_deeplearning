-- Creación de la base de datos
CREATE DATABASE redes_neuronales;
USE redes_neuronales;

-- Tabla network (redes neuronales)
CREATE TABLE red (
    nombre VARCHAR(255) PRIMARY KEY,
    numCapas INT NOT NULL,
    descripcion TEXT
);

-- Tabla layer (capas)
CREATE TABLE capa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    red_nombre VARCHAR(255),
    ordenCapa INT NOT NULL,
    numNeuronas INT,
    numNeuronasCapaAnterior INT,
    funcion INT,
    FOREIGN KEY (red_nombre) REFERENCES red(nombre)
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