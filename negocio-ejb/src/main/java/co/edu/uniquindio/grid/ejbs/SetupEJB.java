package co.edu.uniquindio.grid.ejbs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.grid.entidades.Administrador;
import co.edu.uniquindio.grid.entidades.Empleado;
import co.edu.uniquindio.grid.entidades.Especie;
import co.edu.uniquindio.grid.entidades.Estado;
import co.edu.uniquindio.grid.entidades.Familia;
import co.edu.uniquindio.grid.entidades.Genero;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public SetupEJB() {
	}

	/**
	 * Permite registrar un administrador si no hay aún registrados
	 */
	@PostConstruct
	public void init() {

		TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.CONTAR_ADMINS, Long.class);
		long numeroAdmins = query.getSingleResult();

		if (numeroAdmins == 0) {

			// adicción de investigadores
			Administrador administrador = new Administrador();
			administrador.setCedula("1094238912");
			administrador.setNombre("Pepito");
			administrador.setApellido("Perez");
			administrador.setEmail("pperez@mail.com");
			administrador.setClave("12345");
			administrador.setEstado(Estado.activo);
			administrador.setFechaNacimiento(new Date());
			administrador.setSalario(20000000);

			entityManager.persist(administrador);

			Empleado empleado1 = new Empleado();
			empleado1.setCedula("1095689010");
			empleado1.setNombre("Josefa");
			empleado1.setApellido("Ortiz");
			empleado1.setEmail("jortiz@mail.com");
			empleado1.setClave("12345");
			empleado1.setEstado(Estado.activo);
			empleado1.setFechaNacimiento(new Date());
			empleado1.setSalario(1200000);

			entityManager.persist(empleado1);

			Empleado empleado2 = new Empleado();
			empleado2.setCedula("10973438029");
			empleado2.setNombre("Chantal");
			empleado2.setApellido("Ruiz");
			empleado2.setEmail("cruiz@mail.com");
			empleado2.setClave("12345");
			empleado2.setEstado(Estado.activo);
			empleado2.setFechaNacimiento(new Date());
			empleado2.setSalario(1200000);

			entityManager.persist(empleado2);

			// crear de familias
			Familia familia1 = new Familia();
			familia1.setNombre("Familia1");

			Familia familia2 = new Familia();
			familia2.setNombre("Familia2");

			// crear de genero

			Genero genero1 = new Genero();
			genero1.setNombre("genero1");

			Genero genero2 = new Genero();
			genero2.setNombre("genero2");

			Genero genero3 = new Genero();
			genero3.setNombre("genero3");

			// registrar familias
			List<Genero> generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero2);

			List<Genero> generos2 = new ArrayList<>();
			generos2.add(genero3);

			familia1.setGeneros(generos);
			familia2.setGeneros(generos2);

			entityManager.persist(familia1);
			entityManager.persist(familia2);

			// crear plantas
			Especie especie1 = new Especie();
			especie1.setNombre("planta1");
			especie1.setLugar("colombia");

			Especie especie2 = new Especie();
			especie2.setNombre("planta2");
			especie2.setLugar("canada");

			Especie especie3 = new Especie();
			especie3.setNombre("planta3");
			especie3.setLugar("usa");

			Especie especie4 = new Especie();
			especie4.setNombre("planta4");
			especie4.setLugar("inglaterra");

			// registrar generos
			List<Especie> especies1 = new ArrayList();
			especies1.add(especie1);
			especies1.add(especie2);
			especies1.add(especie3);

			List<Especie> especies2 = new ArrayList();
			especies2.add(especie4);

			genero1.setFamilia(familia1);
			genero2.setFamilia(familia1);
			genero3.setFamilia(familia2);

			genero1.setEspecies(especies1);
			genero2.setEspecies(especies2);

			entityManager.persist(genero1);
			entityManager.persist(genero2);
			entityManager.persist(genero3);

			// registrar plantas
			especie1.setGenero(genero1);
			especie2.setGenero(genero1);
			especie3.setGenero(genero1);
			especie4.setGenero(genero2);

			entityManager.persist(especie1);
			entityManager.persist(especie2);
			entityManager.persist(especie3);
			entityManager.persist(especie4);

		}

	}

}
