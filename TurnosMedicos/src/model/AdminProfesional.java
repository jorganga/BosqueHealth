package model;

import java.util.ArrayList;

public class AdminProfesional {
	
	private ArrayList<Profesional> listaProfesionales;
	private Profesional usuarioLogeado;
	
	
	
	public Profesional getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(Profesional usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}

	public AdminProfesional() {
		listaProfesionales = new ArrayList<Profesional>(); 
	}

	public boolean login(String userName, String pwd) {
		usuarioLogeado = listaProfesionales.stream()
	            .filter(p -> p.getIdentificacion().equals(userName))  // Filtra por id
	            .findFirst()                        // Obtiene el primer resultado encontrado
	            .orElse(null);                      // Retorna null si no se encuentra
		
		if (usuarioLogeado != null)
		{
			if (usuarioLogeado.getClave().equals(pwd)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void CargarEspecialistas(ArrayList<Especialidad> listaEspecialidades) {
		//cargar especialistas por cada especialidad
		int idEspecialidad = 0; //medicina interna
		Especialidad especialidad = listaEspecialidades.get(idEspecialidad);
		
		listaProfesionales.add(new Profesional("Nicolás Angulo", "1034517158", "jor_angulo@yahoo.es", 0, null, 0, especialidad, true, "123"));
		
		listaProfesionales.add(new Profesional("Carlos Martínez", "12345678", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "12345678"));
	
		listaProfesionales.add(new Profesional("Ana Gómez", "87654321", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "87654321"));
		listaProfesionales.add(new Profesional("Luis Rodríguez", "11223344", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "11223344"));
//		listaProfesionales.add(new Profesional("Maria Fernanda", "33445566", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "33445566"));
//		listaProfesionales.add(new Profesional("Jorge Salazar", "55667788", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55667788"));
//		listaProfesionales.add(new Profesional("Lucía Pérez", "99887766", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "99887766"));
//		listaProfesionales.add(new Profesional("Pedro Sánchez", "44556677", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "44556677"));

		idEspecialidad++; //cirugía
		especialidad = listaEspecialidades.get(idEspecialidad);
		listaProfesionales.add(new Profesional("Ricardo Torres", "22334455", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "22334455"));
		listaProfesionales.add(new Profesional("Elena Ríos", "66778899", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "66778899"));
		listaProfesionales.add(new Profesional("Manuel Herrera", "99881122", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "99881122"));
		listaProfesionales.add(new Profesional("Patricia López", "55669988", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55669988"));
//		listaProfesionales.add(new Profesional("Fernando Castro", "11227788", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "11227788"));
//		listaProfesionales.add(new Profesional("Gloria Méndez", "33442211", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "33442211"));
//		listaProfesionales.add(new Profesional("Raúl Vargas", "77889911", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "77889911"));
//
        
//        idEspecialidad++; //oncología
//		especialidad = listaEspecialidades.get(idEspecialidad);
//		listaProfesionales.add(new Profesional("Santiago Peña", "55443322", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55443322"));
//		listaProfesionales.add(new Profesional("Carmen Ortiz", "99882233", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "99882233"));
//		listaProfesionales.add(new Profesional("Gabriel Ruiz", "66779911", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "66779911"));
//		listaProfesionales.add(new Profesional("Laura Jiménez", "22338877", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "22338877"));
//		listaProfesionales.add(new Profesional("Andrés Pardo", "44556699", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "44556699"));
//		listaProfesionales.add(new Profesional("Marta Reyes", "77886655", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "77886655"));
//		listaProfesionales.add(new Profesional("Esteban Silva", "11225544", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "11225544"));
//
//
//        idEspecialidad++; //neumología
//		especialidad = listaEspecialidades.get(idEspecialidad);
//		listaProfesionales.add(new Profesional("Paula Mendoza", "33446688", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "33446688"));
//		listaProfesionales.add(new Profesional("Roberto Cáceres", "44557799", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "44557799"));
//		listaProfesionales.add(new Profesional("Daniela Pérez", "22339955", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "22339955"));
//		listaProfesionales.add(new Profesional("Ignacio Núñez", "99884422", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "99884422"));
//		listaProfesionales.add(new Profesional("Verónica Vega", "55668833", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55668833"));
//		listaProfesionales.add(new Profesional("Felipe Moreno", "66771100", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "66771100"));
//		listaProfesionales.add(new Profesional("Rosa Sandoval", "88990022", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "88990022"));
//
//        
//        idEspecialidad++; //dermatología
//		especialidad = listaEspecialidades.get(idEspecialidad);
//		listaProfesionales.add(new Profesional("Antonio Duarte", "33447788", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "33447788"));
//		listaProfesionales.add(new Profesional("Silvia Navarro", "55664433", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55664433"));
//		listaProfesionales.add(new Profesional("Tomás Figueroa", "11228844", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "11228844"));
//		listaProfesionales.add(new Profesional("Clara Villalba", "77889922", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "77889922"));
//		listaProfesionales.add(new Profesional("Miguel Ángel Soto", "66778855", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "66778855"));
//		listaProfesionales.add(new Profesional("Julia Castro", "22334466", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "22334466"));
//		listaProfesionales.add(new Profesional("Raquel Padilla", "99887744", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "99887744"));
//
//        
//        idEspecialidad++; //cardiología
//		especialidad = listaEspecialidades.get(idEspecialidad);
//		listaProfesionales.add(new Profesional("Alberto Mejía", "44557788", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "44557788"));
//		listaProfesionales.add(new Profesional("Diana Paredes", "11229988", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "11229988"));
//		listaProfesionales.add(new Profesional("Ricardo Valenzuela", "22336644", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "22336644"));
//		listaProfesionales.add(new Profesional("Natalia Carrillo", "33448899", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "33448899"));
//		listaProfesionales.add(new Profesional("Sofía Rincón", "55667722", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "55667722"));
//		listaProfesionales.add(new Profesional("Javier Duarte", "77884466", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "77884466"));
//		listaProfesionales.add(new Profesional("Mónica Salinas", "88992211", "jor_angulo@yahoo.es", 0, null, 0, especialidad, false, "88992211"));

	}

	public ArrayList<Profesional> getListaProfesionales() {
		return listaProfesionales;
	}

	public void setListaProfesionales(ArrayList<Profesional> listaProfesionales) {
		this.listaProfesionales = listaProfesionales;
	}

	
}
