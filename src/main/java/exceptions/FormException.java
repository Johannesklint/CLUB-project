package exceptions;

public class FormException extends Exception {

	private static final long serialVersionUID = -6070043972576734501L;

	public FormException(String message) {
		super(message);
	}
	// Use Face Handler in Exception class? Bekvämt men inte helt ok design om man inte byter namn på klassen.
	
}