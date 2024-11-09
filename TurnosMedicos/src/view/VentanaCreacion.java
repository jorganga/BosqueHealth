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
	public JTable tableSeguimientos;
	public JButton btnBuscarPaciente;
	public JLabel lblPaciente;
	public JTable tableExamenes;
	public JButton btnNuevoSeguimiento;
	public JButton btnSolicitarExamenes;
	
	public VentanaCreacion() {
		setResizable(false);
		setTitle("Buscar Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 600);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(677, 0, 100, 100);
		lblLogo.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		getContentPane().add(lblLogo);
		
		btnNuevoSeguimiento = new JButton("Crear Nuevo Seguimiento");
		btnNuevoSeguimiento.setBounds(10, 161, 362, 47);
		btnNuevoSeguimiento.setBackground(new Color(128, 128, 128));
		btnNuevoSeguimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnNuevoSeguimiento);
		
		btnSolicitarExamenes = new JButton("Solicitar Examenes");
		btnSolicitarExamenes.setBounds(429, 163, 207, 47);
		btnSolicitarExamenes.setBackground(new Color(128, 128, 128));
		btnSolicitarExamenes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnSolicitarExamenes);
		
		JLabel lblBPaciente = new JLabel("Buscar Paciente");
		lblBPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBPaciente.setBounds(132, 113, 121, 16);
		getContentPane().add(btnNuevoSeguimiento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 285, 362, 178);
		getContentPane().add(scrollPane);
		
		tableSeguimientos = new JTable();
		tableSeguimientos.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(tableSeguimientos);
		
		JLabel lblTratamientoYFecha = new JLabel("Seguimientos");
		lblTratamientoYFecha.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTratamientoYFecha.setBounds(10, 235, 176, 39);
		getContentPane().add(lblTratamientoYFecha);
		
		lblPaciente = new JLabel("(NOMBRE DE PACIENTE)");
		lblPaciente.setBounds(148, 74, 311, 26);
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblPaciente);
		
		btnBuscarPaciente = new JButton("Buscar");
		btnBuscarPaciente.setBackground(new Color(128, 128, 128));
		btnBuscarPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarPaciente.setBounds(10, 74, 128, 26);
		getContentPane().add(btnBuscarPaciente);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(388, 285, 362, 178);
		getContentPane().add(scrollPane_1);
		
		tableExamenes = new JTable();
		tableExamenes.setBackground(new Color(192, 192, 192));
		scrollPane_1.setViewportView(tableExamenes);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 784, 561);
		lblBackground.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		getContentPane().add(lblBackground);
	}
}
