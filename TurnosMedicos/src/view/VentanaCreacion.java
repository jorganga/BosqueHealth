package view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaCreacion extends JFrame{
	private JTextField textFieldBuscarPaciente;
	private JTable table;
	public VentanaCreacion() {
		getContentPane().setLayout(null);
		
		textFieldBuscarPaciente = new JTextField();
		textFieldBuscarPaciente.setBounds(61, 37, 121, 20);
		getContentPane().add(textFieldBuscarPaciente);
		textFieldBuscarPaciente.setColumns(10);
		
		JButton btnNuevoSeguimiento = new JButton("Crear Nuevo Seguimiento");
		btnNuevoSeguimiento.setBounds(274, 10, 155, 47);
		getContentPane().add(btnNuevoSeguimiento);
		
		JButton btnSolicitarExamenes = new JButton("Solicitar Examenes");
		btnSolicitarExamenes.setBounds(518, 10, 121, 47);
		getContentPane().add(btnSolicitarExamenes);
		
		JLabel lblNewLabel = new JLabel("Buscar Paciente");
		lblNewLabel.setBounds(61, 10, 121, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 68, 581, 163);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTratamientoYFecha = new JLabel("Detalle Del \r\nTratamiento / Fecha");
		lblTratamientoYFecha.setBounds(61, 242, 155, 26);
		getContentPane().add(lblTratamientoYFecha);
		
		JLabel lblDetalleTyF = new JLabel("(poner detalle)");
		lblDetalleTyF.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyF.setBounds(61, 265, 155, 26);
		getContentPane().add(lblDetalleTyF);
		
		JLabel lblTratamientoYMedico = new JLabel("Detalle Del \r\nTratamiento / Fecha");
		lblTratamientoYMedico.setBounds(239, 242, 155, 26);
		getContentPane().add(lblTratamientoYMedico);
		
		JLabel lblDetalleTyM = new JLabel("(poner detalle)");
		lblDetalleTyM.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyM.setBounds(239, 265, 155, 26);
		getContentPane().add(lblDetalleTyM);
		
		JLabel lblDetalleTyM2 = new JLabel("(poner detalle)");
		lblDetalleTyM2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyM2.setBounds(61, 335, 486, 76);
		getContentPane().add(lblDetalleTyM2);
	}
}
