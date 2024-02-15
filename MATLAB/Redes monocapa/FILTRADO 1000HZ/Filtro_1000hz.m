% Voz contaminada con señal de alta freciencia

clc; 
clearvars;
close all;

% Se cargan los datos de la señal de voz que se desea filtrar desde archivo
% *.mat

% Configurar la grabación de audio
Fs = 44100; % Frecuencia de muestreo en Hz (por ejemplo, 44100 Hz para audio de calidad de CD)
duracion = 10; % Duración de la grabación en segundos

% Cargar el archivo de audio WAV
% Cargar el archivo de audio
[original, Fs] = audioread('ORIGINAL.mp3');
[ruido, Fs] = audioread('RUIDO.mp3');
[mezcla, Fs] = audioread('MEZCLA.mp3');
area(mezcla,'DisplayName','mezcla')

% Se contamina voz con el ruido

obj_audio=audioplayer(mezcla,Fs);
play(obj_audio);

P=con2seq(mezcla');
T=con2seq(original');



% Se crea red Adaline con 15 retardos

net=newlin([-2 2],1,0:15,0.01);

% Entrenamiento

[net,Y,E,Pf]=adapt(net,P,T);

% Visualizacion señal

plot(time,cat(2,Y{:}),'b',time,cat(2,T{:}),'r',time,cat(2,E{:}),'g',[1 2.5],[0 0],'k')
legend({'Salida','Salida deseada','voz filtrada'})
Econ=seq2con(E);
obj_audio=audioplayer(Econ{1,1},Fs);
play(obj_audio);