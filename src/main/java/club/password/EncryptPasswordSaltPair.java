package club.password;

public class EncryptPasswordSaltPair {
	
	private String encryptPassword;
	private String salt;
	
	EncryptPasswordSaltPair(String encryptPassword,String salt) {
		this.encryptPassword = encryptPassword;
		this.salt = salt;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}
	public String getSalt() {
		return salt;
	}
	
	@Override
	public String toString() {
		return encryptPassword+":"+salt;
	}
}
