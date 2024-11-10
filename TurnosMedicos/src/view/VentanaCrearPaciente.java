package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VentanaCrearPaciente extends JFrame{
	
	public JTextField txtIdentificacion;
	public JTextField txtEmail;
	public JTextField txtTipoSangre;
	public JTextField txtPeso;
	public JTextField txtFechaNacimiento;
	public JTextField txtNombre;
	public JButton btnCrearPaciente;
	
	public VentanaCrearPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 432);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("yyyy-MM-dd");
		lblNewLabel.setBounds(221, 291, 87, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblCreacionDePaciente = new JLabel("CREACION DE PACIENTE");
		lblCreacionDePaciente.setBounds(0, 22, 407, 33);
		lblCreacionDePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreacionDePaciente.setFont(new Font("Tahoma", Font.PLAIN, 30));
		getContentPane().add(lblCreacionDePaciente);
		
		JLabel lblID = new JLabel("Identificación:");
		lblID.setBounds(87, 128, 115, 14);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblID);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(149, 162, 53, 14);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblEmail);
		
		JLabel lblTipoSangre = new JLabel("Tipo De Sangre:");
		lblTipoSangre.setBounds(69, 193, 133, 23);
		lblTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblTipoSangre);
		
		JLabel lblNewLabel_4 = new JLabel("Peso:");
		lblNewLabel_4.setBounds(156, 231, 46, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha De Nacimiento:");
		lblNewLabel_5.setBounds(24, 269, 179, 23);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNewLabel_5);
		
		txtIdentificacion = new JTextField();
		txtIdentificacion.setBounds(222, 128, 185, 20);
		getContentPane().add(txtIdentificacion);
		txtIdentificacion.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(222, 162, 185, 20);
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail);
		
		txtTipoSangre = new JTextField();
		txtTipoSangre.setBounds(222, 197, 185, 20);
		txtTipoSangre.setColumns(10);
		getContentPane().add(txtTipoSangre);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(222, 231, 185, 20);
		txtPeso.setColumns(10);
		getContentPane().add(txtPeso);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(222, 269, 185, 20);
		txtFechaNacimiento.setColumns(10);
		getContentPane().add(txtFechaNacimiento);
		
		btnCrearPaciente = new JButton("Crear");
		btnCrearPaciente.setBounds(163, 327, 179, 33);
		btnCrearPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearPaciente.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(btnCrearPaciente);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(222, 92, 185, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNombre.setBounds(87, 92, 115, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 476, 393);
		lblBackground.setIcon(new ImageIcon(VentanaCrearPaciente.class.getResource("/view/pexels-francesco-ungaro-281260 (4).jpg")));
		getContentPane().add(lblBackground);
	}
}
