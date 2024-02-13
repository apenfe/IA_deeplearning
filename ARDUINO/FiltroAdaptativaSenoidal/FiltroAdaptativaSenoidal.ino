 #include <Math.h>

 //Neuronas de entrada
 const int N = 4;
 //Neuronas de salida
 const int M = 1;

 const float pi = 3.1416;

 // Definicion de muestras que se van a usar para el filtrado de la señal
 // Son la entrada para la red neuronal

 float X_K=0;
 float X_K_1=0;
 float X_K_2=0;
 float X_K_3=0;

 // Salida deseada

 float D;

 // Razon de aprendizaje

 float alfa = 0.23;

 // Matriz con los pesos y bias

 float Pesos[M][N+1]={
  {1.0,1.0,1.0,1.0,1.0}
 };

 int i,j;
 float Neta;
 float SalidaRed[M];
 float EntradaRed[N];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:

}
