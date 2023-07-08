package co.edu.uniquindio.grid.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * Informacion que representa una especie vegetal
 * 
 * @author EinerZG
 * @version 1.0
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Especie.OBTENER_FAMILIA, query = "select e.genero.familia from Especie e where e.id= :id"),
		@NamedQuery(name = Especie.FAMILIA_MAS_ESPECIES, query = "select max(select count(e.genero.familia.id) from Especie e where f.id= e.genero.familia.id group by e.genero.familia) from Familia f"),
		@NamedQuery(name = Especie.NOMBRE_FAMILIA_MAX_ESPECIE, query = "select f.nombre from Familia f where (select count(e.genero.familia.id) from Especie e where f.id= e.genero.familia.id group by e.genero.familia)=:value") })
public class Especie implements Serializable {

	public static final String NOMBRE_FAMILIA_MAX_ESPECIE = "NombreFamiliaMaxEspecie";
	public static final String FAMILIA_MAS_ESPECIES = "FamiliaMasEspecies";

	/**
	 * referencia la consulta para obtener familia
	 */
	public static final String OBTENER_FAMILIA = "ObtenerFamilia";

	/**
	 * identificado unico de una especie vegetal a registrar
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 50)
	private Long id;
	/**
	 * nombre de las especies vegetales
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * lugar donde fue recolectada la especie vegetal
	 */
	private String lugar;
	/**
	 * genero al que pertenece la especie
	 */
	@ManyToOne
	private Genero genero;
	/**
	 * registro en que fue agregada la planta.
	 */
	@OneToOne
	private Registro registro;

	/**
	 * representa la imagen que se desea almacenar en la base de datos
	 */
	@Lob
	private byte[] imagen;
	private static final long serialVersionUID = 1L;

	public Especie() {
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
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return the imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the registro
	 */
	public Registro getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	/**
	 * @return the genero
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
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
		Especie other = (Especie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
