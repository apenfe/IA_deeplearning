clc; 
clearvars;
close all;
format short

A=[3,5;1,-2];
B=[1,2;4,5];

[m,n1]=size(A);
[n2,p]=size(B);

if(n1~=n2)

    disp('La multiplicacion de AB no puede realizarse por tamaño no adecuado')

else
        C1=A*B
        
        for i=1:m
            for j=1:n1
                C2(i,j)=A(i,:)*B(:,j);
        
            end
        end
end



C2