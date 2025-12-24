package bicis.controladores;

import java.util.ArrayList;
import java.util.List;

import bicis.estaciones.GestorEstacion;
import bicis.bicicleta.GestorBicis;
import bicis.excepciones.EstacionException;
import bicis.excepciones.UsuarioException;
import bicis.excepciones.BiciException;
import bicis.usuarios.Administrador;
import bicis.usuarios.GestorUsuarios;
import bicis.usuarios.Usuario;


public class ControladorAdministrador {
	
	private GestorUsuarios gu; /** el gestor de usuarios */
	private Administrador admin; /** el administrador de la sesión */


	/**
	 * Constructor que asigna el gestor de usuarios que recibe como parámetro	 
	 * @param gesu es el gestor de usuarios 
	 */
	public ControladorAdministrador(GestorUsuarios gesu) {
		gu = gesu;
	}

	GestorEstacion ge = GestorEstacion.getInstancia();

	GestorBicis gb = GestorBicis.getInstancia();
	
	/**
	 * Metodo para registrar una estacion en el sistema
	 * @param idEstacion (ÚNICO)
	 * @param nombre
	 * @param x
	 * @param y
	 * @param numAnclajes
	 * @throws EstacionExcepcion si ya existe una estacion con ese id
	 */
	
	public void crearCiclista(String login, String clave, String nombre) throws UsuarioException {
		// creo ciclista
		gu.crearUsuario(login, clave, nombre, "Ciclista");
	}	
	
	public void registrarEstacion(String idEstacion, String nombre, int x, int y, int numAnclajes) throws UsuarioException, EstacionException {
		if (admin == null)
			throw new UsuarioException("No existe el administrador de la sesión");
		// creo estacion
		ge.registrarEstacion(idEstacion, nombre, x, y, numAnclajes);
	}	
	
	public void registrarBici(int idB, boolean esE, String idE) throws BiciException, UsuarioException, EstacionException {
		if (admin == null)
			throw new UsuarioException("No existe el administrador de la sesión");
		
		// creo bici
		gb.registrarBici(idB, esE, idE);
	}	
	
	
	/**
	 * Metodo para identificar a un administrativo en el sistema, guardando una referencia en el atributo admin
	 * @param login del administrativo
	 * @param clave del administrativo
	 * @throws ExcepcionUsuario si las credenciales de usuario no son válidas
	 * 			o si las credenciales no son de un administrativo
	 */
	public void identificarAdministrador(String login, String clave) throws UsuarioException {
		if (gu.validarUsuario(login, clave)) {
			// admin válido
			try {
				admin = (Administrador) gu.getUsuario(login);
			} catch (ClassCastException e) {
				throw new UsuarioException("Usuario "+login+" no es Administrador");
			}
		}
		else // admin no válido
			throw new UsuarioException("Credenciales de usuario no válidas");
	}
	

	/**
	 * Metodo para recuperar una lista de descripciones de usuario de cierto tipo
	 * @param tipo de usuario de interés
	 * @return lista de descripciones de usuarios del tipo de interés
	 * @throws ExcepcionUsuario si el administrativo no se ha identificado en el sistema,  		
	 * 			o si el tipo de usuario solicitado no existe
	 */
	public List<String> listarUsuariosTipo(String tipo) throws UsuarioException {
		if (admin== null)
			throw new UsuarioException("Autenticación requerida");
		// obtengo lista de descripciones de ususarios y la devuelvo
		List<String> descUsuarios = new ArrayList<>();
		for (Usuario us : gu.listarUsuariosTipo(tipo)) 
			descUsuarios.add(us.toString());
		return descUsuarios;
	}
	
	public String localizarBici(int idB)  throws UsuarioException, BiciException {
		if (admin == null)
			throw new UsuarioException("No existe el administrador de la sesión");
		return GestorBicis.getInstancia().localizarBici(idB);
	}
	/**
	 * Metodo para cerrar sesión que elimina la referencia a admin
	 */
	public void cerrarSesion() {
		admin = null;
	}

}
