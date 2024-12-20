package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import model.persistence.CitaDAO;
import model.persistence.DataMapperExamenMedico;
import model.persistence.DataMapperTratamientoMedico;
import model.persistence.ExamenMedicoDAO;
import model.persistence.TratamientoMedicoDAO;

/**
 * Clase encargada de administrar los exámenes médicos, incluyendo la creación
 * de nuevos exámenes, la consulta de los exámenes de los pacientes y la
 * generación de reportes.
 */
public class AdminExamen {
	/**
	 * Constructor de la clase. Inicializa la lista de tipos de exámenes.
	 */
	public AdminExamen() {
		listaTipoExamen = new ArrayList<TipoExamen>();
	}

	private ArrayList<TipoExamen> listaTipoExamen;

	/**
	 * Obtiene la lista de tipos de exámenes disponibles.
	 */
	public ArrayList<TipoExamen> getListaTipoExamen() {
		return listaTipoExamen;
	}

	/**
	 * Carga los tipos de exámenes predefinidos en el sistema.
	 */
	public void cargarTipoExamen() {
		listaTipoExamen.add(new TipoExamen("1", "Ácido úrico"));
		listaTipoExamen.add(new TipoExamen("2", "Colesterol"));
		listaTipoExamen.add(new TipoExamen("3", "Creatinina"));
		listaTipoExamen.add(new TipoExamen("4", "Electrocardiograma"));
		listaTipoExamen.add(new TipoExamen("5", "Función hepática"));
		listaTipoExamen.add(new TipoExamen("6", "Función renal"));
		listaTipoExamen.add(new TipoExamen("7", "Glucosa"));
		listaTipoExamen.add(new TipoExamen("8", "Hemoglobina glicosilada"));
		listaTipoExamen.add(new TipoExamen("9", "Hemograma completo"));
		listaTipoExamen.add(new TipoExamen("10", "Perfil lipídico"));
		listaTipoExamen.add(new TipoExamen("11", "Prueba de embarazo"));
		listaTipoExamen.add(new TipoExamen("12", "Prueba de función tiroidea"));
		listaTipoExamen.add(new TipoExamen("13", "Prueba de VIH"));
		listaTipoExamen.add(new TipoExamen("14", "Rayos X de tórax"));
		listaTipoExamen.add(new TipoExamen("15", "Uroanálisis"));
	}

	/**
	 * Crea un nuevo examen médico para un paciente específico, asignado a un
	 * profesional y con una descripción.
	 */
	public boolean crearExamen(TipoExamen tipo, Profesional medico, Paciente miPaciente, String nota) {

		ExamenMedicoDAO daoExamen = new ExamenMedicoDAO();
		ExamenMedicoDTO examenDTO = new ExamenMedicoDTO();
		ExamenMedico examen = new ExamenMedico(tipo, LocalDate.now(), medico, miPaciente, nota);
		examenDTO = DataMapperExamenMedico.ExamenMedicoToExamenMedicoDTO(examen);
		notificarExamenCreado(examenDTO);

		return daoExamen.add(examenDTO);
	}

	/**
	 * Lista los exámenes médicos realizados a un paciente específico.
	 */

	public ArrayList<ExamenMedicoDTO> listarExamenesPaciente(Paciente paciente) {
		ExamenMedicoDAO examenDao = new ExamenMedicoDAO();
		ArrayList<ExamenMedicoDTO> listaTotal = examenDao.getAll();
		return listaTotal.stream()
				.filter(exam -> exam.getPaciente().getIdentificacion().equals(paciente.getIdentificacion()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Carga un reporte en forma de tabla con los exámenes médicos realizados,
	 * mostrando detalles como tipo de examen, especialidad, profesional, fecha y
	 * descripción.
	 */
	public DefaultTableModel cargarReporteExamenes(ArrayList<ExamenMedicoDTO> lista) {
		String especialidad = "";
		String profesional = "";
		String descripcion = "";
		String tipoExamen = "";
		LocalDate fecha;

		String[] columnNames = { "TIPO EXAMEN", "ESPECIALIDAD", "PROFESIONAL", "FECHA", "DESCRIPCION" };

		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		for (ExamenMedicoDTO model : lista) {
			tipoExamen = model.getTipo().getNombre();
			especialidad = model.getMedico().getEspecialidad().getNombre();
			profesional = model.getMedico().getNombre();
			fecha = model.getFecha();
			descripcion = model.getNota();

			Object[] data = { tipoExamen, especialidad, profesional, fecha, descripcion };
			tableModel.addRow(data);
		}
		;

		return tableModel;
	}

	/**
	 * Notifica al paciente la creación de un nuevo examen médico mediante un correo
	 * electrónico.
	 */
	private void notificarExamenCreado(ExamenMedicoDTO examenCreado) {
		String mensaje = "Sr(ra) " + examenCreado.getPaciente().getNombre() + "\r\n\n";
		mensaje = mensaje + "Se ha creado un examen médico:\r\n\n";
		mensaje = mensaje + "Especialidad: " + examenCreado.getMedico().getEspecialidad() + "\r\n";
		mensaje = mensaje + "Fecha: " + examenCreado.getFecha() + "\r\n";
		mensaje = mensaje + "Tipo De Examen: " + examenCreado.getTipo() + "\r\n";
		mensaje = mensaje + "Descripción: " + examenCreado.getNota() + "\r\n";
		mensaje = mensaje + "Generado por el especialista: " + examenCreado.getMedico().getNombre() + "\r\n";

		Email email = new Email("Bosque Health - Examen", examenCreado.getPaciente().getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de Examen enviado a: " + email.getDestinatario());
	}

}
