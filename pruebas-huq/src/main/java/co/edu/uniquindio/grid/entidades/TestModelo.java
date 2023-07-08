package co.edu.uniquindio.grid.entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.grid.dto.InfoRegistroDTO;

/**
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author EinerZG
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestModelo {

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
	@UsingDataSet({ "persona.json" })
	public void buscarPersona() {
		Empleado empleado = entityManager.find(Empleado.class, "949876543");
		Assert.assertEquals("jvargas@mail.com", empleado.getEmail());
	}

	/**
	 * prueba para la inserción de personas en la base de datos
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarPersona() {

		Administrador administrador = new Administrador();
		administrador.setCedula("1234567");
		administrador.setNombre("Jaime");
		administrador.setApellido("Suarez");
		administrador.setClave("12345");
		administrador.setEmail("jsuare@mail.com");
		administrador.setEstado(Estado.activo);
		administrador.setSalario(20000000);

		Persona p = entityManager.find(Persona.class, "1234567");
		Assert.assertNull(p);

		entityManager.persist(administrador);

		p = entityManager.find(Persona.class, "1234567");
		Assert.assertNotNull(p);

	}

	/**
	 * Permite probar el inicio de sesion en la aplicaccion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonasUnoTest() {
		Query query = entityManager.createQuery("select p from Persona p");
		int tamanio = query.getResultList().size();
		Assert.assertEquals(tamanio, 3);
	}

	/**
	 * Permite probar el inicio de sesion en la aplicaccion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonasDosTest() {
		Query query = entityManager.createNamedQuery(Persona.LISTAR_TODOS);
		int tamanio = query.getResultList().size();
		Assert.assertEquals(tamanio, 3);
	}

	/**
	 * Permite probar el funcionamiento del inicio de sesion
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void iniciarSessionTest() {

		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.OBTENER_POR_CREDENCIALES, Persona.class);
			query.setParameter("email", "pperez@mail.com");
			query.setParameter("clave", "12345");
			Persona p = query.getSingleResult();
			Assert.assertNotNull(p);

		} catch (NoResultException e) {
			e.printStackTrace();
		}

	}

	/**
	 * permite probar obtener el nombre de una familia por especie
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "especie.json", "genero.json", "familia.json" })
	public void obtenerFamiliaDeEspecieTest() {

		try {
			TypedQuery<Familia> query = entityManager.createNamedQuery(Especie.OBTENER_FAMILIA, Familia.class);
			query.setParameter("id", 1);
			Familia familia = query.getSingleResult();
			Assert.assertEquals(familia.getNombre(), "Amaranthacea");
		} catch (NoResultException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permite probar el funcionamiento de listar los registros por recolector
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json" })
	public void obtenerRegistrosDeRecolectorTest() {
		TypedQuery<Registro> query = entityManager.createNamedQuery(Persona.OBTENER_REGISTROS_DE_RECOLECTOR,
				Registro.class);
		query.setParameter("cedula", "123456789");
		List<Registro> registros = query.getResultList();
		Assert.assertEquals(registros.size(), 2);
	}

	/**
	 * Permite probar el funcionamiento de listar las especies vegetales por genero
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "especie.json", "genero.json", "familia.json" })
	public void obtenerEspeciesPorGeneroTest() {
		TypedQuery<Especie> query = entityManager.createNamedQuery(Genero.OBTENER_ESPECIES_POR_GENERO, Especie.class);
		query.setParameter("id", 1);
		List<Especie> especies = query.getResultList();
		Assert.assertEquals(especies.size(), 2);
	}

	/**
	 * Permite probar el listado de registro por recolectores
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json" })
	public void obtenerRegistroPorRecolector() {
		Query query = entityManager.createNamedQuery(Persona.OBTENER_REGISTRO_POR_RECOLECTOR);
		Iterator<Object[]> lista = query.getResultList().iterator();

		while (lista.hasNext()) {
			Object[] elemeto = lista.next();
			String cedulaPersona = (String) elemeto[0];
			Registro registro = (Registro) elemeto[1];
			System.out.println(String.format("Cedula: %s, Registro id: %s", cedulaPersona, registro));
		}

	}

	/**
	 * permite obtener información general del registro usando un DTO
	 */
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "genero.json", "especie.json" })
	public void obtenerInformacionDeRegistro() {

		try {
			Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("2019-02-28 00:00:00.0");

			TypedQuery<InfoRegistroDTO> query = entityManager.createNamedQuery(Genero.LISTAR_REGISTROS_POR_FECHA,
					InfoRegistroDTO.class);
			query.setParameter("fecha", fecha);
			List<InfoRegistroDTO> registros = query.getResultList();
			Iterator<InfoRegistroDTO> iter = registros.iterator();

			while (iter.hasNext()) {
				InfoRegistroDTO type = iter.next();
				System.out.println(type);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
