package co.edu.uniquindio.grid.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Empleado;
import co.edu.uniquindio.grid.entidades.Estado;
import co.edu.uniquindio.grid.entidades.Familia;
import co.edu.uniquindio.grid.entidades.Genero;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.excesiones.ElementoNoEncontradoException;
import co.edu.uniquindio.grid.excesiones.ElementoRepetidoException;

/**
 * Maneja todas las operaciones asociadas a los adminstradores
 * 
 * @author EinerZG
 * @version 1.0
 */
@Stateless
@LocalBean
public class AdminEJB implements AdminEJBRemote {

	/**
	 * permite hacer todas las transacciones de la base de datos
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public AdminEJB() {
	}

	/**
	 * Permite obtener una persona usando las credenciales de acceso
	 * 
	 * @param email email de la persona
	 * @param clave clave de la persona
	 * @return persona que inicia sesion o null
	 */
	public Persona iniciarSesion(String email, String clave) {

		try {

			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.OBTENER_POR_CREDENCIALES, Persona.class);
			query.setParameter("email", email);
			query.setParameter("clave", clave);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#registrarFamilia(co.edu.uniquindio
	 * .grid.entidades.Familia)
	 */
	public Familia registrarFamilia(Familia familia) throws ElementoRepetidoException {
		if (buscarFamiliaPorNombre(familia.getNombre()) != null) {
			throw new ElementoRepetidoException("Ya existe una familia con ese nombre");
		} else {
			try {
				entityManager.persist(familia);
				return buscarFamiliaPorNombre(familia.getNombre());
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * permite elminar una familia de la base de datos
	 * 
	 * @param familia familia a eliminar
	 * @return familia eliminada o null
	 * @throws ElementoNoEncontradoException
	 */
	public Familia eliminarFamilia(Long familia) throws ElementoNoEncontradoException {
		Familia f = entityManager.find(Familia.class, familia);
		if ( f == null) {
			throw new ElementoNoEncontradoException("Familia no encontrada");
		} else {
			try {
				f.setGeneros(listarGenerosPorFamilia(f.getNombre()));
				entityManager.remove(f);
				return f;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#insertarEmpleado(co.edu.uniquindio
	 * .grid.entidades.Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException {
		if (entityManager.find(Persona.class, empleado.getCedula()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con esta cedula");
		} else if (buscarPersonaPorEmail(empleado.getEmail()) != null) {
			throw new ElementoRepetidoException("Ya existe una persona registrada con este email");
		}
		try {
			empleado.setEstado(Estado.activo);
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#eliminarPersona(java.lang.String)
	 */
	public Persona eliminarPersona(String cedula) throws ElementoNoEncontradoException {
		Persona persona = entityManager.find(Empleado.class, cedula);
		if (persona == null) {
			throw new ElementoNoEncontradoException("Persona no encontrada en los registros");
		} else {
			persona.setEstado(Estado.inactivo);
			entityManager.merge(persona);
			return persona;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#listarEmpleados()
	 */
	public List<Empleado> listarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.LISTAR_EMPLEADOS, Empleado.class);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#listarFamilias()
	 */
	public List<Familia> listarFamilias() {
		TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.LISTAR_FAMILIAS, Familia.class);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#eliminarGenero(long)
	 */
	public Genero eliminarGenero(long idGenero) throws ElementoNoEncontradoException {
		Genero genero = entityManager.find(Genero.class, idGenero);
		if (genero == null) {
			throw new ElementoNoEncontradoException("El genero con este id no se encuentra registrado");
		} else {
			try {
				entityManager.remove(genero);
				return genero;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.edu.uniquindio.grid.ejbs.AdminEJBRemote#registrarGenero(co.edu.uniquindio.
	 * grid.entidades.Genero)
	 */
	public Genero registrarGenero(Genero genero) throws Exception {
		if (buscarGeneroPorNombre(genero.getNombre()) != null) {
			throw new ElementoRepetidoException("El genero con este id ya fue registrado");
		} else if (genero.getFamilia() == null) {
			throw new Exception("El genero debe tener una familia relacionada");
		} else {
			try {
				entityManager.persist(genero);
				return genero;
			} catch (Exception e) {
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#buscarFamilia(java.lang.Long)
	 */
	public Familia buscarFamilia(Long idFamilia) {
		return entityManager.find(Familia.class, idFamilia);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.edu.uniquindio.grid.ejbs.AdminEJBRemote#listarGeneros()
	 */
	public List<Genero> listarGeneros() {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.LISTAR_GENEROS, Genero.class);
		return query.getResultList();
	}

	/**
	 * permite buscar una familia por el nombre
	 * 
	 * @param nombre nombre de la familia que se desea buscar
	 * @return familia buscada o null
	 */
	private Familia buscarFamiliaPorNombre(String nombre) {

		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Familia.BUSCAR_FAMILIA_POR_NOMBRE,
					Familia.class);
			query.setParameter("nombre", nombre);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Lista de generos asociados a una familia
	 * @param nombreFamilia nombre de la familia 
	 * @return lista de generos asociado
	 */
	private List<Genero> listarGenerosPorFamilia( String nombreFamilia ) {
		TypedQuery<Genero> query = entityManager.createNamedQuery(Familia.LISTAR_GENEROS_POR_FAMILIA, Genero.class);
		query.setParameter("nombre", nombreFamilia);
		return query.getResultList();
	}
	
	/**
	 * permite buscar un genero por su nombre
	 * 
	 * @param nombreGenero nombre del genero
	 * @return genero buscado o null
	 */
	private Genero buscarGeneroPorNombre(String nombreGenero) {
		try {
			TypedQuery<Genero> query = entityManager.createNamedQuery(Genero.GENERO_POR_NOMBRE, Genero.class);
			query.setParameter("nombre", nombreGenero);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * permite buscar una personas usando su email
	 * 
	 * @param email email de la presona
	 * @return persona con el email especificado
	 */
	private Persona buscarPersonaPorEmail(String email) {

		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.BUSCAR_POR_EMAIL, Persona.class);
			query.setParameter("email", email);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

}
