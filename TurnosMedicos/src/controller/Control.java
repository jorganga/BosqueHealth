package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import model.*;
import model.persistence.CitaDAO;
import model.persistence.TurnoDAO;
import view.VentanaAsignarTurnos;
import view.VentanaCita;
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
	ArrayList<Paciente> listaPaciente; // luego borrar
	Reporte reporteTurnos;
	CitaDAO citaDao;
	Profesional userActivo;

	AdminClinica administrador;
	AdminProfesional adminProfesional;
	private ModelFacade mf;

	public Control() {
		reporteTurnos = new Reporte();
		mf = new ModelFacade();
	}

	public void Funcionar() {

		/*
		 * EmailSender email = new EmailSender();
		 * email.EnviarMailGmail("jor_angulo@yahoo.es", "test mail java",
		 * "Prueba de mensaje con java email Nico");
		 */
		ventanaLogin = new VentanaLogin(); 
		ventanaPrincipal = new VentanaPrincipal();
		ventanaTurnos = new VentanaTurnos();
		ventanaAsignarTurnos = new VentanaAsignarTurnos();
		ventanaCita = new VentanaCita();
		ventanaMCita = new VentanaMostrarCita();
		
		ventanaLogin.btnLogin.addActionListener(this);

		ventanaPrincipal.btnAsignarTurnos.addActionListener(this);
		ventanaPrincipal.btnReporteTurnos.addActionListener(this);
		ventanaPrincipal.btnCita.addActionListener(this);
		ventanaPrincipal.btnMostrarCita.addActionListener(this);

		ventanaAsignarTurnos.btnGenerarTurnos.addActionListener(this);
		ventanaAsignarTurnos.cboxPeriodo.addActionListener(this);

		ventanaCita.btnCrearCita.addActionListener(this);

		administrador = new AdminClinica();
		adminProfesional = new AdminProfesional();

		administrador.cargarEspecialidades();

		adminProfesional.CargarEspecialistas(administrador.getListaEspecialidades());
		listaDeDoctores = adminProfesional.getListaProfesionales();
		
		ventanaLogin.setVisible(true);

		cargarPacientes();
		cargarTurnos();
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
		if (ventanaLogin.btnLogin == e.getSource()) {
			loginUsuario();
		}
	}

	public void cargarPacientes() {
		AdminPacientes adminP = new AdminPacientes();
		adminP.CargarPacientesDemo();
		Date fecha = new Date();
		listaPaciente = adminP.getListadoPacientes();
	}

	public void mostrarVentanaCitas() {
		DefaultListModel<Paciente> modelo = new DefaultListModel<>();

		// Poblar el modelo con el ArrayList
		for (Paciente elemento : listaPaciente) {
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
			Paciente pacienteSeleccionado = (Paciente) ventanaCita.listaPacientes.getSelectedValue();
			String id = ventanaCita.tableTurnos.getValueAt(ventanaCita.tableTurnos.getSelectedRow(), 0).toString();

			if (agendarCita(id, pacienteSeleccionado)) {
				resultado = "Cita creada exitosamente!";
				cargarTurnos();
			}
		}
		showMessageDialog(null, resultado);
	}

	private boolean agendarCita(String idTurno, Paciente pac) {
		AdminCita admiCita = new AdminCita();
		try {
			admiCita.CrearCita(idTurno, pac);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	private void mostrarVentanaMostrarCita() {
		citaDao = new CitaDAO();
		listaCitas = citaDao.getAll();
		DefaultTableModel tableModelCita = reporteTurnos.GenerarReporteCitas(listaCitas);
		ventanaMCita.tableMostrarCita.setModel(tableModelCita);
		ventanaMCita.setVisible(true);
	}
	
	private void loginUsuario()
	{
		String pwd = new String(ventanaLogin.passwordField.getPassword());
		
		if (ventanaLogin.txtUsuario.getText().equals("")) {
			showMessageDialog(null, "Ingrese el nombre de usuario!");
			return;
		}
		if (pwd.equals("")) {
			showMessageDialog(null, "Ingrese el password!");
			return;
		}
		if (adminProfesional.login(ventanaLogin.txtUsuario.getText(), pwd)) {
			ventanaPrincipal.setVisible(true);
			ventanaLogin.setVisible(false);
			userActivo = adminProfesional.getUsuarioLogeado();
			ventanaPrincipal.lblBienvenido.setText("Bienvenido " + userActivo.getNombre());
			mostrarControlesDirector(userActivo.isEsDirector());
		}
		else
		{
			showMessageDialog(null, "Acceso Denegado! Revise los datos ingresados");
			return;
		}
	}
	
	private void mostrarControlesDirector(boolean esDirector) {
		ventanaPrincipal.btnAsignarTurnos.setVisible(esDirector);
	}
	
	
	

}
