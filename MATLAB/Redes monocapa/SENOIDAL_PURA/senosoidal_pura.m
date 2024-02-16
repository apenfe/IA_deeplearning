% Programa que filtra una señal senoidal contaminada con ruido de alta
% frecuencia

% Definicion de la señales a procesar

time = 1:0.01:5;

% Señal contaminada
X = sin(2*pi*2*time)+0.5*sin(2*pi*24*time);
P = con2seq(X);

% Señal objetivo

Taux=sin(2*pi*2*time);
T=con2seq(Taux);

figure
plot(time, cat(2,P{:}), time,cat(2,T{:}),'--')
title('Señales de entrada y salida deseada')
xlabel('Tiempo')
legend({'Entrada','Salida deseada'})

% Creamos una red ADALINE con 5 retardos
net=newlin([-2 2],1,[0 1 2 3 4 5],0.1);

net.biasConnect=0;

% Entrenamiento de la red

[net,Y,E,pf]=adapt(net,P,T);
figure
% Visualizacion de las señales trabajadas de la red
plot(time,cat(2,Y{:}),'b',time,cat(2,T{:}),'r',time,cat(2,E{:}),'g')