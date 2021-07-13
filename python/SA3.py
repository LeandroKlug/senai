print('=' * 50)
print('{:^50}'.format('Caixa Senai'))
print('=' * 50)

totalCompras = float(input('Qual o valor total da compra: R$'))
valorRecebido = float(input('Qual o valor recebido: R$'))
troco = valorRecebido - totalCompras

nota = 50
totNota = 0

print(f'Troco: R$ {troco}')

while True:
    if valorRecebido < 0:
        print('O sistema não aceita valores negativos')
        break
    elif valorRecebido < totalCompras:
        print('Valor não suficiente para pagar a compra')
        break
    else:
        if troco >= nota:
            troco -= nota
            totNota += 1
        else:
            if totNota > 0:                
                print(f'Total de {totNota} notas de R$ {nota}')
            if nota == 50:
                nota = 20
            elif nota == 20:
                nota = 10
            elif nota == 10:
                nota = 5
            elif nota == 5:
                nota = 2
            elif nota == 2:
                nota = 1
            elif nota == 1:
                nota = 0.50
            elif nota == 0.50:
                nota = 0.25
            elif nota == 0.25:
                nota = 0.10
            elif nota == 0.10:
                nota = 0.05
            elif nota == 0.05:
                nota = 0.01
            totNota = 0
            if troco == 0:
                break
