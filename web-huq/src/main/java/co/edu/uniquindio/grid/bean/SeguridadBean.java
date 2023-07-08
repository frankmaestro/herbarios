package co.edu.uniquindio.grid.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.edu.uniquindio.grid.ejbs.AdminEJB;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.util.Util;

/**
 * Permite manejar la sesión de la aplicacion
 * 
 * @author EinerZG
 * @version 1.0
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * usuario que tiene iniciada la sesion
	 */
	private Persona usuario;
	/**
	 * determina si esta autenticado o no
	 */
	private boolean autenticado;
	/**
	 * permite consultar la capa de negocio
	 */
	@EJB
	private AdminEJB adminEJB;

	/**
	 * inicializa la info basica para el manejo de la sesion
	 */
	@PostConstruct
	private void init() {
		usuario = new Persona();
	}

	/**
	 * permite iniciar sesión
	 */
	public void iniciarSesion() {

		Persona u = adminEJB.iniciarSesion(usuario.getEmail(), usuario.getClave());

		if (u == null) {
			Util.mostrarMensaje("No se pudo iniciar sesion verifique sus credenciales",
					"No se pudo iniciar sesion verifique sus credenciales");
		} else {
			usuario = u;
			autenticado = true;
		}
	}

	/**
	 * permite cerrar sesion
	 */
	public void cerrarSesion() {
		if (usuario != null) {
			usuario = null;
			autenticado = false;
			init();
		}
	}
	
	/**
	 * navegación del menu principal
	 * @param i 
	 * @return
	 */
	public String navegacion(int i) {
		switch (i) {
		case 1:
			return "/admin/familia/familias";
		case 2:
			return "/admin/familia/registrar_familia";
		case 3:
			return "/admin/genero/generos";
		case 4:
			return "/admin/genero/registrar_genero";
		default:
			return null;
		}
	}

	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the autenticado
	 */
	public boolean isAutenticado() {
		return autenticado;
	}

	/**
	 * @param autenticado the autenticado to set
	 */
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

}
