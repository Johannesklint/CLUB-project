package club.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class PasswordHandler {

	private static HMAC hash(String password, byte[] salt) throws CouldNotEncryptPasswordException{
		try {
			String sha1Password = getSHA(password,salt,"SHA-512");
			return HMAC.buildfromParametes(sha1Password, bytesToHex(salt));
		} catch (NoSuchAlgorithmException e) {
			throw new CouldNotEncryptPasswordException();
		}
	}
	
	public static boolean isPasswordMatch(String password, HMAC hashedPasswordSaltPair) throws CouldNotEncryptPasswordException {
		HMAC c = hash(password,hashedPasswordSaltPair.getSaltBytes());		
		return c.equals(hashedPasswordSaltPair);
	}
	
	public static HMAC hash(String password) throws CouldNotEncryptPasswordException {
		byte[] salt;
		try {
			salt = generateSalt();
		} catch (NoSuchAlgorithmException e) {
			throw new CouldNotEncryptPasswordException();
		} catch (NoSuchProviderException e) {
			throw new CouldNotEncryptPasswordException();
		}
		return hash(password,salt);
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
		byte[] bytes = md.digest(password.getBytes());
		return DatatypeConverter.printHexBinary(bytes);
	}
	
	private static String bytesToHex(byte[] bytes) {		
		return DatatypeConverter.printHexBinary(bytes);
	}
}