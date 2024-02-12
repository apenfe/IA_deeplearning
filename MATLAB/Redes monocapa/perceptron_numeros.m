,% Recnocer carcateres decimales
% la entrada es una binarizacion de una imagen en una matriz de 5*3 bits

%Matriz de entrada con los diferentes bits que representan los diferentes
%numeros del 0 al 9

X=[0 0 1 1;
 0 1 0 1];

D=[0 0 0 1];

% Grafica de estos patrones

plotpv(X,D)

% Generar perceptron con pesos fijos

red=newp(X,D)
red.iw{1,1}=randn(1,2);
red.b{1}=randn(1,1);

% visualizacion de la superficie inicial tras crear los primeros pesos

Pesos=red.iw{1,1};
Bias=red.b{1};
plotpc(Pesos,Bias)

% Entrenamiento de la red

red=train(red,X,D)

% Visualizacion de la superficie cuando ha sido entrenada

figure;
Pesos=red.iw{1,1};
Bias=red.b{1};
plotpc(Pesos,Bias)

% Superponer los puntos en la misma figura
hold on
plot(X(1, D == 1), X(2, D == 1), 'ro'); % Puntos de clase 1 en rojo
plot(X(1, D == 0), X(2, D == 0), 'bx'); % Puntos de clase 0 en azul
hold off

% Prueba de la salida de a red patron de prueba [0,0]

in_prueba=[0;0];
a=sim(red,in_prueba)

% salida esperada a = 0

% Prueba de la salida de a red patron de prueba [1,1]

in_prueba=[1;1];
a=sim(red,in_prueba)

% salida esperada a = 0

% Exportacion de la red Simulink
gensim(red)
