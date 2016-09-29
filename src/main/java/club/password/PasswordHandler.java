package club.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class PasswordHandler {

	public static EncryptPasswordSaltPair encrypt(String password) throws CouldNotEncryptPasswordException{
		try {
			byte[] salt = generateSalt();			
			String sha1Password = getSHA(password,salt,"SHA-512");
			return new EncryptPasswordSaltPair(sha1Password, bytesToHex(salt));
		} catch (NoSuchAlgorithmException e) {
			throw new CouldNotEncryptPasswordException();
		} catch (NoSuchProviderException e) {
			throw new CouldNotEncryptPasswordException();
		}
	}
	
	private static byte[] generateSalt() throws NoSuchAlgorithmException, NoSuchProviderException{
		SecureRandom sr = null;
		sr = SecureRandom.getInstance("SHA1PRNG", "SUN"); // SUN är kanske optional
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	private static String getSHA(String password, byte[] salt, String SHAVersion) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(SHAVersion);
		return hashPassword(md, password, salt);
	}
	
	private static String hashPassword(MessageDigest md, String password, byte[] salt) {
		md.update(salt);
		System.out.println("iiii"+password);
		byte[] bytes = md.digest(password.getBytes());
		System.out.println("iiii"+bytes);
		return DatatypeConverter.printHexBinary(bytes);
	}
	
	private static String bytesToHex(byte[] bytes) {		
		return DatatypeConverter.printHexBinary(bytes);
	}
	

}
