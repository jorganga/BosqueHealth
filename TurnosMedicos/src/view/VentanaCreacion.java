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
	private JTable tableSeguimientos;
	public JButton btnBuscarPaciente;
	public JLabel lblPaciente;
	private JTable tableExamenes;
	public JButton btnNuevoSeguimiento;
	public JButton btnSolicitarExamenes;
	
	public VentanaCreacion() {
		setResizable(false);
		setTitle("Buscar Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(812, 0, 100, 100);
		lblLogo.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		getContentPane().add(lblLogo);
		
		btnNuevoSeguimiento = new JButton("Crear Nuevo Seguimiento");
		btnNuevoSeguimiento.setBounds(10, 64, 197, 47);
		btnNuevoSeguimiento.setBackground(new Color(128, 128, 128));
		btnNuevoSeguimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(btnNuevoSeguimiento);
		
		btnSolicitarExamenes = new JButton("Solicitar Examenes");
		btnSolicitarExamenes.setBounds(396, 64, 155, 47);
		btnSolicitarExamenes.setBackground(new Color(128, 128, 128));
		btnSolicitarExamenes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(btnSolicitarExamenes);
		
		JLabel lblBPaciente = new JLabel("Buscar Paciente");
		lblBPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBPaciente.setBounds(132, 113, 121, 16);
		getContentPane().add(btnNuevoSeguimiento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 362, 178);
		getContentPane().add(scrollPane);
		
		tableSeguimientos = new JTable();
		scrollPane.setViewportView(tableSeguimientos);
		
		JLabel lblTratamientoYFecha = new JLabel("Seguimientos");
		lblTratamientoYFecha.setBounds(10, 126, 155, 26);
		getContentPane().add(lblTratamientoYFecha);
		
		lblPaciente = new JLabel("xxx");
		lblPaciente.setBounds(160, 27, 311, 26);
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblPaciente);
		
		btnBuscarPaciente = new JButton("Buscar");
		btnBuscarPaciente.setBounds(61, 29, 89, 23);
		getContentPane().add(btnBuscarPaciente);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(388, 152, 362, 178);
		getContentPane().add(scrollPane_1);
		
		tableExamenes = new JTable();
		scrollPane_1.setViewportView(tableExamenes);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 802, 552);
		lblBackground.setIcon(new ImageIcon(VentanaCreacion.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		getContentPane().add(lblBackground);
	}
}
