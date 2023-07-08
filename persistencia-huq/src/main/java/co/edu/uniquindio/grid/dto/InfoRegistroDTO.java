/**
 * 
 */
package co.edu.uniquindio.grid.dto;

/**
 * INformación asociada a los registros realizados por los recolectores
 * 
 * @author EinerZG
 * @version 1.0
 */
public class InfoRegistroDTO {

	/**
	 * identificador de los registros
	 */
	private Long id;
	/**
	 * nombre del genero de la planta
	 */
	private String nombreGenero;
	/**
	 * nombde de las especies de plantas
	 */
	private String nombreEspecie;
	/**
	 * cedula del recolector que hizo el registro
	 */
	private String cedulaRecolector;
	/**
	 * email del regolector que hizo el registro
	 */
	private String emailRecolector;

	/**
	 * Constuctor general del DTO
	 */
	public InfoRegistroDTO(Long id, String nombreGenero, String nombreEspecie, String cedulaRecolector,
			String emailRecolector) {
		super();
		this.id = id;
		this.nombreGenero = nombreGenero;
		this.nombreEspecie = nombreEspecie;
		this.cedulaRecolector = cedulaRecolector;
		this.emailRecolector = emailRecolector;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombreGenero
	 */
	public String getNombreGenero() {
		return nombreGenero;
	}

	/**
	 * @param nombreGenero the nombreGenero to set
	 */
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	/**
	 * @return the nombreEspecie
	 */
	public String getNombreEspecie() {
		return nombreEspecie;
	}

	/**
	 * @param nombreEspecie the nombreEspecie to set
	 */
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	/**
	 * @return the cedulaRecolector
	 */
	public String getCedulaRecolector() {
		return cedulaRecolector;
	}

	/**
	 * @param cedulaRecolector the cedulaRecolector to set
	 */
	public void setCedulaRecolector(String cedulaRecolector) {
		this.cedulaRecolector = cedulaRecolector;
	}

	/**
	 * @return the emailRecolector
	 */
	public String getEmailRecolector() {
		return emailRecolector;
	}

	/**
	 * @param emailRecolector the emailRecolector to set
	 */
	public void setEmailRecolector(String emailRecolector) {
		this.emailRecolector = emailRecolector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InfoRegistroDTO [id=" + id + ", nombreGenero=" + nombreGenero + ", nombreEspecie=" + nombreEspecie
				+ ", cedulaRecolector=" + cedulaRecolector + ", emailRecolector=" + emailRecolector + "]";
	}

}
