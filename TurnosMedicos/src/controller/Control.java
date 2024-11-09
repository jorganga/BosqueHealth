package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.mail.MessageAware;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import model.*;
import model.persistence.CitaDAO;
import model.persistence.DataMapperPaciente;
import model.persistence.PacienteDAO;
import model.persistence.TurnoDAO;
import view.VentanaAsignarTurnos;
import view.VentanaBuscarPaciente;
import view.VentanaCita;
import view.VentanaCreacion;
import view.VentanaCrearSeguimiento;
import view.VentanaLogin;
import view.VentanaMostrarCita;
import view.VentanaPrincipal;
import view.VentanaTurnos;
import static java.time.temporal.ChronoUnit.DAYS;
import static javax.swing.JOptionPane.showMessageDialog;

public class Control implements ActionListener {

	ArrayList<Profesional> listaDeDoctores;
	ArrayList<TurnoDTO> listaDeTurnos;
	ArrayList<CitaDTO> listaCitas;
	VentanaLogin ventanaLogin;
	VentanaPrincipal ventanaPrincipal;
	VentanaTurnos ventanaTurnos;
	VentanaAsignarTurnos ventanaAsignarTurnos;
	VentanaCita ventanaCita;
	VentanaMostrarCita ventanaMCita;
	VentanaBuscarPaciente ventanaBuscarPaciente;
	VentanaCreacion ventanaCreacion;
	VentanaCrearSeguimiento ventanaTratamiento;
	
	ArrayList<PacienteDTO> listaPaciente; // luego borrar
	Reporte reporteTurnos;
	CitaDAO citaDao;
	Profesional userActivo;
	AdminClinica administrador;
	AdminCita adminCita;
	AdminProfesional adminProfesional;
	AdminPacientes adminP;
	Paciente pacienteActivo;
	PacienteDAO pacienteDao;
	Timer timer;

	private ModelFacade mf;

	public Control() {
		reporteTurnos = new Reporte();
		mf = new ModelFacade();
	}

	public void Funcionar() {

		// Email email = new Email("Prueba email Bosque Health", "jor_angulo@yahoo.es",
		// "Este es un mensaje de prueba sin formato");
		// email.EnviarMail();

		ventanaLogin = new VentanaLogin();
		ventanaPrincipal = new VentanaPrincipal();
		ventanaTurnos = new VentanaTurnos();
		ventanaAsignarTurnos = new VentanaAsignarTurnos();
		ventanaCita = new VentanaCita();
		ventanaMCita = new VentanaMostrarCita();
		ventanaBuscarPaciente = new VentanaBuscarPaciente();
		ventanaCreacion = new VentanaCreacion();
		ventanaTratamiento = new VentanaCrearSeguimiento();
		
		ventanaLogin.btnLogin.addActionListener(this);

		ventanaLogin.btnLogin.addActionListener(this);
		ventanaMCita.btnCancelarCita.addActionListener(this);
		ventanaPrincipal.btnAsignarTurnos.addActionListener(this);
		ventanaPrincipal.btnReporteTurnos.addActionListener(this);
		ventanaPrincipal.btnCita.addActionListener(this);
		ventanaPrincipal.btnMostrarCita.addActionListener(this);
		ventanaPrincipal.btnSeguimientos.addActionListener(this);

		ventanaAsignarTurnos.btnGenerarTurnos.addActionListener(this);
		ventanaAsignarTurnos.cboxPeriodo.addActionListener(this);

		ventanaCita.btnCrearCita.addActionListener(this);
		
		ventanaBuscarPaciente.btnBuscar.addActionListener(this);
		ventanaBuscarPaciente.btnSeleccionar.addActionListener(this);
		
		ventanaCreacion.btnBuscarPaciente.addActionListener(this);
		ventanaCreacion.btnNuevoSeguimiento.addActionListener(this);
		ventanaCreacion.btnSolicitarExamenes.addActionListener(this);
		
		ventanaTratamiento.btnCrearSegumiento.addActionListener(this);

		adminProfesional = new AdminProfesional();

		administrador = new AdminClinica(null);
		administrador.cargarEspecialidades();

		adminCita = new AdminCita();
		adminP = new AdminPacientes();

		adminProfesional.CargarEspecialistas(administrador.getListaEspecialidades());
		listaDeDoctores = adminProfesional.getListaProfesionales();

		ventanaLogin.setVisible(true);

		timer = new Timer();

		// startTimer();
		// enviarEmailsRecordarCitas();
	}

	public void startTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Tarea ejecutada a: " + System.currentTimeMillis());
				enviarEmailsRecordarCitas();
			}
		};

		// Programar la tarea para que se ejecute cada 60,000 ms (1 minuto)
		timer.scheduleAtFixedRate(task, 0, 10000);
	}

	private void mostrarVentanaAsignarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getItemCount() == 0) {
			Periodo[] periodos = administrador.consultarPeriodos();
			for (int i = 0; i < periodos.length; i++) {
				ventanaAsignarTurnos.cboxPeriodo.addItem(periodos[i]);
			}
		}

		ventanaAsignarTurnos.setVisible(true);
	}

	private void mostrarVentanaReporeteTurnos() {

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);

		ventanaTurnos.tableTurnos.setModel(tableModel);
		ventanaTurnos.setVisible(true);
	}

	private void cargarTurnos() {
		TurnoDAO adminTurnos = new TurnoDAO();
		listaDeTurnos = adminTurnos.getAllActivos();

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);
		ventanaCita.tableTurnos.setModel(tableModel);
	}

	private void generarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getSelectedIndex() < 0) {
			showMessageDialog(null, "Debe seleccionar un periodo");
			return;
		}

		Periodo periodosTurno = (Periodo) ventanaAsignarTurnos.cboxPeriodo.getSelectedItem();
		LocalDate fecha = LocalDate.of(periodosTurno.getYear(), periodosTurno.getMes(), 1);

		String resultado = administrador.generarTurnos(fecha, adminProfesional.getListaProfesionales());

		showMessageDialog(null, resultado);

		cargarTurnos();

		ventanaAsignarTurnos.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventanaPrincipal.btnAsignarTurnos == e.getSource()) {
			mostrarVentanaAsignarTurnos();
		}

		if (ventanaAsignarTurnos.btnGenerarTurnos == e.getSource()) {
			generarTurnos();
		}

		if (ventanaPrincipal.btnReporteTurnos == e.getSource()) {
			mostrarVentanaReporeteTurnos();
		}

		if (ventanaPrincipal.btnCita == e.getSource()) {
			mostrarVentanaCitas();
		}

		if (ventanaCita.btnCrearCita == e.getSource()) {
			crearCita();
		}
		if (ventanaPrincipal.btnMostrarCita == e.getSource()) {
			mostrarVentanaMostrarCita();
		}
		if (ventanaPrincipal.btnSeguimientos == e.getSource()) {
			mostrarVentanaCreacion();
		}
		if (ventanaLogin.btnLogin == e.getSource()) {
			loginUsuario();
		}
		if (ventanaMCita.btnCancelarCita == e.getSource()) {
			cancelarCita();
		}
		if(ventanaCreacion.btnBuscarPaciente == e.getSource()) {
			mostrarVentanaBuscarPaciente();
		}
		if(ventanaBuscarPaciente.btnBuscar == e.getSource()) {
			
		}
		if(ventanaBuscarPaciente.btnSeleccionar == e.getSource()) {
			seleccionarPacienteActivo();
		}
		if(ventanaCreacion.btnNuevoSeguimiento == e.getSource()) {
			mostrarVentanaCrearTratamiento();
		}
		if(ventanaTratamiento.btnCrearSegumiento == e.getSource()) {
			crearTratamiento();
		}
		
	}

	public void cargarPacientes() {
		listaPaciente = adminP.listarPacientes();
		
		if (listaPaciente.size() == 0)
		{
			adminP.CargarPacientesDemo();
			listaPaciente = DataMapperPaciente.listaPacienteToListaPacienteDTO(adminP.getListadoPacientes());
		}
	}

	public void mostrarVentanaCitas() {
		DefaultListModel<PacienteDTO> modelo = new DefaultListModel<>();

		// Poblar el modelo con el ArrayList
		for (PacienteDTO elemento : listaPaciente) {
			modelo.addElement(elemento);
		}

		ventanaCita.listaPacientes.setModel(modelo);

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel<Especialidad>(
				administrador.getListaEspecialidades().toArray(new Especialidad[0]));

		ventanaCita.cboxEspecialidad.setModel(modeloCombo);

		ventanaCita.setVisible(true);
	}

	private void crearCita() {

		String resultado = "";

		if (ventanaCita.tableTurnos.getSelectedRow() == -1) {
			resultado = "Debe seleccionar una cita disponible!";
		}

		if (ventanaCita.listaPacientes.getSelectedIndex() == -1) {
			resultado = "Debe seleccionar un paciente!";
		}

		if (resultado.isEmpty()) {
			PacienteDTO pacienteSeleccionado = (PacienteDTO)ventanaCita.listaPacientes.getSelectedValue();
			String id = ventanaCita.tableTurnos.getValueAt(ventanaCita.tableTurnos.getSelectedRow(), 0).toString();
			
			Paciente pacienteCita = DataMapperPaciente.PacienteDTOToPaciente(pacienteSeleccionado);
			
			if (agendarCita(id, pacienteCita)) {
				resultado = "Cita creada exitosamente!";
				cargarTurnos();
			}
		}
		showMessageDialog(null, resultado);
	}

	private boolean agendarCita(String idTurno, Paciente pac) {
		try {
			adminCita.crearCita(idTurno, pac);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private void mostrarVentanaMostrarCita() {
		refrescarCitas();
		ventanaMCita.setVisible(true);
	}

	private void refrescarCitas() {
		listaCitas = adminCita.listarCitas();
		DefaultTableModel tableModelCita = reporteTurnos.GenerarReporteCitas(listaCitas);
		ventanaMCita.tableMostrarCita.setModel(tableModelCita);
	}

	
	private void loginUsuario()
	{
		String pwd = new String(ventanaLogin.pwd.getPassword());
		
		if (ventanaLogin.txtUsuario.getText().equals("")) {
			showMessageDialog(null, "Ingrese el nombre de usuario!");
			return;
		}
		if (pwd.equals("")) {
			showMessageDialog(null, "Ingrese la contraseña!");
			return;
		}
		if (adminProfesional.login(ventanaLogin.txtUsuario.getText(), pwd)) {
			ventanaPrincipal.setVisible(true);
			ventanaLogin.setVisible(false);
			userActivo = adminProfesional.getUsuarioLogeado();
			ventanaPrincipal.lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 30));
			ventanaPrincipal.lblBienvenido.setText("BIENVENIDO A SOFTHEALTH " + userActivo.getNombre() + "!");
			mostrarControlesDirector(userActivo.isEsDirector());

			cargarDatosClinica();

		} else {
			showMessageDialog(null, "Acceso Denegado! Revise los datos ingresados");
			return;
		}
	}

	private void mostrarControlesDirector(boolean esDirector) {
		ventanaPrincipal.btnAsignarTurnos.setVisible(esDirector);
	}

	private void cargarDatosClinica() {
		administrador.setUserActivo(userActivo);
		cargarPacientes();
		cargarTurnos();
	}

	private void enviarEmailsRecordarCitas() {
		adminCita.enviarEmailsRecordarCitas();
	}

	private void cancelarCita() {

		String mensaje = "";

		if (ventanaMCita.tableMostrarCita.getSelectedRow() == -1) {
			mensaje = "Debe seleccionar una cita disponible!";
		} else {
			String idCitaCancelar = ventanaMCita.tableMostrarCita
					.getValueAt(ventanaMCita.tableMostrarCita.getSelectedRow(), 0).toString();

			CitaDTO citaCancelar = new CitaDTO();
			citaCancelar.setId(idCitaCancelar);
			citaCancelar.setEstado("cancelada");
			if (adminCita.cancelarCita(citaCancelar)) {
				mensaje = "Su cita se ha cancelado";
			} else {
				mensaje = "Error al cancelar la cita";
			}
		}
		showMessageDialog(null, mensaje);
		refrescarCitas();

	}
	private void mostrarVentanaCreacion() {
		cargarDatosVentanaSeguimientos();
		ventanaCreacion.setVisible(true);
	}
	
	private void mostrarVentanaBuscarPaciente() {
		ventanaBuscarPaciente.setVisible(true);
		
		ventanaBuscarPaciente.tablePaciente.setModel(adminP.cargarReportePacientes(listaPaciente)); 
	}
	
	private void seleccionarPacienteActivo() {
		if(ventanaBuscarPaciente.tablePaciente.getSelectedRow() == -1) {
			showMessageDialog(null, "Seleccione un paciente!");
			return;
		}
		String identificacion = ventanaBuscarPaciente.tablePaciente.
				getValueAt(ventanaBuscarPaciente.tablePaciente.getSelectedRow(), 0).toString();
		Paciente pacienteBuscar = new Paciente();
		pacienteBuscar.setId(identificacion);
		
		pacienteDao = new PacienteDAO();
		pacienteActivo = pacienteDao.find(pacienteBuscar);
		
		cargarDatosVentanaSeguimientos();
		ventanaBuscarPaciente.setVisible(false);
	}
	
	private void cargarDatosVentanaSeguimientos() {
		if (pacienteActivo != null)
		{
			ventanaCreacion.lblPaciente.setText(pacienteActivo.getNombre());
		}
		
	}
	
	private void mostrarVentanaCrearTratamiento() {
		if (pacienteActivo == null) {
			showMessageDialog(null, "Seleccione primero un paciente!");
			return;
		}
		ventanaTratamiento.setVisible(true);
		ventanaTratamiento.lblIdMedico.setText("Profesional: " + userActivo.getNombre());
		ventanaTratamiento.lblIdPaciente.setText("Paciente: " + pacienteActivo.getNombre());
	}
	
	private void crearTratamiento() {
		if (ventanaTratamiento.textTratamiento.getText().equals("")) {
			showMessageDialog(null, "Ingrese una descripción!");
			return;
		}
		
		if (adminP.crearTratamiento(ventanaTratamiento.textTratamiento.getText(), pacienteActivo, userActivo)) {
			showMessageDialog(null, "Tratamiento registrado");
			ventanaTratamiento.setVisible(false);
		}
	}

}
