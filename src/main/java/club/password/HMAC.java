package club.password;

public class HMAC {
	
	private String encryptPassword;
	private String salt;
	
	public static HMAC buildfromString(String string) {
	    String[] res = string.split(":");
		return new HMAC(res[0],res[1]);
	}

	static HMAC buildfromParametes(String encryptPassword, String salt) {
		return new HMAC(encryptPassword,salt);
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}
	public String getSalt() {
		return salt;
	}

	public byte[] getSaltBytes() {
		return hexStringToByteArray(salt);
	}

	@Override
	public String toString() {
		return encryptPassword+":"+salt;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof HMAC)) return false;
		HMAC c = (HMAC)o;
		return c.salt.equals(salt) && c.encryptPassword.equals(encryptPassword); 
	}
	
	// COPY PASTED FOM STACK OVERFLOW
	private static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	// ---

	private HMAC(String encryptPassword,String salt) {
		this.encryptPassword = encryptPassword;
		this.salt = salt;
	}
}
