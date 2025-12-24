package bicis.excepciones;

public class EstacionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EstacionException(String causa) {
		super(causa);
	}
}
