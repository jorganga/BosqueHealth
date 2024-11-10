package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

import model.persistence.CitaDAO;
import model.persistence.DataMapperTratamientoMedico;
import model.persistence.TratamientoMedicoDAO;

/**
 * Clase encargada de gestionar los tratamientos médicos, incluyendo su
 * creación, listado y la generación de reportes.
 */
public class AdminTratamiento {
	/**
	 * Crea un tratamiento médico para un paciente específico y un médico
	 * profesional. El tratamiento es guardado en la base de datos y se notifica por
	 * correo electrónico al paciente.
	 */
	public boolean crearTratamiento(String descricion, Paciente miPaciente, Profesional medico) {

		TratamientoMedicoDAO daoTratamiento = new TratamientoMedicoDAO();
		TratamientoMedicoDTO tratamientoDTO = new TratamientoMedicoDTO();

		TratamientoMedico tratamiento = new TratamientoMedico(LocalDate.now(), miPaciente, medico, descricion);

		tratamientoDTO = DataMapperTratamientoMedico.TratamientoMedicoToTratamientoMedicoDTO(tratamiento);

		notificarTratamientoCreado(tratamientoDTO);

		return daoTratamiento.add(tratamientoDTO);

	}

	/**
	 * Lista los tratamientos médicos de un paciente específico. Devuelve todos los
	 * tratamientos asociados al paciente mediante un filtro basado en su
	 * identificación.
	 */
	public ArrayList<TratamientoMedicoDTO> listarTratamientos(Paciente paciente) {
		TratamientoMedicoDAO tratamientoDao = new TratamientoMedicoDAO();
		ArrayList<TratamientoMedicoDTO> listaTotal = tratamientoDao.getAll();
		return listaTotal.stream().filter(
				tratamiento -> tratamiento.getPaciente().getIdentificacion().equals(paciente.getIdentificacion()))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	 /**
     * Genera un reporte de tratamientos médicos en formato de tabla.
     * Este reporte contiene columnas como especialidad, profesional, fecha y descripción del tratamiento.
     */
	public DefaultTableModel cargarReporteTratamientos(ArrayList<TratamientoMedicoDTO> lista) {

		String especialidad = "";
		String profesional = "";
		String descripcion = "";
		LocalDate fecha;

		String[] columnNames = { "ESPECIALIDAD", "PROFESIONAL", "FECHA", "DESCRIPCION" }; // Nombre de la cabecera del
																							// reporte

		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		for (TratamientoMedicoDTO model : lista) {
			especialidad = model.getMedico().getEspecialidad().getNombre();
			profesional = model.getMedico().getNombre();
			fecha = model.getFecha();
			descripcion = model.getDescripcion();

			Object[] data = { especialidad, profesional, fecha, descripcion };
			tableModel.addRow(data);
		}
		;

		return tableModel;
	}/**
     * Envia una notificación por correo electrónico al paciente sobre el tratamiento médico creado.
     * El correo incluye información relevante como especialidad, fecha y descripción del tratamiento.
     */

	private void notificarTratamientoCreado(TratamientoMedicoDTO tratCreado) {
		String mensaje = "Sr(ra) " + tratCreado.getPaciente().getNombre() + "\r\n\n";
		mensaje = mensaje + "Se ha creado un tratamiento médico:\r\n\n";
		mensaje = mensaje + "Especialidad: " + tratCreado.getMedico().getEspecialidad() + "\r\n";
		mensaje = mensaje + "Fecha: " + tratCreado.getFecha() + "\r\n";
		mensaje = mensaje + "Descripción: " + tratCreado.getDescripcion() + "\r\n";
		mensaje = mensaje + "Generado por el especialista: " + tratCreado.getMedico().getNombre() + "\r\n";

		Email email = new Email("Bosque Health - Tratamiento", tratCreado.getPaciente().getEmail(), mensaje);
		email.EnviarMail();
		System.out.println("Email de Tratamiento enviado a: " + email.getDestinatario());
	}

}
