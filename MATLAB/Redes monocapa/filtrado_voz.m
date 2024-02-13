% Voz contaminada con señal de alta freciencia

% Se cargan los datos de la señal de voz que se desea filtrar desde archivo
% *.mat

% Cargar el archivo de audio WAV
voz_ori = audioread("filtrado_voz_Adaline.wav");

% Se genera la señal de ruido

ruido = -0.05+0.1*sin(2*pi*10000*[0:33074]/Fs);
ruido=ruido';

% Se contamina voz con el ruido

voz_contaminada=voz_ori+ruido;
obj_audio=audioplayer(voz_contaminada,Fs);
play(obj_audio);
time=[0:33074]/Fs;

X=ruido';
P=con2seq(X);
T=con2seq(voz_contaminada');

% Se crea red Adaline con 15 retardos

net=newlin([-0.2 0.2],1,[0:15],0.01);

% Entrenamiento

[net,Y,E,Pf]=adapt(net,P,T);

% Visualizacion señal

plot(time,cat(2,Y{:}),'b',time,cat(2,T{:}),'r',time,cat(2,E{:}),'g',[1 2.5],[0 0],'k')
legend({'Salida','Salida deseada','voz filtrada'})
Econ=seq2con(E)
obj_audio=audioplayer(Econ{1,1},Fs);
play(obj_audio);