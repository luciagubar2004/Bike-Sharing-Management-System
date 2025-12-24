package bicis.arranque;

import java.util.List;

import bicis.controladores.ControladorAdministrador;
import bicis.controladores.ControladorCiclista;
import bicis.excepciones.SaldoException;
import bicis.excepciones.UsuarioException;
import bicis.usuarios.GestorUsuarios;

public class PruebasBiciIter0 {

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
}
