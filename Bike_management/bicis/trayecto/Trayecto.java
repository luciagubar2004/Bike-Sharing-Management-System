package bicis.trayecto;

import bicis.bicicleta.Bici;
import bicis.estaciones.Estacion;

public class Trayecto {

	private Estacion EstOrigen;
	private Estacion EstDestino;
	private Bici bicicleta;
	
	// Constructor de la clase Trayecto	
	public Trayecto(Bici bici, Estacion estOrigen) {
	        bicicleta = bici;
	        EstOrigen = estOrigen;	        
	}

	//método que fija la estacion de origen
	public void setEstOrigen(Estacion estOrigen) {
		EstOrigen = estOrigen;
	}
	
	//método que fija la estacion de destino
	public void setEstDestino(Estacion estDestino) {
		EstDestino = estDestino;
		
	}
	
	 // Método que obtiene la estacion de origen
    public Estacion getEstOrigen() {
        return EstOrigen;
    }

    // Método que obtiene la estacion de destino
    public Estacion getEstDestino() {
        return EstDestino;
    }

    // Método que obtiene la bicicleta
    public Bici getBici() {
        return bicicleta;
    }
		
	//método que fija la estacion de destino
	public void setBici(Bici bici) {
		bicicleta = bici;
	}
	
	
	//calculator
	public double calcularPrecio() {
		double precio;
		if (EstDestino == null) {
	        // Manejar el caso cuando la estación de destino es null
	        // Por ejemplo, puedes devolver 0 o algún valor predeterminado
	        return 0;
	    }
		//calcula la distancia entre dos estaciones según sus coordenadas cx y cy
		double distancia = Math.sqrt(Math.pow(EstOrigen.getCx() - EstDestino.getCx(), 2) + Math.pow(EstOrigen.getCy() - EstDestino.getCy(), 2)); 
		boolean esE=bicicleta.getEsE(); //para aplicar un precio u otro
		if (esE) {
			precio=distancia*0.2;
		} else {
			precio=distancia*0.1;
		}	
		return precio;		
	}
	
		//calculator 2
	
	public double calcularDistancia() {
		    if (EstOrigen == null || EstDestino == null) {
		        return 0.0; // Si alguna de las estaciones es nula, devolver distancia cero
		    }

		    // Calcular la distancia utilizando la fórmula de la distancia euclidiana entre dos puntos en un plano
		    double distancia = Math.sqrt(Math.pow(EstOrigen.getCx() - EstDestino.getCx(), 2) + Math.pow(EstOrigen.getCy() - EstDestino.getCy(), 2));

		    return distancia;
		}

		
	
	
}

