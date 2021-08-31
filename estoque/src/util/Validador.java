package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

import dao.PessoaDAO;

public abstract class Validador {


	// Verifica a quantidade mínima de caracteres de um campo
	public static boolean qtdeMinChar(String texto, int qtdeChar) {
		return texto.trim().length() >= qtdeChar;
		//return texto.length() >= qtdeChar; antes
		// "     " -> comprimento 5 -> "" : 0
		// "Yo Yo" -> comprimento 5 -> "YoYo" : 4 mas conta ainda " " então 5
	}
	
	// Verifica a quantidade máxima de caracteres de um campo
	public static boolean qtdeMaxChar(String texto, int qtdeChar) {
		return texto.trim().length() <= qtdeChar;
	}
	
	// Verifica um limite mínimo e máximo de caracteres
	public static boolean qtdeMinMaxChar(String texto, int qtdeMin, int qtdeMax) {
		return qtdeMinChar(texto,qtdeMin) && qtdeMaxChar(texto, qtdeMax) ;
	}	
	
	// Verifica se um e-mail é válido
	public static boolean email(String email) {
		return email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
	}
	
	// Verifica se dois valores são iguais
	public static boolean iguais(String texto1, String texto2) {
		return texto1.equals(texto2); 
	}
	
	// Verifica telefones no padrão (##)#####-#### (celular) e (##)####-#### (telefone fixo)
	public static boolean telefone(String telefone) {
		return telefone.matches("^(\\([0-9]{2}\\))([9]{1})?([0-9]{4})-([0-9]{4})$");
	}

	// Verifica se um atributo da pessoa está em uso com o valor informado
	public static boolean verificaAtributoEmUso(String atributo, String valor) {
		
		PessoaDAO pesquisaPessoa = new PessoaDAO();
		return pesquisaPessoa.verificaAtributoEmUsoPessoa(atributo, valor);
	
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//não está funcionando? quero so numeros positivos de 0 para cima
	// Verifica se está usando solmente numeros
	public static boolean numeros(String numeros) {
		//return numeros.matches("[0-9]+"); -> não?
		//return numeros.matches("\\b\\d+\\b"); -> não?
		return numeros.matches("\\d+");
	}
	
	//importar estas duas bibliotecas no inicio da classe
	//import java.text.ParseException;
	//import java.text.SimpleDateFormat;
	
	//como usar
	//Validador.data("29/02/2000"); -> true/false
	
	public static boolean data(String data) {
		
		try {
			//Crio um objeto do tipo SimpleDateFormat
			//para trabalhar com datas no padrão dia/mês/ano
			SimpleDateFormat objetoSDF = new SimpleDateFormat("dd/MM/yyyy");
			
			//difinindo que não sera aceitas datas inválidas
			objetoSDF.setLenient(false);
			
			// tentando conversa a String em um objeto do tipo date
			// se funcionar, a data é valida, caso contrário inválida
			objetoSDF.parse(data);
			
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}
	
	// importar no começo da classe
	// import java.util.InputMismatchException;

	private static String removeChars(String texto) {
		return texto.replaceAll("[^0-9]+","");
	}

	public static boolean validaCPF(String CPF) {

		// removendo . e - do CPF
		CPF = removeChars(CPF);
		
		// verificando se o CPF não é uma das sequência abaixo
		if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
			CPF.equals("22222222222") || CPF.equals("33333333333") ||
			CPF.equals("44444444444") || CPF.equals("55555555555") ||
			CPF.equals("66666666666") || CPF.equals("77777777777") ||
			CPF.equals("88888888888") || CPF.equals("99999999999") ||
			(CPF.length() != 11)) {
			return(false);
		}
		
		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
		// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			}else {
				dig10 = (char)(r + 48); // converte no respectivo caractere numerico
			}

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
			num = (int)(CPF.charAt(i) - 48);
			sm = sm + (num * peso);
			peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				 dig11 = '0';
			else dig11 = (char)(r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
				return true;
			}else {
				return false;
			}
		} catch (InputMismatchException erro) {
			return false;
		}
	}
	
	// testa se um String é um inteiro
	public static boolean isInteiro(String str) {
		if(str != null && str.matches("[0-9]*")){
			return true;
		}else {
			return false;
		}
	}
}