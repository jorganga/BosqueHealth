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
	public JPasswordField pwd;

	public VentanaLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(70, 71, 149, 20);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setText("1034517158");
		txtUsuario.setText("1034517158");
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(70, 47, 115, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 102, 115, 14);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPassword);
		
		btnLogin = new JButton("Iniciar Sesión");
		btnLogin.setBounds(79, 159, 123, 39);
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setSelected(true);
		contentPane.add(btnLogin);
		
		pwd = new JPasswordField();
		pwd.setText("123");
		pwd.setBounds(70, 117, 149, 20);
		pwd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(pwd);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 284, 223);
		lblBackground.setIcon(new ImageIcon(VentanaLogin.class.getResource("/view/pexels-francesco-ungaro-281260 (1).jpg")));
		contentPane.add(lblBackground);
		
		pwd.setText("123");
		
	}
}
