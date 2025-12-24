package bicis.excepciones;

public class BiciException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BiciException(String causa) {
		super(causa);
	}
}
