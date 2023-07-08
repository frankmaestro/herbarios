package co.edu.uniquindio.grid.entidades;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author EinerZG
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestJPQL {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * prueba la busqueda de un elemento en la base de datos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json" })
	public void listarPersonasSinRegistros() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONAS_SIN_REGISTROS, Persona.class);
		List<Persona> lista = query.getResultList();
		Iterator<Persona> ite = lista.iterator();
		while (ite.hasNext()) {
			//System.out.println(ite.next().getCedula());
		}
	}

	/**
	 * prueba la el conteo de registros por persona
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json" })
	public void contarRegistrosPorPersona() {

		Query query = entityManager.createNamedQuery(Registro.REGISTROS_POR_PERSONA);
		List<Object[]> lista = query.getResultList();
		Iterator<Object[]> ite = lista.iterator();
		System.out.println("eestoy acá");

		while (ite.hasNext()) {
			Object[] i = ite.next();
			System.out.println(i[0]);
			System.out.println(i[1]);
			System.out.println("------------");
		}

	}

	/**
	 * prueba mas plantas de familia
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "especie.json", "genero.json", "familia.json" })
	public void plataMasFamiliasTest() {
		
		TypedQuery<Long> query = entityManager.createNamedQuery(Especie.FAMILIA_MAS_ESPECIES, Long.class);
		Long max = query.getSingleResult();
		
		TypedQuery<String> query2 = entityManager.createNamedQuery(Especie.NOMBRE_FAMILIA_MAX_ESPECIE, String.class);
		query2.setParameter("value", max);
		String nombreFamilia = query2.getSingleResult();
		
		System.out.println(nombreFamilia);
		
		
	}

}
