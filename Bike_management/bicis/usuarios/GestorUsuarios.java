package bicis.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bicis.bicicleta.Bici;

import bicis.excepciones.UsuarioException;

public class GestorUsuarios {


	/**
	 * Mapa de usuarios del sistema
	 */
	private Map<String, Usuario> mapaUsuarios; // Declaración del atributo mapaUsuarios de tipo Map que asocia cadenas (logins) con objetos Usuario

	/**
	 * Constructor que inicializa el mapa de usuarios
	 */
	public GestorUsuarios() { //declaracion del constructor 
		// inicializo mapa de usuarios
		mapaUsuarios = new HashMap<>(); //crea e inicializa el objeto  HashMap para almacenar los usuarios
	}	
	
	/**
	 * Método que crea una nueva instancia del tipo de usuario adecuado y la agrega al mapa, indexada por login
	 * 
	 * @param login del usuario (ÚNICO)
	 * @param clave del usuario (en claro)
	 * @param nombre del usuario
	 * @param tipo de usuario a generar
	 * @throws ExcepcionUsuario si ya existe un usuario con ese login, 
	 * 			si el tipo de usuario no existe en el sistema,
	 * 			si hubo un error interno en la creación del usuario
	 */
	public void crearUsuario(String login, String clave, String nombre, String tipoUsuario) 
			throws UsuarioException { //declaracion del metodo crearUsuario que puede lanzar una excecpcion UsuarioExcepcion 
		if (mapaUsuarios.containsKey(login)) // existe el login?
			throw new UsuarioException("Login ya existe"); //lanzamiento de una excepcion UsuarioExcepcion si ya exsiste un usuario con ese login 
		else {
			Usuario u; // usuario a crear
			switch(tipoUsuario) {				
			case "Ciclista": //si el tipo de usuario es ciclista 
				u = new Ciclista(login, clave, nombre); // Creación de un objeto Ciclista con los parámetros proporcionados
				break;
			case "Administrador": //si el tipo de usuario es administrador 
				u = new Administrador(login, clave, nombre); //creacion de un nuevo objeto administrador con los parametros proporcionados 
				break;
			default: // tipo de usuario incorrecto
				throw new UsuarioException("Tipo de usuario \""+tipoUsuario+"\" incorrecto");
			}
			// guardo usuario en el mapa 
			mapaUsuarios.put(login, u);			
		}
	}	

	/**
	 * Método que comprueba las credenciales de un usuario
	 * 
	 * @param login del usuario
	 * @param clave del usuario (en claro)
	 * @return true si existe un usuario con ese login y su clave coincide con la proporcionada
	 * 			false en cualquier otro caso 
	 */
	public boolean validarUsuario(String login, String clave) { //declaracion del metodo validarUsuario 
		Usuario u = mapaUsuarios.get(login); //obtiene el usuario del mapa usando el login como clave 
		if (u == null) //si el usuario no exsiste en el mapa 
			return false; //retorna falso 
		else 
			return clave.equals(u.getClave()); //retorna verdadero si la clave coincide con la proporcionada por el usuario 
	}

	/**
	 * Método que devuelve un usuario a partir de su login
	 * 
	 * @param login del usuario
	 * @return el usuario del mapa con ese login o null si no existe
	 */
	public Usuario getUsuario(String login) { //deckaracion del metodo gestUsuario 
		return mapaUsuarios.get(login); //retorna el usuario correspondiente al login del mapa o null si no exixte 
	}
	
	/**
	 * Método que devuelve una lista de usuarios en el sistema por tipo
	 * 
	 * @param tipo de usuario de interés
	 * @return lista de usuarios del tipo de interés encontrados
	 * @throws ExcepcionUsuario si el tipo de usuario solicitado no existe 
	 */
	public List<Usuario> listarUsuariosTipo(String tipoUsuario) throws UsuarioException {
		// inicializo lista
		List<Usuario> lus = new ArrayList<>();		
		// preparo lista
		for (Usuario us : mapaUsuarios.values()) {
			// incluyo en la lista según el tipo de usuario	
			switch(tipoUsuario) {				
			case "Ciclista": // si el usuario es "ciclista"
				if (us instanceof Ciclista) //comprueba si el usuario es una instancia de cilista 
					lus.add(us); //añade el usuario a la lista 
				break;
			case "Administrador":
				if (us instanceof Administrador)
					lus.add(us);
				break;
			default: // tipo de usuario incorrecto
				throw new UsuarioException("Tipo de usuario \""+tipoUsuario+"\" incorrecto");
			}				
		}			
		// y la devuelvo
		return lus;
	}
	
	public Ciclista buscarBiciCiclista(Bici bici) {
		
		 for (Usuario usuario : mapaUsuarios.values()) {
			
		        if (usuario instanceof Ciclista) {
		            Ciclista ciclista = (Ciclista) usuario;
		            Bici biciCiclista = ciclista.tieneBiciAlq();
		            if (biciCiclista != null) {
		              
		               
		                if (bici.getIdB() == biciCiclista.getIdB()) {
		                   
		                    return ciclista;
		                }
		            }
		        }
		    }
		
		
		
		    return null;
		}
	
}