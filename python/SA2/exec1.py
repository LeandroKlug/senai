lista = [5,7,2,9,4,1,3]

def somaLista(lista):
    soma = 0
    for elemento in lista:
        soma += elemento
    return soma

def tamanhoLista(lista):
    count = 0
    for element in lista:
        count += 1
    return count

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

def sortAsc(lista):
    n = tamanhoLista(lista)

    for i in range(n):   
        ordenar = True

        for j in range(n - i - 1):
            if lista[j] > lista[j + 1]:

                lista[j], lista[j + 1] = lista[j + 1], lista[j]

                ordenar = False
        if ordenar:
            break

    return lista

def sortDesc(lista):
    n = tamanhoLista(lista)

    for i in range(n):   
        ordenar = True

        for j in range(n - i - 1):
            if lista[j] < lista[j + 1]:

                lista[j], lista[j + 1] = lista[j + 1], lista[j]

                ordenar = False
        if ordenar:
            break

    return lista

#============================================================

listaTamanho = tamanhoLista(lista)
print('Tamanho da lista e',listaTamanho)

listaMaiorValor = maiorValor(lista)
print('Maior valor da lista', listaMaiorValor)

listaMenorValor = menorValor(lista)
print('Menor valor da lista', listaMenorValor)

listaSoma = somaLista(lista)
print('Soma dos valores da lista', listaSoma)

listaAsc = sortAsc(lista)
print('Ordem crescente dos valores da lista', listaAsc)

listaDesc = sortDesc(lista)
print('Ordem decrescente dos valores da lista', listaDesc)
