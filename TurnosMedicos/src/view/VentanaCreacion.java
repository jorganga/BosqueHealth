package view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VentanaCreacion extends JFrame{
	private JTextField textFieldBuscarPaciente;
	private JTable table;
	public VentanaCreacion() {
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(812, 0, 100, 100);
		getContentPane().add(lblLogo);
		
		textFieldBuscarPaciente = new JTextField();
		textFieldBuscarPaciente.setBounds(132, 140, 121, 20);
		getContentPane().add(textFieldBuscarPaciente);
		textFieldBuscarPaciente.setColumns(10);
		
		JButton btnNuevoSeguimiento = new JButton("Crear Nuevo Seguimiento");
		btnNuevoSeguimiento.setBackground(new Color(128, 128, 128));
		btnNuevoSeguimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNuevoSeguimiento.setBounds(336, 113, 197, 47);
		getContentPane().add(btnNuevoSeguimiento);
		
		JButton btnSolicitarExamenes = new JButton("Solicitar Examenes");
		btnSolicitarExamenes.setBackground(new Color(128, 128, 128));
		btnSolicitarExamenes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSolicitarExamenes.setBounds(578, 113, 155, 47);
		getContentPane().add(btnSolicitarExamenes);
		
		JLabel lblNewLabel = new JLabel("Buscar Paciente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(132, 113, 121, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(143, 183, 581, 219);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTratamientoYFecha = new JLabel("Detalle Del \r\nTratamiento / Fecha");
		lblTratamientoYFecha.setBounds(57, 438, 155, 26);
		getContentPane().add(lblTratamientoYFecha);
		
		JLabel lblDetalleTyF = new JLabel("(poner detalle)");
		lblDetalleTyF.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyF.setBounds(57, 461, 155, 26);
		getContentPane().add(lblDetalleTyF);
		
		JLabel lblTratamientoYMedico = new JLabel("Detalle Del \r\nTratamiento / Fecha");
		lblTratamientoYMedico.setBounds(235, 438, 155, 26);
		getContentPane().add(lblTratamientoYMedico);
		
		JLabel lblDetalleTyM = new JLabel("(poner detalle)");
		lblDetalleTyM.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyM.setBounds(235, 461, 155, 26);
		getContentPane().add(lblDetalleTyM);
		
		JLabel lblDetalleTyM2 = new JLabel("(poner detalle)");
		lblDetalleTyM2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleTyM2.setBounds(426, 413, 486, 76);
		getContentPane().add(lblDetalleTyM2);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		lblBackground.setBounds(0, 0, 912, 552);
		getContentPane().add(lblBackground);
	}
}
