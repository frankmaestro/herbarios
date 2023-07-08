package co.edu.uniquindio.grid.entidades;

import co.edu.uniquindio.grid.entidades.Empleado;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Información general de un administrador
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQuery(name = Administrador.CONTAR_ADMINS, query = "select count(a) from Administrador a")
public class Administrador extends Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * referencia a la consulta para contar administradores
	 */
	public static final String CONTAR_ADMINS = "ContarAdmins";

	public Administrador() {
		super();
	}

}
