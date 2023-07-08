/**
 * 
 */
package co.edu.uniquindio.grid.entidades;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.grid.ejbs.AdminEJB;
import co.edu.uniquindio.grid.excesiones.ElementoNoEncontradoException;
import co.edu.uniquindio.grid.excesiones.ElementoRepetidoException;

/**
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author EinerZG
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestNegocio {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * EJB que se desea probar
	 */
	@EJB
	private AdminEJB adminEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdminEJB.class).addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * prueba para la inserción de personas en la base de datos
	 * @throws ElementoRepetidoException 
	 */
	@Ignore
	@Test(expected = ElementoRepetidoException.class)
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarEmpleado() throws ElementoRepetidoException {

		Empleado empleado = new Empleado();
		empleado.setCedula("1234567");
		empleado.setNombre("Jaime");
		empleado.setApellido("Suarez");
		empleado.setClave("12345");
		empleado.setEmail("ybonilla@mail.com");
		empleado.setEstado(Estado.activo);
		empleado.setSalario(20000000);

		try {
			Persona persona = adminEJB.insertarEmpleado(empleado);
			Assert.assertNotNull(persona);
		} catch (ElementoRepetidoException e) {
			throw new ElementoRepetidoException(e.getMessage());
		}

	}
	
	/**
	 * prueba para eliminar un genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familia.json","genero.json", "especie.json" })
	public void eliminarGeneroTest() {
		try {
			Assert.assertNotNull(adminEJB.eliminarGenero(2l));
		} catch (ElementoNoEncontradoException e) {
			Assert.fail(e.getMessage());
		}

	}

}
