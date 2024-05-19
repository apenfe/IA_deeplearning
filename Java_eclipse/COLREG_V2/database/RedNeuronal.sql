-- Creación de la base de datos
create DATABASE redes_neuronales;
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
CREATE TABLE adn (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Crear la tabla numbers
CREATE TABLE gen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_red varchar(255) NOT NULL,
    data_set_id INT,
    value DECIMAL(10, 5),
    FOREIGN KEY (adn_id) REFERENCES adn(id)
);

-- Crear la tabla entornos
CREATE TABLE entorno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    alto DOUBLE,
    ancho DOUBLE,
    entradaX DOUBLE,
    entradaY DOUBLE,
    salidaX DOUBLE,
    salidaY DOUBLE,
    paso DOUBLE,
    areaAprox DOUBLE
);