
#========================================================
# Faça um programa que leia 4 valores, calcule a média e 
# imprima positivo ou negativo (para ser positivo a média deve ser acima de 1)

num1 = int(input('Insira o primeiro número: '))
num2 = int(input('Insira o segundo número: '))
num3 = int(input('Insira o terceiro número: '))
num4 = int(input('Insira o quarto número: '))

somaMedia = num1 + num2 + num3 + num4

if somaMedia <= 1:
    print(f'A media e de {somaMedia} esse valor e negativo') 
else:
    print(f'A media e de {somaMedia} esse valor e positivo')   