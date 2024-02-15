
 //Neuronas de entrada
 const int N = 5;
 //Neuronas de salida
 const int M = 1;

 const float pi = 3.1416;

 // Definicion de muestras que se van a usar para el filtrado de la señal
 // Son la entrada para la red neuronal

 float X_K=0;
 float X_K_1=0;
 float X_K_2=0;
 float X_K_3=0;
 float X_K_4=0;

 // Salida deseada

 float D;

 // Razon de aprendizaje

 float alfa = 0.1;

 // Matriz con los pesos y bias

 float Pesos[M][N+1]={
  {1.0,1.0,1.0,1.0,1.0,1.0}
 };

 int i,j;
 float Neta;
 float SalidaRed[M];
 float EntradaRed[N];

void setup() {
  
  Serial.begin(9600);

}

void loop() {
  
  float Salida;
  int Tiempo;
  float Cuadrada;
  float Contaminada;
  long Entrada = millis();

  Tiempo=Entrada/1000;

  //Señal cuadrada sin contaminacion

    Cuadrada=  sin(pi*0.005*Tiempo);
  
  // Señal cuadrada contaminada

      Contaminada=-0.1+(0.20*sin(pi*0.50*Tiempo));
  

  // Muestra actual de la señal a filtrar

  X_K=Contaminada;

  // Definicion entradas red
  EntradaRed[0]=X_K;
  EntradaRed[1]=X_K_1;
  EntradaRed[2]=X_K_2;
  EntradaRed[3]=X_K_3;
  EntradaRed[4]=X_K_4;
  D=Cuadrada;

  // calculo de la salida de red

  for(i=0; i<M; i++){

    Neta=Pesos[i][N];

    for(j=0; j<N; j++){
    
      Neta+=Pesos[i][j]*EntradaRed[j];

    }

    SalidaRed[i]=Neta;

  }

    Salida=SalidaRed[0];

    // actualizar pesos

    Pesos[0][0]=Pesos[0][0]+alfa*(D-Salida)*X_K;
    Pesos[0][1]=Pesos[0][1]+alfa*(D-Salida)*X_K_1;
    Pesos[0][2]=Pesos[0][2]+alfa*(D-Salida)*X_K_2;
    Pesos[0][3]=Pesos[0][3]+alfa*(D-Salida)*X_K_3;
    Pesos[0][4]=Pesos[0][4]+alfa*(D-Salida)*X_K_4;
    Pesos[0][5]=Pesos[0][5]+alfa*(D-Salida);

    // ACTUALIZAR MUESTRAS SIGUIENTE ITERACION

X_K_4=X_K_3;
X_K_3=X_K_2;
X_K_2=X_K_1;
X_K_1=X_K;

Serial.print("\t");
Serial.print(Salida);
Serial.print("\t");
Serial.print(Contaminada);
Serial.print("\t");
Serial.println(Cuadrada);
delay(600);

}