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