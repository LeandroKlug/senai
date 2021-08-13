def tamanhoLista(lista):
    count = 0
    for element in lista:
        count += 1
    return count

def funcLista(valor):
    for posicao in range(tamanhoLista(valor)):
        print(posicao, valor[posicao])

listaDeValores = ['a',5,8.15,3,True,8,5,3.1415,['um', 'dois', 'três'],2]

funcLista(listaDeValores)