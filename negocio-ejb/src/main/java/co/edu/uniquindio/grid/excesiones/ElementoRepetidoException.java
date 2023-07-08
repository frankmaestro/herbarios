/**
 * 
 */
package co.edu.uniquindio.grid.excesiones;

/**
 * Excepción lanzada cuando se encuentra información repetida
 * 
 * @author EinerZG
 * @version 1.0
 */
public class ElementoRepetidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ElementoRepetidoException(String mensaje) {
		super(mensaje);
	}

}
