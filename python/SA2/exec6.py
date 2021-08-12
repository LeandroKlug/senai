#========================================================
# Crie um programa que converta horas em segundos, conforme o valor que o usuário informar quando solicitado a ele.

def horas_segundos(horas):
    segundos = horas * 3600
    print(f'Conversao de {horas} horas para {segundos} segundos')

horas_segundos(int(input('Para converter em segundos, digite a hora desejada: ')))