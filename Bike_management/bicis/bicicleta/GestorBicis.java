package bicis.bicicleta;



import java.util.HashMap;
import java.util.Map;

import bicis.estaciones.Estacion;
import bicis.estaciones.GestorEstacion;
import bicis.excepciones.BiciException;
import bicis.excepciones.EstacionException;
import bicis.usuarios.GestorUsuarios;
import bicis.usuarios.Ciclista;

public class GestorBicis
{
	private static GestorBicis instancia = null;
	  

	// Método estático para obtener la instancia única del GestorBici
	public static GestorBicis getInstancia() {
	      if (instancia == null) {
	          instancia = new GestorBicis();
	      }
	      return instancia;
	}
	
	GestorEstacion ge = GestorEstacion.getInstancia();
	
	/**
	 * Estación del sistema de la cual aprendemos si tiene anclajes libres o no
	 */
	private Estacion e;
	public GestorBicis(Estacion gesu) {
		e = gesu;
	}
	
	/**
	 * Mapa de usuarios del sistema
	 */
	private Map<Integer, Bici> mapaBicis;
	/**
	 * Constructor que inicializa el mapa de usuarios
	 */
	public GestorBicis() {
		// inicializo mapa de bicis
		mapaBicis = new HashMap<>();
	}	
	
	
	
	/**
	 * Método que crea una nueva instancia del tipo de bici adecuada y la agrega al mapa, indexada por idEstacion
	 * 
	 * @param id de la Bici (ÚNICO)
	 * @param esE booleano para saber si es electrica o mecanica
	 * @param id de la Estacion a la que se la asigna
	 * @throws BiciExcepcion si ya una bici con ese identificador 
	 * 			si el tipo de usuario no existe en el sistema,
	 */
	public void registrarBici(int idB, boolean esE, String idE) 
			throws BiciException, EstacionException {
		if (mapaBicis.containsKey(idB)) // existe el identificador?
			throw new BiciException("Ya existe la bici " +idB);
		else {
			if(ge.comprobarIdE(idE)) {
					e=ge.getEstacion(idE);
					if(e.tieneAnclajesLibres()>0) {	
					Bici b; // bici a crear
							if (esE) {
										b = new BiciElectrica(idB, esE, idE);
									} else {
										b = new BiciMecanica(idB, esE, idE);
									}		
								// guardo bici en el mapa y en la lista
								mapaBicis.put(idB, b);	
								e.addBici(b);
					}
					else {
						throw new EstacionException("No hay anclaje libre para asignar la bici " +idB+  "a la estación "+idE);
					}
			}
			else {
				throw new EstacionException("No existe la estacion "+idE);
		}
	}
	
	}
	
	public Bici getBici (int idB) {
		return mapaBicis.get(idB);
	}
	
	
	public boolean containsKey(int idB) {
	    return mapaBicis.containsKey(idB);
	}
	
	public String localizarBici (int idB) throws BiciException{
		String info = "";
		if (mapaBicis.containsKey(idB)) {
			 Bici bici = getBici(idB);
			
			 Estacion estacionLocalizada = GestorEstacion.getInstancia().buscarBiciEstacion(bici);
			 if (estacionLocalizada == null) {
			
				 GestorUsuarios gestorUsuarios = new GestorUsuarios();
				Ciclista ciclistaBici = gestorUsuarios.buscarBiciCiclista(bici);
				if (ciclistaBici != null) {
				info = "El ciclista " + ciclistaBici.getNombre() + "(" + ciclistaBici.getLogin() + ")" + "está usando la bici " + idB ;
				}else {
				
				}
			 } else {
				 info = "La bici " + idB + "está en la estación " + estacionLocalizada.getNombre() + "(" + estacionLocalizada.getIdE() + ")";
			 }
			
		} else {
			throw new BiciException("No existe la bici " +idB);
		}
		return info;
	}
}
