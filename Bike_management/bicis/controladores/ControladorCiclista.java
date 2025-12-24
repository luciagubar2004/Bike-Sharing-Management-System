package bicis.controladores; //declaracion del paquete donde se encuentra la clase

//importacion de diferentes clases desde otros paquetes
import java.util.List;

//import bicis.estaciones.Estacion;

import bicis.excepciones.SaldoException;
import bicis.excepciones.TrayectoException;
import bicis.excepciones.UsuarioException;
import bicis.estaciones.GestorEstacion;
import bicis.excepciones.EstacionException;
import bicis.usuarios.Ciclista;
import bicis.usuarios.GestorUsuarios;
//import bicis.estaciones.Estacion;
//import bicis.estaciones.GestorEstacion;

//declaracion de la clase controador ciclista 
public class ControladorCiclista {
	
	private GestorUsuarios gu; /** el gestor de usuarios */
	private Ciclista cic; /** el ciclista de la sesión */
	
	GestorEstacion ge = GestorEstacion.getInstancia(); //sirve para emplear métodos de gestor estacion
	
	/**
	 * Constructor que asigna el gestor de usuarios que recibe como parámetro 
	 * @param gu es el gestor de usuarios 
	 */
	public ControladorCiclista(GestorUsuarios gu) {
		this.gu = gu;   // Asignación del parámetro gu al atributo gu
	}

	/**
	 * Metodo para registrarse como ciclista en el sistema
	 * @param login del ciclista a crear (ÚNICO)
	 * @param clave del ciclista a crear
	 * @param nombre del ciclista a crear
	 * @throws ExcepcionUsuario si ya existe un usuario con ese login
	 * 			o si hubo un error interno en la creación del ciclista
	 */
	public void crearCiclista(String login, String clave, String nombre) throws UsuarioException {
		// creo ciclista
		gu.crearUsuario(login, clave, nombre, "Ciclista"); //lamada al metodo crearUsuario del objeto gu 
	}	
	
	/**
	 * Metodo para identificar a un ciclista en el sistema, guardando una referencia en el atributo cic
	 * @param login del ciclista
	 * @param clave del ciclista
	 * @throws ExcepcionUsuario si las credenciales de usuario no son válidas
	 * 			o si las credenciales no son de un ciclista
	 */
	public void identificarCiclista(String login, String clave) throws UsuarioException { //declaracion del metodo identificarCiclista 
		if (gu.validarUsuario(login, clave)) { //comprueba si las credenciales del usuario son validas 
			// usuario válido
			try {
				cic = (Ciclista) gu.getUsuario(login); //intento de conversion del usuario a un ciclista 
			} catch (ClassCastException e) { //captura de unaa posible excepcion de conversion de tipos
				throw new UsuarioException("Usuario "+login+" no es Ciclista");
			}
		}
		else // usuario no válido
			throw new UsuarioException("Credenciales de usuario no válidas");
	}
	
	/**
	 * Metodo para recargar el saldo del ciclista de la sesión
	 * @param cant a incrementar en el saldo del ciclista
	 * @throws SaldoException 
	 * @throws ExcepcionUsuario si el ciclista de la sesión (cic) no existe
	 * @throws SaldoException si el saldo no está comprendido en (0-100]
	 */
	public void recargarSaldo(double cant) throws UsuarioException, SaldoException {
		if (cic == null)
			throw new UsuarioException("No existe el ciclista de la sesión");
		if (cant <= 0 || cant > 100)
			throw new SaldoException("Saldo fuera de rango: debe estar comprendido en (0-100]");
		cic.recargarSaldo(cant);
	}
	
	
	//metodo para ver la Estacion
	

	public String verEstacion(String idEstacion) throws UsuarioException, EstacionException {
	    if (cic == null)
	        throw new UsuarioException("No existe el ciclista de la sesión");
	    
	    return String.valueOf(ge.verEstacion(idEstacion));
	}
	

	public List<String> buscarEstaciones(double cX, double cY, int dmax, boolean tipo) throws UsuarioException {
	    if (cic == null)
	        throw new UsuarioException("No existe el ciclista de la sesión");
	    return GestorEstacion.getInstancia().buscarEstaciones(cX, cY, dmax, tipo);
	}

	
	//REVISAR ESTO
	public void desbloquearBici (String idE, int idB) throws UsuarioException, EstacionException {
		if (cic == null) {
	        throw new UsuarioException("No existe el ciclista de la sesión");
		}
		 // Obtener el saldo del ciclista
	    double saldo = cic.getSaldo();
	    // Verificar si el saldo es suficiente
	   if (saldo > 10) {
		   GestorEstacion.getInstancia().desbloquearBici(idB, idE, cic);
	   } else {
	        throw new UsuarioException("No tienes suficiente saldo");
	    }
	}
	

	
	public void devolverBici (String idE) throws UsuarioException, EstacionException, TrayectoException  {
		if (cic == null) {
	        throw new UsuarioException("No existe el ciclista de la sesión");
		}	//llama a gestor estación si el cic ha iniciado sesión
		   ge.devolverBici(idE, cic);
	}
	
	
	
	public List<String> misTrayectos () throws UsuarioException{
		if (cic == null) {
	        throw new UsuarioException("No existe el ciclista de la sesión");
		}
	    return ge.misTrayectos(cic);
	}
	
	
	
	public String verInfoTrayecto (int indiceTrayecto) throws UsuarioException, TrayectoException {
		if (cic == null) {
	        throw new UsuarioException("No existe el ciclista de la sesión");
		}	
		  return String.valueOf (ge.verInfoTrayecto(cic, indiceTrayecto));
		
	}
	

	/**
	 * Metodo para cerrar sesión que elimina la referencia a cic
	 */
	
	public void cerrarSesion() {
		cic = null;
		
	}	
	
	
}
