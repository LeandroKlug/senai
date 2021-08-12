lista = []

print("\n")
for i in range(0, 20):
    print("Digite o valor desejado na posicao", i, )
    item = int(input())
    lista.append(item)

def tamanhoLista(lista):
    count = 0
    for element in lista:
        count += 1
    return count

def media(lista):
    soma = 0
    for elemento in lista:
        soma += elemento
    media = soma / tamanhoLista(lista)
    return media

def maiorValor(lista):
    maior = lista[0]
    for valor in lista:
        if valor > maior:
            maior = valor
    return maior

def menorValor(lista):
    menor = lista[0]
    for valor in lista:
        if valor < menor:
            menor = valor
    return menor

print("Lista completa ", lista)
print("Média da lista ", media(lista))
print("Maior valor da lista ", maiorValor(lista))
print("Menor valor da lista ", menorValor(lista))