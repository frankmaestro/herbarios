package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Información que representa la información de un envio
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Registro.REGISTROS_POR_PERSONA, query = "select r.recolector.nombre, count(r.recolector) from Registro r group by r.recolector") })
public class Registro implements Serializable {

	private static final long serialVersionUID = 1L;

	private enum Estado {
		Pendiente, Aceptado, Rechazado
	}

	/**
	 * referencia la consulta que permite contar registros por persona
	 */
	public static final String REGISTROS_POR_PERSONA = "RegistrosPorPersona";

	/**
	 * Valor que identifica de forma única a las especie vegetales
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * fecha en que se realizó el envío
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEnvio;
	/**
	 * mensaje de respuesta al envio
	 */
	private String mensaje;
	/**
	 * estado del envío
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private Estado estado;
	/**
	 * Especie que se envio en el registro
	 */
	@OneToOne(mappedBy = "registro")
	private Especie especie;
	/**
	 * el recolector q realiza el envío
	 */
	@ManyToOne
	private Persona recolector;

	public Registro() {
		super();
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
	 * @return the fechaEnvio
	 */
	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the especie
	 */
	public Especie getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the recolector
	 */
	public Persona getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Persona recolector) {
		this.recolector = recolector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
