package bicis.estaciones;

import java.util.ArrayList;
import java.util.List;


import bicis.bicicleta.Bici;


public class Estacion {
	private String idEstacion; /** id único del usuario en el sistema */
	private String nombre; /** nombre de la estacion */
	private float x; /** coordenada x*/
	private float y; /** coordenada y*/
	private int numAnclajes; /**numero de anclajes totales*/
	private double distancia;
	/**
	 * Constructor que crea una instancia de esta clase	 * 
	 * @param idEstacion (ÚNICO)
	 * @param nombre
	 * @param x
	 * @param y
	 * @param numAnclajes
	 */
	public Estacion(String idE, String nom, float cx, float cy, int numA) {
		idEstacion = idE;		//asigna a los atributos del objeto los parámetros que se pasan al constructor
		nombre = nom;
		x = cx;
		y = cy;
		numAnclajes = numA;
		
	}
	
	/**
	 * Método que devuelve el identificador
	 * 
	 * @return el identificador
	 */
	public String getIdE() {
		return idEstacion;
	}
	
	
	/**
	 * Método que devuelve el nombre
	 * 
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Método que fija el nombre
	 * 
	 * @param nombre nuevo
	 */
	public void setNombre(String nom) {
		nombre = nom;
	}
	
	
	/**
	 * Método que devuelve la coordenada x
	 * 
	 * @return la coordenada x
	 */
	public float getCx() {
		return x;
	}
	
	/**
	 * Método que fija la coordenada x
	 * 
	 * @param coordenada x nueva
	 */
	public void setCx(float cx) {
		x = cx;
	}
	
	/**
	 * Método que devuelve la coordenada y
	 * 
	 * @return la coordenada y
	 */
	public float getCy() {
		return y;
	}
	
	/**
	 * Método que fija la coordenada y
	 * 
	 * @param coordenada y nueva
	 */
	public void setCy(float cy) {
		y = cy;
	}
	
	
	/**
	 * Método que devuelve el numero de anclajes
	 * 
	 * @return el número de anclajes
	 */
	public int getNumA() {
		return numAnclajes;
	}
	
	/**
	 * Método que fija el numero de anclajes
	 * 
	 * @param numero de anclajes nuevo
	 */
	public void setNumA(int numA) {
		numAnclajes = numA;
	}
	
	/**
	 * Lista de bicicletas ancladas a la estacion
	 */
	private List<Bici> lba= new ArrayList<>(); 

	
	public int tieneAnclajesLibres() {
	        return numAnclajes - lba.size();
	 }
	
	public void addBici(Bici bici) { 
	    lba.add(bici); 
	}
	
	//yo
	public List<Bici> getBicicletas() {
	    return lba;
	}


	//Para ver si la estación cumple con los requisitos
	public boolean estValida(int dmax, double cX, double cY, boolean tipo ) {
		distancia = Math.sqrt(Math.pow(x - cX, 2) + Math.pow(y - cY, 2)); //calcula la distancia
		if (distancia <= dmax) { //primero mira si cumple la distancia
			if (tipo) { //luego dependiendo del tipo mira la siguiente condición, si hay bicis o anclajes
				if (lba.size() >= 1) {
					// Verifica si la estación tiene bicis
					return true;
				}else {
					return false;
				}
				
			}else {
				if (lba.size() < numAnclajes ) {
					// Verifica si la estación tiene anclajes libres para devolver una bici
						return true;
					}else {
						return false;
					}	
			}
			
		}else {
			return false;
		}
	}


				
	// Método para obtener la distancia
    public double getDistancia() {
        return distancia;
    }


	/**
	 * Método que devuelve una descripción de la estacion
	 * 
	 * @return descripción
	 */
	@Override
	public String toString() {
		// Compone una cadena con todos los campos y la retorna
		String cadena = "Estacion: " + getIdE() + 
				"\n Nombre: " + getNombre() + 
				"\n Coordenadas: (" + getCx() + ", " + getCy() + ")";
		return cadena;
	}
	
	public boolean biciAncladaEst (Bici b) {
		boolean biciAncladaEst ;
		biciAncladaEst =  lba.contains(b);
		return biciAncladaEst;
	}
	
	public void quitarBiciLista(Bici b) {
		lba.remove(b);
	}
}

