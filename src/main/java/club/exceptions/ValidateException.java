package club.exceptions;

public class ValidateException extends Exception {

	private static final long serialVersionUID = -6070043972576734501L;

	public ValidateException(String message) {
		super(message);
	}
	// Use Face Handler in Exception class? Bekvämt men inte helt ok design om man inte byter namn på klassen.
	
}