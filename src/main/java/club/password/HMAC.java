package club.password;

public class HashedPasswordSaltPair {
	
	private String encryptPassword;
	private String salt;
	
	HashedPasswordSaltPair(String encryptPassword,String salt) {
		this.encryptPassword = encryptPassword;
		this.salt = salt;
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

	public static HashedPasswordSaltPair fromString(String string) {
	    String[] res = string.split(":");
		return new HashedPasswordSaltPair(res[0],res[1]);
	}

	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	@Override
	public String toString() {
		return encryptPassword+":"+salt;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof HashedPasswordSaltPair)) return false;
		
		HashedPasswordSaltPair c = (HashedPasswordSaltPair)o;

		System.out.println("------");;
		System.out.println(salt);
		System.out.println(encryptPassword);
		System.out.println(c.salt);
		System.out.println(c.encryptPassword);
		
		return c.salt.equals(salt) && c.encryptPassword.equals(encryptPassword); 
	}
}
