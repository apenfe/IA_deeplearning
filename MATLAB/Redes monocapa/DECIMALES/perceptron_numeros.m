% Reconocer caracteres decimales
% La entrada es una binarización de una imagen en una matriz de 5*3 bits

% Matriz de entrada con los diferentes bits que representan los diferentes numeros del 0 al 9
Xaux=[1 1 1 1 0 1 1 0 1 1 0 1 1 1 1; % cero
    0 0 1 0 0 1 0 0 1 0 0 1 0 0 1; % uno
    1 1 1 0 0 1 1 1 1 1 0 0 1 1 1; % dos
    1 1 1 0 0 1 0 1 1 0 0 1 1 1 1; % tres
    1 0 1 1 0 1 1 1 1 0 0 1 0 0 1; % cuatro
    1 1 1 1 0 0 1 1 1 0 0 1 1 1 1; % cinco
    1 1 1 1 0 0 1 1 1 1 0 1 1 1 1; % seis
    1 1 1 0 0 1 0 0 1 0 0 1 0 0 1; % siete
    1 1 1 1 0 1 1 1 1 1 0 1 1 1 1; % ocho
    1 1 1 1 0 1 1 1 1 0 0 1 1 1 1]; % nueve

% Se transpone la matriz para que quede con las dimensiones que la red neuronal necesita
X = Xaux';

% Salida deseada de la red neuronal, corresponde al código binario del número decimal respectivo codificado en 4 bits
Daux=[0 0 0 0;
    0 0 0 1;
    0 0 1 0;
    0 0 1 1;
    0 1 0 0;
    0 1 0 1;
    0 1 1 0;
    0 1 1 1;
    1 0 0 0;
    1 0 0 1];

% Se transpone la matriz para que quede con las dimensiones que la red neuronal necesita
D = Daux';

% Se crea la red y se entrena
Red = newp(X, D);
Red = train(Red, X, D);

% Validación de la red, se pide digitar un patrón
disp('Para validar la red, digite el vector de patrones de entrada')
disp('Número de 15 binarios entre []')

% Patrón de entrada
X1 = input('X1 = ');

% Simulación de la red
Y = sim(Red, X1');
disp('El número resultante, en binario, leído de arriba para abajo es:')
Y

gensim(Red)



