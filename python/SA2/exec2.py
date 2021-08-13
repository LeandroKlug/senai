lista = []

def somaLista(lista):
    soma = 0
    for elemento in lista:
        soma += elemento
    return soma

for i in range(0, 4):
    num = int(input('Insira um número: '))
    lista.append(num)
somaMedia = somaLista(lista) / 4

if somaMedia <= 1:
    print(f'A media e de {somaMedia} esse valor e negativo') 
else:
    print(f'A media e de {somaMedia} esse valor e positivo') 
  