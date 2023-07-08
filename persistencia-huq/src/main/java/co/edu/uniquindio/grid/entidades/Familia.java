package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Información de una familia de plantas
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Familia.BUSCAR_FAMILIA_POR_NOMBRE, query= "select f from Familia f where f.nombre=:nombre"),
	@NamedQuery(name=Familia.LISTAR_FAMILIAS, query= "select f from Familia f"),
	@NamedQuery(name= Familia.LISTAR_GENEROS_POR_FAMILIA, query= "select g from Familia f inner join f.generos g where f.nombre=:nombre")
})
public class Familia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * referencia el query para listar las familias de plantas
	 */
	public static final String LISTAR_FAMILIAS = "ListarFamilias";
	
	/**
	 * listar generos de la familia
	 */
	public static final String LISTAR_GENEROS_POR_FAMILIA = "ListarGenerosPorFamilia";
	
	/**
	 * referencia la query para realizar busqueda por nombre
	 */
	public static final String BUSCAR_FAMILIA_POR_NOMBRE = "BuscarFamiliaPorNombre";
	
	/**
	 * valor q identifica de forma unica a la familia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * nombre de la familia
	 */
	private String nombre;
	/**
	 * lista de generos de la familia de plantas
	 */
	@OneToMany(mappedBy="familia", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<Genero> generos;

	public Familia() {
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
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	/**
	 * Nombre de la familia de plantas
	 */
	@Override
	public String toString() {
		return nombre;
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
		Familia other = (Familia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
