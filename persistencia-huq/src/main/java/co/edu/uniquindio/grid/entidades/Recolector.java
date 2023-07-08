package co.edu.uniquindio.grid.entidades;

import co.edu.uniquindio.grid.entidades.Persona;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Información general de un recolector
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
public class Recolector extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}
}
