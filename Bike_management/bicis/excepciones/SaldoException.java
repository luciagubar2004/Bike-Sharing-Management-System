package bicis.excepciones;

public class SaldoException extends Exception {
	/** Las clases que derivan de Exception deben tener un atributo como éste */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor para crear una excepción relativa a saldos
	 * @param causa de la excepción
	 */
	public SaldoException(String causa) {
		super(causa);
	}
}
