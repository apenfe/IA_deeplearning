% Definir el tiempo
time = 0:0.01:5;

% Cargar los datos de audio
load('matlab.mat', 'cancion'); % Si 'matlab.mat' contiene datos de audio

% Señal contaminada
X = cancion + 0.5*sin(2*pi*24*time);

% Señal deseada (sin contaminación)
T = cancion;

% Visualizar las señales de entrada y salida deseada
figure
plot(time, X, time, T, '--')
title('Señales de entrada y salida deseada')
xlabel('Tiempo')
legend({'Entrada','Salida deseada'})

% Crear una red ADALINE con 5 retardos
net = newlin([-2 2], 1, [0 1 2 3 4 5], 0.1);
net.biasConnect = 0;

% Entrenamiento de la red
[net, Y, E, pf] = adapt(net, X', T');

% Visualización de las señales procesadas por la red
figure
plot(time, cat(2, Y{:}), 'b', time, T, 'r', time, cat(2, E{:}), 'g')
