package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAsignarTurnos;
	public JButton btnReporteTurnos;
	public JButton btnCita;
	public JButton btnMostrarCita;
	public JButton btnMostrarCita_1;
	public JLabel lblBienvenido;
	private JLabel lblBackground;
	private JLabel lblLogo;
	public JButton btnSeguimientos;
	private JLabel lblQueDeseaHacer;
	public JButton btnCrearPaciente;
	/**
    * Constructor que inicializa los componentes visuales de la ventana principal de la aplicación.
    * Configura botones, etiquetas, y el fondo, permitiendo la interacción del usuario con las opciones disponibles.
    */
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		btnAsignarTurnos = new JButton("Asignar Turnos");
		btnAsignarTurnos.setBackground(new Color(128, 128, 128));
		btnAsignarTurnos.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnAsignarTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCrearPaciente = new JButton("Crear Paciente");
		btnCrearPaciente.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnCrearPaciente.setBackground(Color.GRAY);
		btnCrearPaciente.setBounds(342, 372, 181, 48);
		contentPane.add(btnCrearPaciente);
		
		lblQueDeseaHacer = new JLabel("Que desea hacer hoy?");
		lblQueDeseaHacer.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueDeseaHacer.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblQueDeseaHacer.setBounds(118, 239, 647, 33);
		contentPane.add(lblQueDeseaHacer);
		btnSeguimientos = new JButton("Seguimientos");
		btnSeguimientos.setBackground(new Color(128, 128, 128));
		btnSeguimientos.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnSeguimientos.setBounds(567, 372, 181, 48);
		contentPane.add(btnSeguimientos);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(390, 93, 100, 100);
		contentPane.add(lblLogo);
		btnAsignarTurnos.setBounds(342, 313, 181, 48);
		contentPane.add(btnAsignarTurnos);
		
		btnReporteTurnos = new JButton("Reporte Turnos");
		btnReporteTurnos.setBackground(new Color(128, 128, 128));
		btnReporteTurnos.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnReporteTurnos.setBounds(118, 313, 181, 48);
		contentPane.add(btnReporteTurnos);
		
		btnCita = new JButton("Asignar Citas");
		btnCita.setBackground(new Color(128, 128, 128));
		btnCita.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnCita.setBounds(567, 313, 181, 48);
		contentPane.add(btnCita);
		
		btnMostrarCita = new JButton("Cancelar Citas");
		btnMostrarCita.setBackground(new Color(128, 128, 128));
		btnMostrarCita.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnMostrarCita.setBounds(118, 372, 181, 48);
		contentPane.add(btnMostrarCita);
		
		lblBienvenido = new JLabel("BIENVENIDO A SOFTHEALTH ");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBienvenido.setBounds(118, 31, 647, 33);
		contentPane.add(lblBienvenido);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		lblBackground.setBounds(0, 0, 894, 572);
		contentPane.add(lblBackground);
	}
}
