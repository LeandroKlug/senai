#========================================================
# Crie uma função que receba como parâmetro uma lista, com valores de qualquer tipo. 
# A função deve imprimir todos os elementos da lista, enumerando-os

def funcLista(valor):
    for posicao in range(len(valor)):
        print(posicao, valor[posicao])

listaDeValores = [1,5,8,3,1,8,5,12,4,2]

funcLista(listaDeValores)