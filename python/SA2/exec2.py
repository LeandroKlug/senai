num1 = int(input('Insira o primeiro número: '))
num2 = int(input('Insira o segundo número: '))
num3 = int(input('Insira o terceiro número: '))
num4 = int(input('Insira o quarto número: '))

somaMedia = (num1 + num2 + num3 + num4) / 4

if somaMedia <= 1:
    print(f'A media e de {somaMedia} esse valor e negativo') 
else:
    print(f'A media e de {somaMedia} esse valor e positivo')   