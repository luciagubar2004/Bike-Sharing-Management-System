package bicis.excepciones;

public class TrayectoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TrayectoException(String causa) {
		super(causa);
	}
}
