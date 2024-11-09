package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtUsuario;
	public JButton btnLogin;
	public JPasswordField passwordField;

	public VentanaLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setText("1034517158");
		txtUsuario.setBounds(70, 62, 149, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(70, 47, 115, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(70, 102, 115, 14);
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setSelected(true);
		btnLogin.setBounds(79, 159, 123, 39);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(70, 117, 149, 20);
		contentPane.add(passwordField);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaLogin.class.getResource("/view/pexels-francesco-ungaro-281260 (1).jpg")));
		lblBackground.setBounds(0, 0, 284, 223);
		contentPane.add(lblBackground);
	}
}
