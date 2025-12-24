package bicis.arranque;

import java.util.List;

import bicis.controladores.ControladorAdministrador;
import bicis.controladores.ControladorCiclista;
import bicis.excepciones.BiciException;
import bicis.excepciones.EstacionException;
import bicis.excepciones.SaldoException;
import bicis.excepciones.UsuarioException;
import bicis.usuarios.GestorUsuarios;


public class PruebasBiciIter1 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros por línea de comandos que no se tratan.
	 */
	public static void main(String[] args) {

		//*************************************
		//*******INICIALIZACION GESTORES*******
		//*************************************
		// Instancio el gestor de usuarios
		GestorUsuarios gu = new GestorUsuarios();

				
		
		// Creo administrador inicial
		try {
			gu.crearUsuario("admin", "admin", "Laura Admin", "Administrador");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		//*************************************
		//****INICIALIZACION CONTROLADORES*****
		//*************************************				
		// Instancio controladores de sesión
		ControladorAdministrador cadmin = new ControladorAdministrador(gu);
		ControladorCiclista ccic = new ControladorCiclista(gu);
	

		System.out.println("///////////////////////////");
		System.out.println("// CASOS DE USO ITERACIÓN 0");
		System.out.println("///////////////////////////\n");	
		casosUsoCiclistaIter0(ccic);
		casosUsoAdminIter0(cadmin);

		System.out.println("\n\n///////////////////////////");
		System.out.println("// CASOS DE USO ITERACIÓN 1");
		System.out.println("///////////////////////////\n");		
		casosUsoAdminIter1(cadmin);
		casosUsoCiclistaIter1(ccic);
	}

	/**
	 * Método que realiza los casos de uso de creación de ciclistas y recarga de saldo
	 * @param ccic controlador de sesión de ciclista
	 */
	private static void casosUsoCiclistaIter0(ControladorCiclista ccic) {
		System.out.println("/// REGISTRO DE CICLISTAS ///\n");
		System.out.println("Se registran tres ciclistas\n");
		try {
			ccic.crearCiclista("cic0", "clave", "Tadej Pogacar");
			ccic.crearCiclista("cic1", "clave", "Alberto Contador");
			ccic.crearCiclista("cic2", "clave", "Jonas Vingegaard");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n/// RECARGAR SALDO ///\n");
		
		// ------------------------------------
		// -- Usuario cic0 (CICLISTA) --
		// ------------------------------------
		System.out.println("<<inicio sesión cic0 (Pogacar)>>");
		try {
			ccic.identificarCiclista("cic0","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Pogacar recarga 20€");
		try {
			ccic.recargarSaldo(20);
		} catch (UsuarioException | SaldoException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n<<cierre sesión cic0>>");
		ccic.cerrarSesion();
		
		// ------------------------------------
		// -- Usuario cic1 (CICLISTA) --
		// ------------------------------------
		System.out.println("\n<<inicio sesión cic1 (Contador)>>");
		try {
			ccic.identificarCiclista("cic1","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Contador recarga 15€");
		try {
			ccic.recargarSaldo(15);
		} catch (UsuarioException | SaldoException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n<<cierre sesión cic1>>");
		ccic.cerrarSesion();		
	}
	
	/**
	 * Método que realiza el caso de uso de listar usuarios
	 * @param cadmin controlador de sesión de administrador
	 */
	private static void casosUsoAdminIter0(ControladorAdministrador cadmin) {
		System.out.println("\n\n/// LISTAR USUARIOS ///\n");

		// ------------------------------------
		// -- Usuario admin (ADMINISTRADOR) --
		// ------------------------------------
		System.out.println("<<inicio sesión admin>>");
		System.out.println("(admin inicial creado en el main)");
		try {
			cadmin.identificarAdministrador("admin","admin");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		
		System.out.println("\nlista de ciclistas:");
		try {
			List<String> descs = cadmin.listarUsuariosTipo("Ciclista");
			for (String desc :descs)
				System.out.println(desc+"\n");
			System.out.println("hay "+descs.size()+" usuarios de tipo \"Ciclista\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nlista de administradores:");
		try {
			List<String> descs = cadmin.listarUsuariosTipo("Administrador");
			for (String desc : descs)
				System.out.println(desc+"\n");
			System.out.println("hay "+descs.size()+" usuarios de tipo \"Administrador\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}	
		System.out.println("\n<<cierre sesión admin>>");
		cadmin.cerrarSesion();
	}
	

	/**
	 * Método que realiza los casos de uso del administrador de la iteración 1
	 * @param cadmin controlador de sesión de administrador
	 */
	private static void casosUsoAdminIter1(ControladorAdministrador cadmin) {
		System.out.println("/// REGISTRAR ESTACIÓN ///\n");
		

		// ------------------------------------
		// -- Usuario admin (ADMINISTRADOR) --
		// ------------------------------------
		System.out.println("<<inicio sesión admin>>");
		try {
			cadmin.identificarAdministrador("admin","admin");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		
		System.out.println("\nAdmin registra varias estaciones en el sistema");
		try {
			cadmin.registrarEstacion("e0", "Plaza Mayor", 5, 5, 5); // idEstacion, nombre, x, y, numAnclajes
			cadmin.registrarEstacion("e1", "Teleco", 8, 9, 3);
			cadmin.registrarEstacion("e2", "Peral", 1, 0, 3);
			cadmin.registrarEstacion("e3", "Parquesol", 3, 3, 3);
			cadmin.registrarEstacion("e4", "Campogrande", 4, 5, 3);
			cadmin.registrarEstacion("e5", "Rondilla", 7, 7, 3);
			cadmin.registrarEstacion("e6", "Pajarillos", 10, 8, 3);
			
			System.out.println("\n(la siguiente falla por utilizar un identificador repetido)");
			cadmin.registrarEstacion("e3", "Pinar de Jalón", 7, 1, 3);			
		} catch (UsuarioException | EstacionException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n\n/// REGISTRAR BICI ///");
		
		System.out.println("\nAdmin registra muchas bicis (eléctricas y mecánicas) en el sistema");
		try {
			cadmin.registrarBici(0, true, "e0"); // idBici, esElectrica, idEstacion
			cadmin.registrarBici(1, true, "e0");
			cadmin.registrarBici(2, false, "e0");
			cadmin.registrarBici(3, true, "e0");
			cadmin.registrarBici(4, false, "e1");
			cadmin.registrarBici(5, false, "e1");
			cadmin.registrarBici(6, false, "e1");
			cadmin.registrarBici(7, true, "e2");
			cadmin.registrarBici(8, true, "e4");
			cadmin.registrarBici(9, true, "e4");
			cadmin.registrarBici(10, false, "e4");
			cadmin.registrarBici(11, true, "e5");
			cadmin.registrarBici(12, false, "e5");
			cadmin.registrarBici(13, true, "e6");
			cadmin.registrarBici(14, false, "e6");
		} catch (UsuarioException | BiciException | EstacionException e) {
			System.out.println(e.getMessage());
		} 

		System.out.println("\n(los siguientes registros de bicis fallan por diversas razones)");
		try {
			cadmin.registrarBici(0, false, "e6");
		} catch (UsuarioException | BiciException | EstacionException e) {
			System.out.println(e.getMessage());
		} 
		try {
			cadmin.registrarBici(15, false, "e7");
		} catch (UsuarioException | BiciException | EstacionException e) {
			System.out.println(e.getMessage());
		} 
		try {
			cadmin.registrarBici(15, false, "e1");
		} catch (UsuarioException | BiciException | EstacionException e) {
			System.out.println(e.getMessage());
		} 
		
		System.out.println("\n<<cierre sesión admin>>");
		cadmin.cerrarSesion();
	}
	

	/**
	 * Método que realiza los casos de uso del ciclista de la iteración 1
	 * @param ccic controlador de sesión de ciclista
	 */
	private static void casosUsoCiclistaIter1(ControladorCiclista ccic) {
		System.out.println("\n\n/// VER ESTACIÓN ///\n");
		
		// ------------------------------------
		// -- Usuario cic0 (CICLISTA) --
		// ------------------------------------
		System.out.println("<<inicio sesión cic0 (Pogacar)>>");
		try {
			ccic.identificarCiclista("cic0","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		
		System.out.println("\nPogacar pide información de varias estaciones\n");
		try {
			System.out.println(ccic.verEstacion("e0") + "\n");
			System.out.println(ccic.verEstacion("e3") + "\n");
			System.out.println("(la siguiente estación no existe)");
			System.out.println(ccic.verEstacion("e10") + "\n");
		} catch (UsuarioException | EstacionException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n\n/// BUSCAR ESTACIONES ///");
		
		System.out.println("\nLa ordenación de estaciones es OPCIONAL (ver enunciado de la práctica).\n"
				+ "Si no se va a realizar la ordenación basta con no tratar el parámetro con el orden de la llamada.");			

		System.out.println("\nPogacar está en la posición (2.3, 3.1) y busca estaciones de acuerdo a varios criterios y con distinto orden");

		System.out.println("\nBúsqueda de estaciones con bicis disponibles a menos de 5km, ordenadas por cercanía al ciclista:");		
		try {
			// buscarBici = true => sólo valen estaciones con alguna bici disponible
			for (String s : ccic.buscarEstaciones(2.3, 3.1, 5, true)) // xCic, yCic, distMax, buscarBici, orden
				System.out.println(s);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\nBúsqueda de estaciones con anclajes disponibles a menos de 6km, ordenadas por número de anclajes:");
		try {
			// buscarBici = false => sólo valen estaciones con algún anclaje disponible
			for (String s : ccic.buscarEstaciones(2.3, 3.1, 6, false)) // xCic, yCic, distMax, buscarBici, orden
				System.out.println(s);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nBúsqueda de estaciones con bicis disponibles a menos de 4km, ordenadas por número de bicis eléctricas:");
		try {
			for (String s : ccic.buscarEstaciones(2.3, 3.1, 4, true)) // xCic, yCic, distMax, buscarBici, orden
				System.out.println(s);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
				
		System.out.println("\n<<cierre sesión cic0>>");
		ccic.cerrarSesion();

		// ------------------------------------
		// -- Usuario cic1 (CICLISTA) --
		// ------------------------------------
		System.out.println("\n<<inicio sesión cic1 (Contador)>>");
		try {
			ccic.identificarCiclista("cic1","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nContador está en la posición (8.7, 9.3) y hace la misma búsqueda que la primera de Pogacar");

		System.out.println("\nBúsqueda de estaciones con bicis disponibles a menos de 5km, ordenadas por cercanía al ciclista:");		
		try {
			for (String s : ccic.buscarEstaciones(8.7, 9.3, 5, true)) // xCic, yCic, distMax, buscarBici, orden
				System.out.println(s);
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n<<cierre sesión ci10>>");
		ccic.cerrarSesion();		
	}
}
