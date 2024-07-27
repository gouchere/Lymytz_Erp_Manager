package lym.com.api.service.authentification;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lym.com.api.service.commons.ConstantesManager;

@Component
public class EncryptPassword implements IHashPassWord {
	private static final Logger logger = LoggerFactory.getLogger(EncryptPassword.class);

	public EncryptPassword() {
	}

	@Override
	public MotDePasse hashPassword(String password, String methode) {
		methode = (methode != null) ? methode : "PBKDF2";
		MotDePasse mdp = new MotDePasse();
		switch (methode) {
		case "MD5":
			mdp.setPassword(hashMd5(password));
			break;
		case "MD5S":
			String salt = getSalt();
			mdp.setPassword(hashMd5(salt + password + salt));
			mdp.setSalt(salt);
			break;
		case "PBKDF2":
			break;
		case "BCRYPT":
			break;
		case "SCRYPT":
			break;
		}
		return mdp;
	}

	@Override
	public MotDePasse decryptPassword(String password, String methode, String salt) {
		methode = (methode != null) ? methode : "PBKDF2";
		MotDePasse mdp = new MotDePasse();
		switch (methode) {
		case "MD5":
			mdp.setPassword(hashMd5(password));
			break;
		case "MD5S":
			mdp.setPassword(hashMd5(salt + password + salt));
			mdp.setSalt(salt);
			break;
		case "PBKDF2":
			break;
		case "BCRYPT":
			break;
		case "SCRYPT":
			break;
		}
		return mdp;
	}

	private String hashMd5(String password) {
		byte[] tabBytes = null;
		try {
			try {
				tabBytes = MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException ex) {
				logger.error(EncryptPassword.class.getName(), ex);
			}
		} catch (NoSuchAlgorithmException ex) {
			logger.error(EncryptPassword.class.getName(), ex);
		}
		return new String(tabBytes);
	}

//	private String hashPBKDF2(String password) {
//		return password;
//	}
//
//	private String hashBcrypt(String password) {
//		return password;
//	}
//
//	private String hashScrypt(String password) {
//		return password;
//	}

	private String getSalt() {
		Random	 rand = new Random();
		String re = "";
		for (int i = 0; i < 8; i++) {
			re += ConstantesManager.ALPHABET[rand.nextInt(ConstantesManager.ALPHABET.length)];
		}
		return re;
	}
}
