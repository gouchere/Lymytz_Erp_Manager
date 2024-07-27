package lym.com.api.service.authentification;

public class MotDePasse {
	private String password;
	private String salt;

	public MotDePasse() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
