clc; 
clearvars;
close all;
format short

A=[3,5;1,-2];
B=[1,2;4,5];

C1 = A+B

[filas,columnas]=size(A);

for i=1:filas
    for j=1:columnas
        C2(i,j)=A(i,j)+B(i,j);

    end
end

C2