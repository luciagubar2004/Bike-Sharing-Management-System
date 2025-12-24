package bicis.bicicleta;


public abstract class Bici {

//cadmin.registrarBici(0, true, "e0"); // idBici, esElectrica, idEstacion
	
	private int idBici; /** id único de la bici en el sistema */
	private boolean esElectrica; /** indica el tipo de bici */
	private String idEstacion; /** id de la estación en la que se registra */
	
	
	/**
	 * Constructor que crea una instancia de esta clase	 * 
	 * @param idBici (ÚNICO)
	 * @param esElectrica 
	 * @param idEstacion
	 */
	public Bici (int idB, boolean esE, String idE) {
		idBici = idB;
		esElectrica = esE;
		idEstacion = idE;
	}
	
	/**
	 * Método que devuelve el identificador de la bici
	 * 
	 * @return el identificador de la bici
	 */
	public int getIdB() {
		return idBici;
	}
	
	/**
	 * Método que devuelve el tipo de bici
	 * 
	 * @return tipo de bici
	 */
	public boolean getEsE() {
		return esElectrica;
	}
	
	/**
	 * Método que devuelve la estacion
	 * 
	 * @return la estacion
	 */
	public String getIdE() {
		return idEstacion;
	}
	
	/**
	 * Método que fija la estacion
	 * 
	 * @param estacion nueva
	 */
	public void setIdE(String idE) {
		idEstacion = idE;
	}

	
	
}
