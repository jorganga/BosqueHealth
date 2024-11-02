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
import model.persistence.TurnoDAO;
import view.VentanaAsignarTurnos;
import view.VentanaCita;
import view.VentanaPrincipal;
import view.VentanaTurnos;

import static java.time.temporal.ChronoUnit.DAYS;
import static javax.swing.JOptionPane.showMessageDialog;

public class Control implements ActionListener {

	ArrayList<Profesional> listaDeDoctores;
	ArrayList<TurnoDTO> listaDeTurnos;
	VentanaPrincipal ventanaPrincipal;
	VentanaTurnos ventanaTurnos;
	VentanaAsignarTurnos ventanaAsignarTurnos;
	VentanaCita ventanaCita;
	ArrayList<Paciente> listaPaciente; // luego borrar
	
	AdminClinica administrador;
	private ModelFacade mf;

	public Control() {
		mf = new ModelFacade();
	}

	public void Funcionar() {

		/*
		 * EmailSender email = new EmailSender();
		 * email.EnviarMailGmail("jor_angulo@yahoo.es", "test mail java",
		 * "Prueba de mensaje con java email Nico");
		 */

		cargarPacientes();
		cargarTurnos();

		ventanaPrincipal = new VentanaPrincipal();
		ventanaTurnos = new VentanaTurnos();
		ventanaAsignarTurnos = new VentanaAsignarTurnos();
		ventanaCita = new VentanaCita();

		ventanaPrincipal.btnAsignarTurnos.addActionListener(this);
		ventanaPrincipal.btnReporteTurnos.addActionListener(this);
		ventanaPrincipal.btnCita.addActionListener(this);
		
		ventanaAsignarTurnos.btnGenerarTurnos.addActionListener(this);
		ventanaAsignarTurnos.cboxPeriodo.addActionListener(this);
		

		administrador = new AdminClinica();

		administrador.CargarEspecialidades();

		administrador.CargarEspecialistas();
		

		ventanaPrincipal.setVisible(true);

		/*
		 * AdminClinica administrador = new AdminClinica();
		 * 
		 * administrador.CargarEspecialidades();
		 * 
		 * administrador.CargarEspecialistas();
		 * 
		 * LocalDate fecha = LocalDate.of(2024, 10, 1);
		 * 
		 * administrador.GenerarTurnos(fecha);
		 * 
		 * listaDeTurnos = administrador.getListaTurnos();
		 * 
		 * 
		 * VentanaTurnos ventTurnos =new VentanaTurnos();
		 * 
		 * Reporte reporteTurnos = new Reporte();
		 * 
		 * DefaultTableModel tableModel =
		 * reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);
		 * 
		 * ventTurnos.tableTurnos.setModel(tableModel);
		 * 
		 * ventTurnos.setVisible(true);
		 */

	}

	private void mostrarVentanaAsignarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getItemCount() == 0) {
			Periodo[] periodos = administrador.ConsultarPeriodos();
			for (int i = 0; i < periodos.length; i++) {
				ventanaAsignarTurnos.cboxPeriodo.addItem(periodos[i]);
			}
		}

		ventanaAsignarTurnos.setVisible(true);
	}

	private void mostrarVentanaReporeteTurnos() {

		Reporte reporteTurnos = new Reporte();

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);

		ventanaTurnos.tableTurnos.setModel(tableModel);
		ventanaTurnos.setVisible(true);
	}
	
	private void cargarTurnos() {
		TurnoDAO adminTurnos  = new TurnoDAO();
		listaDeTurnos = adminTurnos.getAll();
	}
	

	private void generarTurnos() {
		
		if (ventanaAsignarTurnos.cboxPeriodo.getSelectedIndex() < 0) {
			showMessageDialog(null, "Debe seleccionar un periodo");
			return;
		}

		Periodo periodosTurno = (Periodo) ventanaAsignarTurnos.cboxPeriodo.getSelectedItem();
		LocalDate fecha = LocalDate.of(periodosTurno.getYear(), periodosTurno.getMes(), 1);

		String resultado = administrador.GenerarTurnos(fecha);
		
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

		/*
		 * if(pizzeria.btnMasaRegular == e.getSource()) { ColocarMasa(2); }
		 */
	}

	public void cargarPacientes() {
		Date fecha = new Date();
		listaPaciente = new ArrayList<Paciente>();
		
		listaPaciente.add(new Paciente("1234", "Sara Angulo", "O+", 56, fecha));
		listaPaciente.add(new Paciente("2345", "Luis Ramírez", "B+", 45, fecha));
		listaPaciente.add(new Paciente("3456", "María López", "AB-", 29, fecha));
		listaPaciente.add(new Paciente("4567", "Carlos Gómez", "O-", 63, fecha));
		listaPaciente.add(new Paciente("5678", "Ana Torres", "A+", 37, fecha));
		listaPaciente.add(new Paciente("6789", "Miguel Fernández", "B-", 50, fecha));
		listaPaciente.add(new Paciente("7890", "Laura Martínez", "AB+", 27, fecha));
		listaPaciente.add(new Paciente("8901", "Pedro Castillo", "O+", 41, fecha));
		listaPaciente.add(new Paciente("9012", "Daniela Vargas", "A-", 33, fecha));
		listaPaciente.add(new Paciente("1235", "Jorge Herrera", "B+", 54, fecha));
		listaPaciente.add(new Paciente("2346", "Carmen Ruiz", "AB-", 22, fecha));
	}

	public void mostrarVentanaCitas() {
		DefaultListModel<Paciente> modelo = new DefaultListModel<>();

        // Poblar el modelo con el ArrayList
        for (Paciente elemento : listaPaciente) {
            modelo.addElement(elemento);
        }
		
		ventanaCita.listaPacientes.setModel(modelo);
		
		DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel<Especialidad>(administrador.getListaEspecialidades().toArray(new Especialidad[0]));
		
		ventanaCita.cboxEspecialidad.setModel(modeloCombo);
		
		
		ventanaCita.setVisible(true);
	}

}
