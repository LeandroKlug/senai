package util;

import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class Seguranca {

	public static String geraSenhaCriptografada(String senha) {

	    String senhaCriptografada = null;
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(senha.getBytes("utf8"));
	        senhaCriptografada = String.format("%040x", new BigInteger(1, digest.digest()));
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return senhaCriptografada;
	}
}