lista1 = [5,7,2,9,4,1,3]

# a) Tamanho da lista
listaTamanho = len(lista1)
print('Tamanho da lista e',listaTamanho)

# b) Maior valor da lista
listaMaiorValor = max(lista1)
print('Maior valor da lista', listaMaiorValor)

# c) Menor valor da lista
listaMenorValor = min(lista1)
print('Menor valor da lista', listaMenorValor)

# d) Soma de todos os valores da lista
listaSoma = sum(lista1)
print('Soma dos valores da lista', listaSoma)

# e) Listar os valores da lista em ordem crescente
listaAsc = sorted(lista1)
print('Ordem crescente dos valores da lista', listaAsc)

# e) Listar os valores da lista em ordem decrescente
listaDesc = sorted(lista1, reverse=True)
print('Ordem decrescente dos valores da lista', listaDesc)

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

#========================================================
# Escreva um programa que leia 20 valores inteiros e informe a média deles, o maior e o menor valor

listaNum = []

print("\n")
for i in range(0, 20):
    print("Digite o valor desejado na posicao", i, )
    item = int(input())
    listaNum.append(item)

print("Lista completa ", listaNum)
print("Soma da lista ", sum(listaNum))
print("Maior valor da lista ", max(listaNum))
print("Menor valor da lista ", min(listaNum))

#========================================================
# Crie uma função para desenhar uma linha, usando o caractere '_' (underline). 
# O tamanho da linha deve ser definido na chamada da função

def linha(n):
 print("_" * n)

linha(int(input("Qual o tamanho da linha: ")))

#========================================================
# Crie uma função que receba como parâmetro uma lista, com valores de qualquer tipo. 
# A função deve imprimir todos os elementos da lista, enumerando-os

def funcLista(valor):
    for posicao in range(len(valor)):
        print(posicao, valor[posicao])

listaDeValores = [1,5,8,3,1,8,5,12,4,2]

funcLista(listaDeValores)

#========================================================
# Crie um programa que converta horas em segundos, conforme o valor que o usuário informar quando solicitado a ele.

def horas_segundos(horas):
    segundos = horas * 3600
    print(f'Conversao de {horas} horas para {segundos} segundos')

horas_segundos(int(input('Para converter em segundos, digite a hora desejada: ')))

#========================================================
lista7 = ["     *", "    * *", "   *   *", "  *     *", " **** ****", "    * *", "    * *"]

for item in lista7:
    print(item)