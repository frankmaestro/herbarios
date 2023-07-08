package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Informacion basica de cada una de las personas asociadas a la entidad
 * bancaria
 * 
 * @author EinerZG
 * @version 1.0 23/03/2019
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = Persona.LISTAR_TODOS, query = "select p from Persona p"),
		@NamedQuery(name = Persona.OBTENER_POR_CREDENCIALES, query = "select p from Persona p where p.email= :email and p.clave= :clave "),
		@NamedQuery(name = Persona.OBTENER_REGISTROS_DE_RECOLECTOR, query = "select r from Persona p inner join p.registros r where p.cedula= :cedula"),
		@NamedQuery(name = Persona.OBTENER_REGISTRO_POR_RECOLECTOR, query = "select p.cedula, r from Persona p left join p.registros r"),
		@NamedQuery(name = Persona.PERSONAS_SIN_REGISTROS, query = "select p from Persona p where p.registros is empty"),
		@NamedQuery(name = Persona.BUSCAR_POR_EMAIL, query = "select p from Persona p where p.email=:email") })
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * referencia la consulta para buscar registros por email
	 */
	public static final String BUSCAR_POR_EMAIL = "BuscarPorEmail";

	/**
	 * referencia la consulta que permite deteerminar las personas sin registros
	 */
	public static final String PERSONAS_SIN_REGISTROS = "PersonasSinRegitros";

	/**
	 * referencia a la consulta para obtener los registros por recolector
	 */
	public static final String OBTENER_REGISTRO_POR_RECOLECTOR = "RegistrosPoRecolector";

	/**
	 * referencia al query para obtener los envios de un recolector
	 */
	public static final String OBTENER_REGISTROS_DE_RECOLECTOR = "ObtenerRegistrosDeRecolector";

	/**
	 * referencia al query para buscar personas por credenciales
	 */
	public static final String OBTENER_POR_CREDENCIALES = "ObtenerCredenciales";

	/**
	 * referencia para listar los clientes
	 */
	public static final String LISTAR_TODOS = "ListarLosClientes";

	/**
	 * cedula de la persona
	 */
	@Id
	@Column(length = 12)
	private String cedula;
	/**
	 * nombre de la persona
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * apellido de la persona
	 */
	@Column(length = 50)
	private String apellido;
	/**
	 * email de la persona
	 */
	@Column(length = 50)
	private String email;
	/**
	 * clave de acceso a la plataforma
	 */
	@Column(length = 20)
	private String clave;
	/**
	 * estado activo o inactivo
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private Estado estado;
	/**
	 * fecha de nacimiento de la persona
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	/**
	 * lista de registros enviada por en usuario
	 */
	@OneToMany(mappedBy = "recolector")
	private List<Registro> registros;

	public Persona() {
		super();
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return the registros
	 */
	public List<Registro> getRegistros() {
		return registros;
	}

	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
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
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
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
		Persona other = (Persona) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

}
