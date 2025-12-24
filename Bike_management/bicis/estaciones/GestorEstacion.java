package bicis.estaciones;
import bicis.usuarios.Ciclista;
import bicis.bicicleta.GestorBicis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bicis.bicicleta.Bici;
//import bicis.bicicleta.GestorBicis;
import bicis.excepciones.EstacionException;
import bicis.excepciones.TrayectoException;
import bicis.excepciones.UsuarioException;
import bicis.trayecto.Trayecto;
import bicis.estaciones.GestorEstacion;



public class GestorEstacion {
	private static GestorEstacion instancia = null;
	  

	// Método estático para obtener la instancia única del GestorEstacion
	public static GestorEstacion getInstancia() {
	      if (instancia == null) {
	          instancia = new GestorEstacion();
	      }
	      return instancia;
	}
	
	
	
	/**
	 * Mapa de estaciones del sistema
	 */
	private Map<String, Estacion> mapaEstacion;

	/**
	 * Constructor que inicializa el mapa de estaciones
	 */
	public GestorEstacion() {
		// inicializo mapa de estaciones
		mapaEstacion = new HashMap<>();
		
	}
	
	
	/**
	 * Método que comprueba si el identificador de la estacion existe
	 */
	
	public boolean comprobarIdE(String idE) {
		Estacion e = mapaEstacion.get(idE);
		if (e == null)
			return false;
		else 
			return true;
	}
	
	/**
	 * Método que devuelve una estacion a partir de su identificador
	 * 
	 * @param identificador de la estacion
	 * @return la estacion del mapa con ese identificador o null si no existe
	 */
	public Estacion getEstacion(String idE) {
		return mapaEstacion.get(idE);
	}
	

	// formato parametros estacion idEstacion, nombre, x, y, numAnclajes 
	public void registrarEstacion(String idEstacion, String nombre, int x, int y, int numAnclajes) throws EstacionException {
		if (mapaEstacion.containsKey(idEstacion)) // existe el identificador
			throw new EstacionException("Ya existe la estación " +idEstacion);
		else {
			Estacion e; // estacion a crear
				e = new Estacion(idEstacion, nombre, x, y, numAnclajes);
					
			// guardo estacion en el mapa
			mapaEstacion.put(idEstacion, e);			
		}
	}
	
	
	
	//
	//
	public String verEstacion(String idEstacion) throws EstacionException {
	    if (mapaEstacion.containsKey(idEstacion)) { // Existe el identificador
	        Estacion estacion = getEstacion(idEstacion); // Obtiene la información de la estación con dicho id

	        // Inicia con la información básica de la estación
	        String info = estacion.toString() + "\n";

	        int anclajesLibres = estacion.tieneAnclajesLibres();
	        info += "Anclajes libres: " + anclajesLibres + "\n";

	        //crea e inicializa las variables en las que se guardará el numero de bicicletas electricas y mecanicas que hay en cada estacion
	        int biciElectrica = 0;
	        int biciMecanica = 0;

	        List<Bici> bicicletas = estacion.getBicicletas();

	        for (Bici bici : bicicletas) { //bucle para diferenciar el tipo de bici 
	            if (bici.getEsE()) {
	                biciElectrica++;
	            } else {
	                biciMecanica++;
	            }
	        }

	        info += "Bicicletas eléctricas disponibles: " + biciElectrica + "\n";
	        info += "Bicicletas mecánicas disponibles: " + biciMecanica;

	        // Devuelve el String generado con toda la información
	        return info;
	    } else {
	        throw new EstacionException("No existe la estación");
	    }
	}


	//private List<Estacion> lev= new ArrayList<>(); //crea una lista para almacenar las estaciones válidas

	public List<String> buscarEstaciones(double cX, double cY, int dmax, boolean tipo) {
		 List<Estacion> lev= new ArrayList<>(); //crea una lista para almacenar las estaciones válidas
		 List<String> estacionesEncontradas = new ArrayList<>();
		int sizelev = lev.size(); //tamaño de la lista almacenado en sizelev para la condicion del bucle
		for (Estacion estacion : mapaEstacion.values()) {

            if (estacion.estValida(dmax, cX, cY, tipo)) { 
            	    lev.add(estacion);
            	   
            	 // Inicia con la información básica de la estación
        	        String infoEstacion = estacion.toString() + "\n";

        	        int anclajesLibres = estacion.tieneAnclajesLibres();
        	        infoEstacion += "Anclajes libres: " + anclajesLibres + "\n"; //añade los anclajes libres a la cadena

        	        int biciElectrica = 0;
        	        int biciMecanica = 0;

        	        List<Bici> bicicletas = estacion.getBicicletas();

        	        for (Bici bici : bicicletas) {
        	            if (bici.getEsE()) {
        	                biciElectrica++;
        	            } else {
        	                biciMecanica++;
        	            }
        	        }

        	        infoEstacion += "Bicicletas eléctricas disponibles: " + biciElectrica + "\n"; //añade a la cadena
        	        infoEstacion += "Bicicletas mecánicas disponibles: " + biciMecanica;
        	    	infoEstacion += "\n Distancia: " + String.format("%.2f km", estacion.getDistancia()); //añade la distancia
       	    
        	    	estacionesEncontradas.add(infoEstacion.toString()); //añade la cadena infoEstacion a la lista estacionesEncontradas
       		      
            } 
            if (sizelev == 5) //se sale del loop si se han encontrado las 5 estaciones o de que acabe de recorrer el for
            	break;
         }
		 return estacionesEncontradas; //devuelve la lista
	
	}
	
	public void desbloquearBici ( int idB, String idE, Ciclista cic) throws EstacionException {
		 if (mapaEstacion.containsKey(idE)) { // Existe el identificador
		        
		       if ( GestorBicis.getInstancia().containsKey(idB)) { //existe el idbici
		    	   Estacion estacion = getEstacion(idE); // Obtiene la información de la estación con dicho id
		    	   Bici bici = GestorBicis.getInstancia().getBici(idB);
		    	   if (estacion.biciAncladaEst(bici)) {
		    		   Bici biciAlquilada = cic.tieneBiciAlq();
		    		   if (biciAlquilada == null) {
		    			   // Llama al método quitarBiciLista() 
		                    estacion.quitarBiciLista(bici);
		                    cic.setBiciAlquilada(bici);
		                    cic.abrirTrayecto(bici, estacion);
		                  
		    		   }else {
		    			   throw new EstacionException("\nYa tienes una bici que aún no has devuelto");
		    		   }
		    		   
		    		   }else {
		    			   throw new EstacionException("No está la bici "+idB+" en "+idE);
		    		   }
		    	   }
		       }
		        else {
		        throw new EstacionException("No existe la estación");
		    }
		       
		 }


	public void devolverBici(String idE, Ciclista cic) throws EstacionException, TrayectoException{
		if (mapaEstacion.containsKey(idE)) { // Existe el identificador
			Estacion ef = mapaEstacion.get(idE);//obtiene la estación final del mapa
			Bici biciAlquilada = cic.tieneBiciAlq(); //comprueba que el ciclista tiene una bici alquilada
  		   	if (biciAlquilada != null) {
  		   		ef=getEstacion(idE);
  		   		if(ef.tieneAnclajesLibres()>0) {
  		   			ef.addBici(biciAlquilada); //añade la bici a la lista de bicis ancladas de la estacion final
  		   			cic.devolverBici(ef);
  		   		}
  		   		else {
  		   			throw new EstacionException("No hay anclaje libre en "+idE);
  		   		}
  		   	}
  		   	else {
  		   		throw new TrayectoException("No tienes ninguna bici del sistema");
  		   	}			
		}
		else {
		        throw new EstacionException("No existe la estación");
		}
		
	}
	
	
	
	public List<String> misTrayectos(Ciclista cic) throws UsuarioException{
		 if (cic == null) {
	            throw new UsuarioException("No existe el ciclista de la sesión");
	        }
		    List<String> descripcionTrayectos = new ArrayList<>(); // Inicializar la lista para almacenar la descripción de los trayectos


		    List<Trayecto> trayectos = cic.getTrayectos();
		    int contador = 0;
		    for (Trayecto trayecto : trayectos) {
		    	 String origen = trayecto.getEstOrigen().getIdE(); // Obtener el ID de la estación de origen
		         String destino = trayecto.getEstDestino() != null ? trayecto.getEstDestino().getIdE() : "..."; // Obtener el ID de la estación de destino
		         String descripcion = "Trayecto #" + contador + " - " + origen + " => " + destino; // Construir la descripción del trayecto sin la información de la bicicleta
		         descripcionTrayectos.add(descripcion); // Agregar la descripción del trayecto a la lista
		         contador++;
		    }

		    return descripcionTrayectos;
		
	}
	
	
	
	public String verInfoTrayecto(Ciclista cic, int indiceTrayecto) throws UsuarioException, TrayectoException {
	    if (cic == null) {
	        throw new UsuarioException("No existe el ciclista de la sesión");
	    }

	    List<Trayecto> trayectos = cic.getTrayectos();
	    if (indiceTrayecto < 0 || indiceTrayecto >= trayectos.size()) {
	        throw new TrayectoException("No existe un trayecto con id " + indiceTrayecto);
	    }

	    Trayecto trayecto = trayectos.get(indiceTrayecto);

	    String origen = trayecto.getEstOrigen().getIdE(); // Obtener el ID de la estación de origen
	    String destino = trayecto.getEstDestino() != null ? trayecto.getEstDestino().getIdE() : "En curso"; // Obtener el ID de la estación de destino

	    // Obtener información de la bicicleta
	    String infoBici = "Bici: " + trayecto.getBici().getIdB() + " (" + (trayecto.getBici().getEsE() ? "eléctrica" : "mecánica") + ")";

	    // Obtener información de la estación de inicio y fin
	    String infoEstaciones = "Estación inicio: " + trayecto.getEstOrigen().getNombre() + "\n" +
	                            "Estación fin: " + destino;

	    // Inicializar la cadena de información de distancia y costo
	    String infoDistanciaCoste = "";

	    // Si el trayecto no está en curso, obtener la distancia y el costo
	    if (!"En curso".equals(destino)) {
	        double distancia = trayecto.calcularDistancia();
	        double coste = trayecto.calcularPrecio();
	        infoDistanciaCoste = String.format("\nDistancia: %.2f km\nCoste: %.2f €", distancia, coste);
	    }

	    // Construir la cadena de información del trayecto
	    String infoTrayecto = String.format("Trayecto #%d - %s => %s\nCiclista: %s\n%s\n%s%s",
	                                         indiceTrayecto, origen, destino, cic.getNombre(), infoBici, infoEstaciones, infoDistanciaCoste);

	    return infoTrayecto;
	}

	public Estacion buscarBiciEstacion(Bici bici) {
	    for (Estacion estacion : mapaEstacion.values()) {
	        boolean existeBiciAnclada = estacion.biciAncladaEst(bici);
	        
	        if (existeBiciAnclada) {
	            return estacion; // Devuelve la estación si se encuentra la bicicleta, interrumpe el bucle
	        }
	    }
	    return null; // Devuelve null si no se encuentra la bicicleta en ninguna estación
	}
	
	
	
	
}

 

	



	

	
	
	
	

