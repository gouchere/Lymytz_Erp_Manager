package lym.com.api.service.authentification;

public interface IHashPassWord {
	public MotDePasse hashPassword(String password, String methode);

	public MotDePasse decryptPassword(String password, String methode, String salt);
}
