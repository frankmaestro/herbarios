package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Información que representa el genero de una familia de plantas
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQueries({ @NamedQuery(name = Genero.LISTAR_GENEROS, query = "select g from Genero g"),
		@NamedQuery(name = Genero.GENERO_POR_NOMBRE, query = "select g from Genero g where g.nombre=:nombre"),
		@NamedQuery(name = Genero.OBTENER_ESPECIES_POR_GENERO, query = "select g from Genero g, in(g.especies) e where g.id= :id"),
		@NamedQuery(name = Genero.LISTAR_REGISTROS_POR_FECHA, query = "select new co.edu.uniquindio.grid.dto.InfoRegistroDTO( e.registro.id, "
				+ "g.nombre, e.nombre, e.registro.recolector.nombre, e.registro.recolector.email) from Genero g inner join g.especies e where "
				+ "e.registro.fechaEnvio= :fecha") })
public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * referencia la consulta para obtener todos los generos
	 */
	public static final String LISTAR_GENEROS = "ListarGeneros";
	/**
	 * referencia la consulta para obtener un genero por nombre
	 */
	public static final String GENERO_POR_NOMBRE = "GeneroPorNombre";
	/**
	 * referencia la consuta para listar registros por fecha
	 */
	public static final String LISTAR_REGISTROS_POR_FECHA = "ListarRegistrosPorFecha";

	/**
	 * referencia la consulta para obtener los generos por especie
	 */
	public static final String OBTENER_ESPECIES_POR_GENERO = "EspeciesPorGenero";

	/**
	 * valor que identifica de forma única al genero
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * Nombre de la especie del genero vegetal
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * familia a la que pertenece el genero
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Familia familia;
	/**
	 * lista de especies que hacen parte del genero
	 */
	@OneToMany(mappedBy = "genero", orphanRemoval = true)
	private List<Especie> especies;

	public Genero() {
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
	 * @return the especies
	 */
	public List<Especie> getEspecies() {
		return especies;
	}

	/**
	 * @param especies the especies to set
	 */
	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
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
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
