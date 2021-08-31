package util;

import javax.swing.text.MaskFormatter;

public abstract class UtilMascara {

	public static final String mCpf = "###.###.###-##";
	public static final String mTel = "(##)####-####";
	public static final String mCel = "(##)#####-####";
	public static final String mDat = "##/##/##";
	public static final String mCep = "##.###-##";

	public static MaskFormatter add(String tipo) {

		String mascara = retornaMascara(tipo);
		MaskFormatter mascaraAplicar = new MaskFormatter();
		try {
			mascaraAplicar.setMask(mascara);
			mascaraAplicar.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mascaraAplicar;
	}

	private static String retornaMascara(String tipo) {

		String mascara;
		switch (tipo) {
		case "cpf":
			mascara = mCpf;
			break;
		case "cel":
			mascara = mCel;
			break;
		case "tel":
			mascara = mTel;
			break;
		case "dat":
			mascara = mDat;
			break;
		case "cep":
			mascara = mCep;
			break;
		default:
			mascara = "";
			break;
		}
		return mascara;
	}
};