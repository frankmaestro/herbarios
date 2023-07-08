package co.edu.uniquindio.grid.ejbs;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.grid.entidades.Empleado;
import co.edu.uniquindio.grid.entidades.Familia;
import co.edu.uniquindio.grid.entidades.Genero;
import co.edu.uniquindio.grid.entidades.Persona;
import co.edu.uniquindio.grid.excesiones.ElementoNoEncontradoException;
import co.edu.uniquindio.grid.excesiones.ElementoRepetidoException;

/**
 * Interfaz que representa las transacciones que se pueden realizar desde la
 * capa de presentación
 * 
 * @author EinerZG
 * @version 1.0
 *
 */
@Remote
public interface AdminEJBRemote {

	String JNDI = "java:global/ear-huq/negocio-huq/AdminEJB!co.edu.uniquindio.grid.ejbs.AdminEJBRemote";

	/**
	 * Permite agregar una persona en la base de datos
	 * 
	 * @param empleado empleado a insetar
	 * @return deveuelve el empleado insertado o null
	 * @throws ElementoRepetidoException cuando hay informacion repetida
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoException;

	/**
	 * Permite eliminar una persona según por medio de su cedula
	 * 
	 * @param cedula cedula de la persona
	 * @return persona elminada
	 * @throws ElementoNoEncontradoException si la persona no es encontrada
	 */
	Persona eliminarPersona(String cedula) throws ElementoNoEncontradoException;

	/**
	 * Permite mostrar todos loe empleados registrado
	 * 
	 * @return lista de empleado
	 */
	List<Empleado> listarEmpleados();

	/**
	 * Permite registrar una familia de plantas
	 * 
	 * @param familia familia que se desea registrar
	 * @return familia registrada o null
	 * @throws ElementoRepetidoException si la familia ya se ha registrado
	 */
	Familia registrarFamilia(Familia familia) throws ElementoRepetidoException;

	/**
	 * Permite listar las familias de las plantas
	 * 
	 * @return lista de familias de plantas
	 */
	List<Familia> listarFamilias();

	/**
	 * Permite eliminar un genero
	 * 
	 * @param idGenero
	 * @return el genero eliminado o null
	 * @throws ElementoNoEncontradoException
	 */
	Genero eliminarGenero(long idGenero) throws ElementoNoEncontradoException;

	/**
	 * Permite registrar un genero
	 * 
	 * @param genero genero a registrar
	 * @return genero registrado o null
	 * @throws Exception si
	 */
	Genero registrarGenero(Genero genero) throws Exception;

	/**
	 * Permite listar todos los generos registrados en la base de datos
	 * 
	 * @return todos los generos
	 */
	List<Genero> listarGeneros();

	/**
	 * Permite buscar una familia por su id
	 * 
	 * @param idFamilia id de la familia a buscar
	 * @return familia a buscar
	 */
	Familia buscarFamilia(Long idFamilia);

}
