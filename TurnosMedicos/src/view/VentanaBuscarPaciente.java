package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Ventana para buscar pacientes en el sistema. Permite ingresar la
 * identificación del paciente y mostrar los resultados en una tabla.
 */
public class VentanaBuscarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tablePaciente;
	public JTextField txtBuscar;
	public JButton btnBuscar;
	public JButton btnSeleccionar;
	private JLabel lblBackground;
	private JLabel lblLogo;
	private JLabel lblGestionDePacientes;

	public VentanaBuscarPaciente() {
		setTitle("Buscar Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 816, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblGestionDePacientes = new JLabel("GESTION DE PACIENTES");
		lblGestionDePacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDePacientes.setBounds(71, 21, 647, 33);
		contentPane.add(lblGestionDePacientes);

		lblLogo = new JLabel("");
		lblLogo.setIcon(
				new ImageIcon(VentanaBuscarPaciente.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(700, 0, 100, 100);
		contentPane.add(lblLogo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 171, 641, 220);
		contentPane.add(scrollPane);

		tablePaciente = new JTable();
		tablePaciente.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(tablePaciente);

		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSeleccionar.setBackground(new Color(128, 128, 128));
		btnSeleccionar.setBounds(329, 430, 148, 34);
		contentPane.add(btnSeleccionar);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(164, 114, 285, 29);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel = new JLabel("Identificación");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 121, 121, 17);
		contentPane.add(lblNewLabel);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBuscar.setBounds(470, 114, 108, 29);
		contentPane.add(btnBuscar);

		lblBackground = new JLabel("");
		lblBackground.setIcon(
				new ImageIcon(VentanaBuscarPaciente.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		lblBackground.setBounds(0, 0, 800, 486);
		contentPane.add(lblBackground);
	}
}
