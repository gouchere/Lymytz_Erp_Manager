package lym.com.api.service.commons;

public class ConstantesManager {
	public static String FILE_SEPARATOR = System.getProperty("file.separator");
	public static String NAME_CONFIG_SERVEUR_APPS = "config_serveur.properties";

	public static byte[] ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/".getBytes();

	public static String MD5_ENCRIPT_METHODE = "MD5";
	public static String MD5S_ENCRIPT_METHODE = "MD5S";
	public static String PBKDF2_ENCRIPT_METHODE = "PBKDF2";
	public static String BCRYPT_ENCRIPT_METHODE = "BCRYPT";
	public static String SCRYPT_ENCRIPT_METHODE = "SCRYPT";

	public static boolean isNumeric(Object val) {
		return val !=null?isNumeric(val.toString()):false;
	}
		
	public	static boolean isNumeric(String val) {
		if(isString(val)) {
			try {
				Float.valueOf(val);
				return true;
			}catch(Exception ex) {}
		}
		return false;
	}

	public static boolean isLong(Long val) {
		return (val != null) ? val > 0 : false;
	}

	public static boolean isInteger(Integer val) {
		return (val != null) ? val > 0 : false;
	}

	public static boolean isString(String val) {
		return (val != null) ? !val.trim().isEmpty() : false;
	}

	public static boolean isDouble(Double val) {
		return (val != null) ? val > -1 : false;
	}

}
