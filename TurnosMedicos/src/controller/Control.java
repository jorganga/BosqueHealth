package controller;
//comentaario
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

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import model.ModelFacade;

import static java.time.temporal.ChronoUnit.DAYS;

import model.*;
import view.VentanaAsignarTurnos;
import view.VentanaCita;
import view.VentanaPrincipal;
import view.VentanaTurnos;
import static javax.swing.JOptionPane.showMessageDialog;

public class Control implements ActionListener {

	ArrayList<Profesional> listaDeDoctores;
	ArrayList<Turno> listaDeTurnos;
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

		CargarPacientes();

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

	private void MostrarVentanaAsignarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getItemCount() == 0) {
			Periodo[] periodos = administrador.ConsultarPeriodos();
			for (int i = 0; i < periodos.length; i++) {
				ventanaAsignarTurnos.cboxPeriodo.addItem(periodos[i]);
			}
		}

		ventanaAsignarTurnos.setVisible(true);
	}

	private void MostrarVentanaReporeteTurnos() {

		Reporte reporteTurnos = new Reporte();

		DefaultTableModel tableModel = reporteTurnos.GenerarReporteTurnosPorEspecialista(listaDeTurnos);

		ventanaTurnos.tableTurnos.setModel(tableModel);
		ventanaTurnos.setVisible(true);
	}

	private void GenerarTurnos() {

		if (ventanaAsignarTurnos.cboxPeriodo.getSelectedIndex() < 0) {
			showMessageDialog(null, "Debe seleccionar un periodo");
			return;
		}

		Periodo periodosTurno = (Periodo) ventanaAsignarTurnos.cboxPeriodo.getSelectedItem();
		LocalDate fecha = LocalDate.of(periodosTurno.getYear(), periodosTurno.getMes(), 1);

		String resultado = administrador.GenerarTurnos(fecha);

		listaDeTurnos = administrador.getListaTurnos();

		showMessageDialog(null, resultado);

		ventanaAsignarTurnos.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ventanaPrincipal.btnAsignarTurnos == e.getSource()) {
			MostrarVentanaAsignarTurnos();
		}

		if (ventanaAsignarTurnos.btnGenerarTurnos == e.getSource()) {
			GenerarTurnos();
		}

		if (ventanaPrincipal.btnReporteTurnos == e.getSource()) {
			MostrarVentanaReporeteTurnos();
		}
		
		if (ventanaPrincipal.btnCita == e.getSource()) {
			MostrarVentanaCitas();
		}

		/*
		 * if(pizzeria.btnMasaRegular == e.getSource()) { ColocarMasa(2); }
		 */
	}

	public void CargarPacientes() {
		Date fecha = new Date();
		listaPaciente = new ArrayList<Paciente>();
		
		listaPaciente.add(new Paciente("Sara", "1234", "o+", 56, fecha, 555));
		listaPaciente.add(
				new Paciente("Ayalita (Novio de Sara(la quiere mucho mumumurlkthdf))", "5552", "o+", 56, fecha, 555));
	}

	public void MostrarVentanaCitas() {
		DefaultListModel<Paciente> modelo = new DefaultListModel<>();

        // Poblar el modelo con el ArrayList
        for (Paciente elemento : listaPaciente) {
            modelo.addElement(elemento);
        }
		
		ventanaCita.listaPacientes.setModel(modelo);
		
		ventanaCita.setVisible(true);
	}

}
