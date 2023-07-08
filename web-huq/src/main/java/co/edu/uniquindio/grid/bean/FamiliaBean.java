/**
 * 
 */
package co.edu.uniquindio.grid.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.edu.uniquindio.grid.ejbs.AdminEJB;
import co.edu.uniquindio.grid.entidades.Familia;
import co.edu.uniquindio.grid.excesiones.ElementoNoEncontradoException;
import co.edu.uniquindio.grid.excesiones.ElementoRepetidoException;
import co.edu.uniquindio.grid.util.Util;

/**
 * Permite manejar todas las operaciones con familia
 * 
 * @author EinerZG
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaBean")
@SessionScoped
public class FamiliaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * familia registrada
	 */
	private Familia familia;

	/**
	 * nombre de la familia de plantas
	 */
	private String nombre;

	/**
	 * listado total de familias de plantas
	 */
	private List<Familia> familias;

	/**
	 * EJB de negocio donde se gestionan las familias de plantas
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * carga la lista de familias
	 */
	@PostConstruct
	private void init() {
		familias = adminEJB.listarFamilias();
	}

	/**
	 * permite invocar la capa de negocio para registrar una familia de plantas en
	 * la base de datos
	 * 
	 * @return nombre de la página con información de las plantas
	 */
	public String agregarFamilia() {
		try {
			Familia f = new Familia();
			f.setNombre(nombre);
			f = adminEJB.registrarFamilia(f);
			familia = f;
			familias = adminEJB.listarFamilias();

			return "/admin/familia/familias";
		} catch (ElementoRepetidoException e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
			return null;
		}
	}

	/**
	 * permite obtener la familia que se desea eliminar
	 */
	public void eliminarFamilia() {
		try {
			adminEJB.eliminarFamilia(familia.getId());
			familias = adminEJB.listarFamilias();
			Util.mostrarMensaje("Eliminación exitosa!!!", "Eliminación exitosa!!!");
		} catch (ElementoNoEncontradoException e) {
			Util.mostrarMensaje(e.getMessage(), e.getMessage());
		}
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the familia
	 */
	public Familia getFamilia() {
		return familia;
	}

	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	/**
	 * @return the familias
	 */
	public List<Familia> getFamilias() {
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

}
