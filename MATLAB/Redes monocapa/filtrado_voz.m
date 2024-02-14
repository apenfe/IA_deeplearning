% Voz contaminada con señal de alta freciencia

% Se cargan los datos de la señal de voz que se desea filtrar desde archivo
% *.mat

% Cargar el archivo de audio WAV
% Cargar el archivo de audio
[A, Fs] = audioread('A.wma');

% Obtener el número de muestras del archivo de audio
numero_muestras_A = length(A);

% Cargar el archivo de audio para la señal de ruido
ruido = -0.05 + 0.1 * sin(2*pi*10000*(0:numero_muestras_A-1)/Fs)';

% Verificar si la señal de ruido tiene más muestras que la señal de voz
if length(ruido) > numero_muestras_A
    % Truncar la señal de ruido si tiene más muestras
    ruido = ruido(1:numero_muestras_A);
elseif length(ruido) < numero_muestras_A
    % Extender la señal de ruido si tiene menos muestras
    ruido = [ruido; zeros(numero_muestras_A - length(ruido), 1)];
end

% Se contamina voz con el ruido

voz_contaminada=A+ruido;
obj_audio=audioplayer(voz_contaminada,Fs);
play(obj_audio);
time=(0:370660)/Fs;


P=con2seq(voz_contaminada);
T=con2seq(ruido);



% Se crea red Adaline con 15 retardos

net=newlin([-0.2 0.2],1,0:15,0.01);

% Entrenamiento

[net,Y,E,Pf]=adapt(net,P,T);

% Visualizacion señal

plot(time,cat(2,Y{:}),'b',time,cat(2,T{:}),'r',time,cat(2,E{:}),'g',[1 2.5],[0 0],'k')
legend({'Salida','Salida deseada','voz filtrada'})
Econ=seq2con(E);
obj_audio=audioplayer(Econ{1,1},Fs);
play(obj_audio);