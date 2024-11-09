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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public VentanaCrearPaciente() {
		getContentPane().setLayout(null);
		
		JLabel lblCreacionDePaciente = new JLabel("CREACION DE PACIENTE");
		lblCreacionDePaciente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreacionDePaciente.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCreacionDePaciente.setBounds(0, 22, 436, 33);
		getContentPane().add(lblCreacionDePaciente);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblID.setBounds(169, 128, 33, 14);
		getContentPane().add(lblID);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(149, 162, 53, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblTipoSangre = new JLabel("Tipo De Sangre:");
		lblTipoSangre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTipoSangre.setBounds(69, 193, 133, 23);
		getContentPane().add(lblTipoSangre);
		
		JLabel lblNewLabel_3 = new JLabel("ID Medico:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(107, 231, 95, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Peso:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(156, 268, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha De Nacimiento:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(24, 306, 179, 14);
		getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(222, 128, 115, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(222, 162, 115, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(222, 197, 115, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(222, 231, 115, 20);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(222, 268, 115, 20);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(222, 306, 115, 20);
		getContentPane().add(textField_5);
		
		JButton btnCrearPaciente = new JButton("Crear");
		btnCrearPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearPaciente.setBackground(Color.LIGHT_GRAY);
		btnCrearPaciente.setBounds(128, 364, 179, 33);
		getContentPane().add(btnCrearPaciente);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCrearPaciente.class.getResource("/view/pexels-francesco-ungaro-281260 (4).jpg")));
		lblBackground.setBounds(0, 0, 436, 443);
		getContentPane().add(lblBackground);
	}
}
