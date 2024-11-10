	package model;

import static javax.swing.JOptionPane.showMessageDialog;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import model.persistence.DataMapperCita;
import model.persistence.DataMapperPaciente;
import model.persistence.DataMapperTratamientoMedico;
import model.persistence.DataMapperTurno;
import model.persistence.PacienteDAO;
import model.persistence.TratamientoMedicoDAO;
import model.persistence.TurnoDAO;

public class AdminPacientes {
	
	private ArrayList<Paciente> listadoPacientes;
	private PacienteDAO dao;
	
	public ArrayList<Paciente> getListadoPacientes() {
		return listadoPacientes;
	}
	
	public ArrayList<PacienteDTO> listarPacientes(){
		PacienteDAO pacienteDAO = new PacienteDAO();
		return pacienteDAO.getAll();
	}

	public void setListadoPacientes(ArrayList<Paciente> listadoPacientes) {
		this.listadoPacientes = listadoPacientes;
	}

	public AdminPacientes() {
		listadoPacientes = new ArrayList<Paciente>();
		dao = new PacienteDAO();
	}


	public void CargarPacientesDemo() {
		listadoPacientes.add(new Paciente("Luis Castro", "344324", "jor_angulo@yahoo.es", "A+", 70, LocalDate.of(1981,12, 16)));
		listadoPacientes.add(new Paciente("Marta Pérez", "456789", "jor_angulo@yahoo.es", "B+", 62, LocalDate.of(1987, 7, 13)));
		listadoPacientes.add(new Paciente("Juan Torres", "678901", "jor_angulo@yahoo.es", "O+", 85, LocalDate.of(1983, 2, 21)));
		listadoPacientes.add(new Paciente("Elena Morales", "234123", "jor_angulo@yahoo.es", "AB-", 59, LocalDate.of(1995, 4, 18)));
		listadoPacientes.add(new Paciente("Fernando López", "981234", "jor_angulo@yahoo.es", "O-", 76, LocalDate.of(1978, 10, 3)));
		listadoPacientes.add(new Paciente("Patricia Gómez", "564738", "jor_angulo@yahoo.es", "A-", 67, LocalDate.of(1982, 8, 28)));
		listadoPacientes.add(new Paciente("Roberto Díaz", "129834", "jor_angulo@yahoo.es", "B-", 74, LocalDate.of(1974, 5, 19)));
		listadoPacientes.add(new Paciente("Claudia Ramírez", "674839", "jor_angulo@yahoo.es", "AB+", 58, LocalDate.of(1986, 12, 1)));
		listadoPacientes.add(new Paciente("Sergio Vallejo", "345678", "jor_angulo@yahoo.es", "O+", 82, LocalDate.of(1991, 11, 7)));
		listadoPacientes.add(new Paciente("Adriana Nieto", "483920", "jor_angulo@yahoo.es", "B+", 64, LocalDate.of(1984, 3, 25)));
		listadoPacientes.add(new Paciente("Gustavo Vargas", "293847", "jor_angulo@yahoo.es", "A+", 79, LocalDate.of(1980, 9, 12)));
		listadoPacientes.add(new Paciente("Alejandro Gómez", "123321", "jor_angulo@yahoo.es", "O+", 80, LocalDate.of(1992, 8, 15)));
		listadoPacientes.add(new Paciente("Beatriz Medina", "456654", "jor_angulo@yahoo.es", "A-", 72, LocalDate.of(1985, 3, 30)));
		listadoPacientes.add(new Paciente("César Gutiérrez", "789987", "jor_angulo@yahoo.es", "B+", 88, LocalDate.of(1979, 6, 10)));
		listadoPacientes.add(new Paciente("Diana Prieto", "101010", "jor_angulo@yahoo.es", "AB+", 63, LocalDate.of(1991, 12, 24)));
		listadoPacientes.add(new Paciente("Eduardo López", "121212", "jor_angulo@yahoo.es", "O-", 70, LocalDate.of(1983, 11, 5)));
		listadoPacientes.add(new Paciente("Florencia Márquez", "343434", "jor_angulo@yahoo.es", "A+", 68, LocalDate.of(1989, 1, 18)));
		listadoPacientes.add(new Paciente("Guillermo Castro", "565656", "jor_angulo@yahoo.es", "AB-", 75, LocalDate.of(1977, 9, 22)));
		listadoPacientes.add(new Paciente("Hilda Pérez", "787878", "jor_angulo@yahoo.es", "B-", 61, LocalDate.of(1988, 7, 14)));
		listadoPacientes.add(new Paciente("Iván Cárdenas", "909090", "jor_angulo@yahoo.es", "A+", 84, LocalDate.of(1982, 4, 19)));
		listadoPacientes.add(new Paciente("Julia Salas", "111111", "jor_angulo@yahoo.es", "O+", 66, LocalDate.of(1990, 5, 27)));
		
		dao.setListaPaciente(listadoPacientes);
		dao.writeSerializable();
	}
	
	public DefaultTableModel cargarReportePacientes(ArrayList<PacienteDTO> lista) {
		
		String nombre = "";
		String identificacion = "";
		String tipoSangre = "";
		String email = "";
		int peso = 0;
		LocalDate fechaNacimiento;
				
		String[] columnNames = {"IDENTIFICACION","NOMBRE","FECHA DE NACIMIENTO", "TIPO SANGUINEO","PESO", "EMAIL"}; //Nombre de la cabecera del reporte
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (PacienteDTO model : lista) {
			identificacion = model.getIdentificacion();
			nombre = model.getNombre();
			fechaNacimiento = model.getFechaNacimiento();
			tipoSangre = model.getTipoSangre();
			email = model.getEmail();
			peso = model.getPeso();
			
			Object[] data = {identificacion, nombre, fechaNacimiento, tipoSangre, peso, email};
			tableModel.addRow(data);
		};
		
		return tableModel;
	}
	
	//crear un nuevo paciente
	public boolean crearPaciente(String identificacion, String nombre, 
			String email, String tipoSanguineo, String fechaNacimiento, String peso) {
		
		if (!esFechaValida(fechaNacimiento)) {
			showMessageDialog(null, "Ingrese una fecha válida en formato yyyy-MM-dd");
			return false;
		}
		
		if (!esNumeroValido(peso)) {
			showMessageDialog(null, "El peso deber ser un número!");
			return false;
		}
		
		if (!esEmailValido(email)) {
			showMessageDialog(null, "El email no es válido!");
			return false;
		}
		
		PacienteDAO daoPaciente = new PacienteDAO(); 
		PacienteDTO pacienteDTO = new PacienteDTO();
		
		int pesoNumerico = Integer.parseInt(peso);
		
		String formato = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        
		LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
		
		Paciente paciente = new Paciente(nombre, identificacion, email, tipoSanguineo, pesoNumerico, fechaNac); 
		
		pacienteDTO = DataMapperPaciente.PacienteToPacienteDTO(paciente);
		
		return daoPaciente.add(pacienteDTO);
	}
	
	private boolean esFechaValida(String fecha) {
		String formato = "yyyy-MM-dd";
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        try {
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
	private boolean esNumeroValido(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	private boolean esEmailValido(String email) {
        String regex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	
	
	
}
