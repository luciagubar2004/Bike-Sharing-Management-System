package bicis.usuarios; //declaracion del paquete donde se encuentra la clase
import java.util.ArrayList;
import java.util.List;
import bicis.trayecto.Trayecto;
import bicis.bicicleta.Bici;
import bicis.estaciones.Estacion;
//import bicis.trayecto.Trayecto;


public class Ciclista extends Usuario { //declaracion de la clase ciclista que hereda de la clase Usuario
		
	private double saldo = 0; /** el saldo del ciclista */
	private Bici biciAlquilada;
	/**
	 * Constructor que crea una instancia de esta clase	 * 
	 * @param login (ÚNICO)
	 * @param clave (en claro)
	 * @param nombre
	 */
	public Ciclista(String l, String c, String n) {
		super(l, c, n);
	}

	/**
	 * Método para recargar el saldo del ciclista
	 * 
	 * @param cant a recargar
	 */
	public void recargarSaldo(double cant) {
		saldo += cant; //suma de la cantidad del saldo al ciclista 
	}
	
	/**
	 * Método que devuelve una descripción del ciclista
	 * 
	 * @return descripción
	 */
	@Override
	public String toString() { //sobrescritura del metodo toString de la clase base 
		// Compone una cadena con todos los campos y la retorna
		String cadena = super.toString(); //llamada al metodo toString de la clase base y asignacion a la variable cadena 
		cadena += "\n Tipo: Ciclista"; // Concatenación de información sobre el tipo de usuario
		cadena += "\n Saldo: " + saldo; // Concatenación de información sobre el saldo del ciclista
		return cadena; //retorno de la cadena que representa la descripcion del ciclista 
	}
	
	//para obtener el saldo en desbloquearbici
	 public double getSaldo() {
		 return saldo;
	 } 
	 
	 //para modificar el saldo
	 //public void setSaldo(double s) {
		// saldo=s;
	 //} 
	
	 public Bici tieneBiciAlq () {
		 return biciAlquilada;
	 }

	 public void setBiciAlquilada (Bici b) {
		biciAlquilada = b;
	 }
	 
	 private List<Trayecto> lty = new ArrayList<>(); 
	 
	 // Método para obtener la lista de trayectos
	    public List<Trayecto> getTrayectos() {
	        return lty;
	    }
	    

	 public void abrirTrayecto(Bici b, Estacion eOrigen) { //desbloquearBici en el diagrama
		
		 Trayecto trayecto = new Trayecto(b, eOrigen); 
		 lty.add(trayecto);
	 }

	public void devolverBici(Estacion ef) {
		setBiciAlquilada (null);
		int tamaño = lty.size();
		Trayecto t= lty.get(tamaño-1); //se resta uno debido a que la lista empieza en cero
		t.setEstDestino(ef);
		double precio=t.calcularPrecio();
		recargarSaldo(-precio);
		
	}
	
}
