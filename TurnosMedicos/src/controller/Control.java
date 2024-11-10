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
import view.VentanaCrearExamen;
import view.VentanaCrearPaciente;
import view.VentanaCrearSeguimiento;
import view.VentanaLogin;
import view.VentanaCancelarCita;
import view.VentanaPrincipal;
import view.VentanaTurnos;
import static java.time.temporal.ChronoUnit.DAYS;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Clase Control que gestiona la lógica de negocio entre las vistas y los
 * modelos de datos en la aplicación clínica. Se encarga de manejar los eventos
 * de usuario, actualizar vistas y gestionar operaciones como asignación de
 * turnos, citas, tratamientos y exámenes médicos.
 */
public class Control implements ActionListener {

	ArrayList<Profesional> listaDeDoctores;
	ArrayList<TurnoDTO> listaDeTurnos;
	ArrayList<CitaDTO> listaCitas;
	ArrayList<TratamientoMedicoDTO> listaTratamientos;
	ArrayList<ExamenMedicoDTO> listaExamenes;

	VentanaLogin ventanaLogin;
	VentanaPrincipal ventanaPrincipal;
	VentanaTurnos ventanaTurnos;
	VentanaAsignarTurnos ventanaAsignarTurnos;
	VentanaCita ventanaCita;
	VentanaCancelarCita ventanaCCita;
	VentanaBuscarPaciente ventanaBuscarPaciente;
	VentanaCreacion ventanaCreacion;
	VentanaCrearSeguimiento ventanaTratamiento;
	VentanaCrearExamen ventanaExamen;
	VentanaCrearPaciente ventanaCrearPac;

	ArrayList<PacienteDTO> listaPaciente; // luego borrar
	Reporte reporteTurnos;
	CitaDAO citaDao;
	Profesional userActivo;
	AdminClinica administrador;
	AdminCita adminCita;
	AdminProfesional adminProfesional;
	AdminPacientes adminP;
	AdminTratamiento adminTrata;
	AdminExamen adminExamen;
	Paciente pacienteActivo;
	PacienteDAO pacienteDao;
	Timer timer;

	private ModelFacade mf;

	/**
	 * Constructor de la clase Control. Inicializa las variables necesarias para el
	 * funcionamiento del controlador.
	 */
	public Control() {
		reporteTurnos = new Reporte();
		mf = new ModelFacade();
	}

	/**
	 * Método principal para iniciar las ventanas y configuraciones iniciales del
	 * sistema.
	 */
	public void Funcionar() {

		// Email email = new Email("Prueba email Bosque Health", "jor_angulo@yahoo.es",
		// "Este es un mensaje de prueba sin formato");
		// email.EnviarMail();

		ventanaLogin = new VentanaLogin();
		ventanaPrincipal = new VentanaPrincipal();
		ventanaTurnos = new VentanaTurnos();
		ventanaAsignarTurnos = new VentanaAsignarTurnos();
		ventanaCita = new VentanaCita();
		ventanaCCita = new VentanaCancelarCita();
		ventanaBuscarPaciente = new VentanaBuscarPaciente();
		ventanaCreacion = new VentanaCreacion();
		ventanaTratamiento = new VentanaCrearSeguimiento();
		ventanaExamen = new VentanaCrearExamen();
		ventanaCrearPac = new VentanaCrearPaciente();

		ventanaLogin.btnLogin.addActionListener(this);

		ventanaLogin.btnLogin.addActionListener(this);
		ventanaCCita.btnCancelarCita.addActionListener(this);
		ventanaPrincipal.btnAsignarTurnos.addActionListener(this);
		ventanaPrincipal.btnReporteTurnos.addActionListener(this);
		ventanaPrincipal.btnCita.addActionListener(this);
		ventanaPrincipal.btnMostrarCita.addActionListener(this);
		ventanaPrincipal.btnSeguimientos.addActionListener(this);
		ventanaPrincipal.btnCrearPaciente.addActionListener(this);

		ventanaAsignarTurnos.btnGenerarTurnos.addActionListener(this);
		ventanaAsignarTurnos.cboxPeriodo.addActionListener(this);

		ventanaCita.btnCrearCita.addActionListener(this);

		ventanaBuscarPaciente.btnBuscar.addActionListener(this);
		ventanaBuscarPaciente.btnSeleccionar.addActionListener(this);

		ventanaCreacion.btnBuscarPaciente.addActionListener(this);
		ventanaCreacion.btnNuevoSeguimiento.addActionListener(this);
		ventanaCreacion.btnSolicitarExamenes.addActionListener(this);

		ventanaTratamiento.btnCrearSegumiento.addActionListener(this);

		ventanaExamen.btnCrearExamen.addActionListener(this);

		ventanaCrearPac.btnCrearPaciente.addActionListener(this);

		adminProfesional = new AdminProfesional();

		administrador = new AdminClinica(null);
		administrador.cargarEspecialidades();

		adminCita = new AdminCita();
		adminP = new AdminPacientes();
		adminTrata = new AdminTratamiento();
		adminExamen = new AdminExamen();

		adminExamen.cargarTipoExamen();
		adminProfesional.CargarEspecialistas(administrador.getListaEspecialidades());
		listaDeDoctores = adminProfesional.getListaProfesionales();

		ventanaLogin.setVisible(true);

		timer = new Timer();

		// startTimer();
		// enviarEmailsRecordarCitas();
	}

	/**
	 * Inicia el temporizador para tareas automáticas, como envío de correos
	 * electrónicos.
	 */
	private void startTimer() {
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

	/**
	 * Muestra la ventana para asignar turnos.
	 */
	private void mostrarVentanaAsignarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getItemCount() == 0) {
			Periodo[] periodos = administrador.consultarPeriodos();
			for (int i = 0; i < periodos.length; i++) {
				ventanaAsignarTurnos.cboxPeriodo.addItem(periodos[i]);
			}
		}

		ventanaAsignarTurnos.setVisible(true);
	}

	/*
	 * Muestra la ventana de reporte de los turnos
	 */
	private void mostrarVentanaReporeteTurnos() {

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);

		ventanaTurnos.tableTurnos.setModel(tableModel);
		ventanaTurnos.setVisible(true);
	}

	/**
	 * Carga los datos de los turnos disponibles.
	 */
	private void cargarTurnos() {
		TurnoDAO adminTurnos = new TurnoDAO();
		listaDeTurnos = adminTurnos.getAllActivos();

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);
		ventanaCita.tableTurnos.setModel(tableModel);
	}

	/**
	 * Genera turnos para un periodo seleccionado.
	 */
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

	/**
	 * Controlador para gestionar eventos de la interfaz gráfica. Maneja las
	 * interacciones del usuario con botones y ventanas, y ejecuta las acciones
	 * correspondientes.
	 */
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
			mostrarVentanaCancelarCita();
		}
		if (ventanaPrincipal.btnSeguimientos == e.getSource()) {
			mostrarVentanaCreacion();
		}
		if (ventanaPrincipal.btnCrearPaciente == e.getSource()) {
			mostrarVentanaCrearPaciente();
		}
		if (ventanaLogin.btnLogin == e.getSource()) {
			loginUsuario();
		}
		if (ventanaCCita.btnCancelarCita == e.getSource()) {
			cancelarCita();
		}
		if (ventanaCreacion.btnBuscarPaciente == e.getSource()) {
			mostrarVentanaBuscarPaciente();
		}
		if (ventanaBuscarPaciente.btnBuscar == e.getSource()) {

		}
		if (ventanaBuscarPaciente.btnSeleccionar == e.getSource()) {
			seleccionarPacienteActivo();
		}
		if (ventanaCreacion.btnNuevoSeguimiento == e.getSource()) {
			mostrarVentanaCrearTratamiento();
		}
		if (ventanaTratamiento.btnCrearSegumiento == e.getSource()) {
			crearTratamiento();
		}
		if (ventanaCreacion.btnSolicitarExamenes == e.getSource()) {
			mostrarVentanaCrearExamen();
		}
		if (ventanaExamen.btnCrearExamen == e.getSource()) {
			crearExamen();
		}
		if (ventanaCrearPac.btnCrearPaciente == e.getSource()) {
			crearPaciente();
		}

	}

	/**
	 * Carga la lista de pacientes desde la base de datos o los carga con datos demo
	 * si está vacía.
	 */
	private void cargarPacientes() {
		listaPaciente = adminP.listarPacientes();

		if (listaPaciente.size() == 0) {
			adminP.CargarPacientesDemo();
			listaPaciente = DataMapperPaciente.listaPacienteToListaPacienteDTO(adminP.getListadoPacientes());
		}
	}

	/**
	 * Muestra la ventana para crear citas médicas, cargando la lista de pacientes y
	 * especialidades.
	 */
	private void mostrarVentanaCitas() {
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

	/**
	 * Crea una nueva cita médica. Se valida si se ha seleccionado un paciente y un
	 * turno disponible.
	 */
	private void crearCita() {

		String resultado = "";

		if (ventanaCita.tableTurnos.getSelectedRow() == -1) {
			resultado = "Debe seleccionar una cita disponible!";
		}

		if (ventanaCita.listaPacientes.getSelectedIndex() == -1) {
			resultado = "Debe seleccionar un paciente!";
		}

		if (resultado.isEmpty()) {
			PacienteDTO pacienteSeleccionado = (PacienteDTO) ventanaCita.listaPacientes.getSelectedValue();
			String id = ventanaCita.tableTurnos.getValueAt(ventanaCita.tableTurnos.getSelectedRow(), 0).toString();

			Paciente pacienteCita = DataMapperPaciente.PacienteDTOToPaciente(pacienteSeleccionado);

			if (agendarCita(id, pacienteCita)) {
				resultado = "Cita creada exitosamente!";
				cargarTurnos();
			}
		}
		showMessageDialog(null, resultado);
	}

	/**
	 * Crea una nueva cita médica. Se valida si se ha seleccionado un paciente y un
	 * turno disponible.
	 */
	private boolean agendarCita(String idTurno, Paciente pac) {
		try {
			adminCita.crearCita(idTurno, pac);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * Muestra la ventana para cancelar citas.
	 */
	private void mostrarVentanaCancelarCita() {
		refrescarCitas();
		ventanaCCita.setVisible(true);
	}

	/**
	 * Refresca la lista de citas disponibles y actualiza la tabla con los datos más
	 * recientes.
	 */
	private void refrescarCitas() {
		listaCitas = adminCita.listarCitas();
		DefaultTableModel tableModelCita = reporteTurnos.GenerarReporteCitas(listaCitas);
		ventanaCCita.tableMostrarCita.setModel(tableModelCita);
	}

	/**
	 * Refresca la lista de tratamientos asociados al paciente activo y actualiza la
	 * tabla con los datos más recientes.
	 */
	private void refrescarTratamientos() {
		listaTratamientos = adminTrata.listarTratamientos(pacienteActivo);
		DefaultTableModel tableModelTratamiento = adminTrata.cargarReporteTratamientos(listaTratamientos);
		ventanaCreacion.tableSeguimientos.setModel(tableModelTratamiento);
	}

	/**
	 * Refresca la lista de exámenes solicitados para el paciente activo y actualiza
	 * la tabla con los datos más recientes.
	 */
	private void refrescarExamenes() {
		listaExamenes = adminExamen.listarExamenesPaciente(pacienteActivo);
		DefaultTableModel tableModelExamen = adminExamen.cargarReporteExamenes(listaExamenes);
		ventanaCreacion.tableExamenes.setModel(tableModelExamen);
	}

	/**
	 * Refresca la lista de pacientes y actualiza la tabla con los datos más
	 * recientes.
	 */
	private void refrescarPacientes() {
		listaPaciente = adminP.listarPacientes();
	}

	/*
	 * Sirve para ingresar como usuario
	 */
	private void loginUsuario() {
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

	/**
	 * Muestra u oculta los controles específicos para un director en la ventana
	 * principal.
	 */
	private void mostrarControlesDirector(boolean esDirector) {
		ventanaPrincipal.btnAsignarTurnos.setVisible(esDirector);
	}

	/**
	 * Carga los datos de la clínica, incluidos los pacientes y los turnos
	 * disponibles.
	 */
	private void cargarDatosClinica() {
		administrador.setUserActivo(userActivo);
		cargarPacientes();
		cargarTurnos();
	}
	/**
	 * Envia recordatorios de citas a los pacientes por correo electrónico.
	 */
	private void enviarEmailsRecordarCitas() {
		adminCita.enviarEmailsRecordarCitas();
	}
	/**
	 * Cancela una cita seleccionada de la lista de citas.
	 */
	private void cancelarCita() {

		String mensaje = "";

		if (ventanaCCita.tableMostrarCita.getSelectedRow() == -1) {
			mensaje = "Debe seleccionar una cita disponible!";
		} else {
			String idCitaCancelar = ventanaCCita.tableMostrarCita
					.getValueAt(ventanaCCita.tableMostrarCita.getSelectedRow(), 0).toString();

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
		ventanaCCita.setVisible(true);
	}
	/**
	 * Muestra la ventana de creación de seguimiento para el paciente activo.
	 */
	private void mostrarVentanaCreacion() {
		cargarDatosVentanaSeguimientos();
		ventanaCreacion.setVisible(true);

	}
	/**
	 * Muestra la ventana de búsqueda de pacientes.
	 */
	private void mostrarVentanaBuscarPaciente() {
		ventanaBuscarPaciente.setVisible(true);

		ventanaBuscarPaciente.tablePaciente.setModel(adminP.cargarReportePacientes(listaPaciente));
	}
	/**
	 * Selecciona un paciente de la lista de búsqueda y carga su información en la
	 * ventana de seguimiento.
	 */
	private void seleccionarPacienteActivo() {
		if (ventanaBuscarPaciente.tablePaciente.getSelectedRow() == -1) {
			showMessageDialog(null, "Seleccione un paciente!");
			return;
		}
		String identificacion = ventanaBuscarPaciente.tablePaciente
				.getValueAt(ventanaBuscarPaciente.tablePaciente.getSelectedRow(), 0).toString();
		Paciente pacienteBuscar = new Paciente();
		pacienteBuscar.setId(identificacion);

		pacienteDao = new PacienteDAO();
		pacienteActivo = pacienteDao.find(pacienteBuscar);

		cargarDatosVentanaSeguimientos();
		ventanaBuscarPaciente.setVisible(false);
	}
	/**
	 * Carga los datos del paciente activo en la ventana de seguimiento. Muestra el
	 * nombre del paciente y actualiza las listas de tratamientos y exámenes.
	 */
	private void cargarDatosVentanaSeguimientos() {
		if (pacienteActivo != null) {
			ventanaCreacion.lblPaciente.setText(pacienteActivo.getNombre());
			refrescarTratamientos();
			refrescarExamenes();
		}

	}
	/**
	 * Muestra la ventana para crear un nuevo tratamiento para el paciente activo.
	 * Si no hay un paciente seleccionado, muestra un mensaje de advertencia.
	 */
	private void mostrarVentanaCrearTratamiento() {
		if (pacienteActivo == null) {
			showMessageDialog(null, "Seleccione primero un paciente!");
			return;
		}
		ventanaTratamiento.setVisible(true);
		ventanaTratamiento.lblIdMedico.setText("Profesional: " + userActivo.getNombre());
		ventanaTratamiento.lblIdPaciente.setText("Paciente: " + pacienteActivo.getNombre());
		ventanaTratamiento.txtSeguimiento.setText("");
	}
	/**
	 * Muestra la ventana para crear un nuevo examen para el paciente activo. Si no
	 * hay un paciente seleccionado, muestra un mensaje de advertencia.
	 */
	private void mostrarVentanaCrearExamen() {
		if (pacienteActivo == null) {
			showMessageDialog(null, "Seleccione primero un paciente!");
			return;
		}
		ventanaExamen.lblIdMedico.setText("Profesional: " + userActivo.getNombre());
		ventanaExamen.lblIdPaciente.setText("Paciente: " + pacienteActivo.getNombre());
		ventanaExamen.txtSeguimiento.setText("");

		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel<TipoExamen>(
				adminExamen.getListaTipoExamen().toArray(new TipoExamen[0]));

		ventanaExamen.cboTipoExamen.setModel(modeloCombo);
		ventanaExamen.setVisible(true);

	}

	private void mostrarVentanaCrearPaciente() {
		ventanaCrearPac.setVisible(true);
	}
	/**
	 * Crea un tratamiento para el paciente activo, validando que la descripción no
	 * esté vacía. Si el tratamiento se registra correctamente, actualiza la lista
	 * de tratamientos.
	 */
	private void crearTratamiento() {
		if (ventanaTratamiento.txtSeguimiento.getText().equals("")) {
			showMessageDialog(null, "Ingrese una descripción!");
			return;
		}

		if (adminTrata.crearTratamiento(ventanaTratamiento.txtSeguimiento.getText(), pacienteActivo, userActivo)) {
			showMessageDialog(null, "Tratamiento registrado");
			refrescarTratamientos();
			ventanaTratamiento.setVisible(false);
		}
	}
	/**
	 * Crea un examen para el paciente activo, validando que la descripción y el
	 * tipo de examen estén completos. Si el examen se registra correctamente,
	 * actualiza la lista de exámenes.
	 */
	private void crearExamen() {
		if (ventanaExamen.txtSeguimiento.getText().equals("")) {
			showMessageDialog(null, "Ingrese una descripción!");
			return;
		}
		if (ventanaExamen.cboTipoExamen.getSelectedIndex() == -1) {
			showMessageDialog(null, "Seleccione un tipo de examen!");
			return;
		}

		TipoExamen tipoEx = (TipoExamen) ventanaExamen.cboTipoExamen.getSelectedItem();

		if (adminExamen.crearExamen(tipoEx, userActivo, pacienteActivo, ventanaExamen.txtSeguimiento.getText())) {
			showMessageDialog(null, "Examen registrado");
			refrescarExamenes();
			ventanaExamen.setVisible(false);
		}

	}

	private void crearPaciente() {
		if (ventanaCrearPac.txtIdentificacion.getText().equals("")) {
			showMessageDialog(null, "Ingrese una Identificación!");
			return;
		}
		if (ventanaCrearPac.txtNombre.getText().equals("")) {
			showMessageDialog(null, "Ingrese un nombre!");
			return;
		}
		if (ventanaCrearPac.txtEmail.getText().equals("")) {
			showMessageDialog(null, "Ingrese un email!");
			return;
		}
		if (ventanaCrearPac.txtFechaNacimiento.getText().equals("")) {
			showMessageDialog(null, "Ingrese una fecha de nacimiento!");
			return;
		}
		if (ventanaCrearPac.txtPeso.getText().equals("")) {
			showMessageDialog(null, "Ingrese una peso!");
			return;
		}
		if (ventanaCrearPac.txtTipoSangre.getText().equals("")) {
			showMessageDialog(null, "Ingrese una tipo de sangre");
			return;
		}

		String identificacion = ventanaCrearPac.txtIdentificacion.getText();
		String nombre = ventanaCrearPac.txtNombre.getText();
		String email = ventanaCrearPac.txtEmail.getText();
		String fechaN = ventanaCrearPac.txtFechaNacimiento.getText();
		String peso = ventanaCrearPac.txtPeso.getText();
		String tipoS = ventanaCrearPac.txtTipoSangre.getText();

		if (adminP.crearPaciente(identificacion, nombre, email, tipoS, fechaN, peso)) {
			showMessageDialog(null, "Paciente registrado");
			refrescarPacientes();
			ventanaCrearPac.txtEmail.setText("");
			ventanaCrearPac.txtIdentificacion.setText("");
			ventanaCrearPac.txtNombre.setText("");
			ventanaCrearPac.txtTipoSangre.setText("");
			ventanaCrearPac.txtFechaNacimiento.setText("");
			ventanaCrearPac.txtPeso.setText("");
			ventanaCrearPac.setVisible(false);
		}

	}

}
