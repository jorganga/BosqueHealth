package view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class VentanaCrearSeguimiento extends JFrame{
	private JTextField textIdPaciente;
	private JTextField textIdMedico;
	private JTextField textTratamiento;
	private JLabel lblBackground;
	private JLabel lblLogo;
	public VentanaCrearSeguimiento() {
		setResizable(false);
		setBounds(100, 100, 704, 463);
		getContentPane().setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaCrearSeguimiento.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(588, 0, 100, 100);
		getContentPane().add(lblLogo);
		
		textIdPaciente = new JTextField();
		textIdPaciente.setBounds(296, 62, 164, 20);
		getContentPane().add(textIdPaciente);
		textIdPaciente.setColumns(10);
		
		JLabel lblIdPaciente = new JLabel("ID Paciente:");
		lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdPaciente.setBounds(198, 62, 90, 20);
		getContentPane().add(lblIdPaciente);
		
		JLabel lblIdMedico = new JLabel("ID Medico:");
		lblIdMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMedico.setBounds(208, 93, 90, 20);
		getContentPane().add(lblIdMedico);
		
		textIdMedico = new JTextField();
		textIdMedico.setColumns(10);
		textIdMedico.setBounds(296, 93, 164, 20);
		getContentPane().add(textIdMedico);
		
		textTratamiento = new JTextField();
		textTratamiento.setBackground(new Color(0, 164, 164));
		textTratamiento.setBounds(75, 149, 536, 166);
		getContentPane().add(textTratamiento);
		textTratamiento.setColumns(10);
		
		JButton btnCrearSegumiento = new JButton("Crear Segumiento");
		btnCrearSegumiento.setBackground(new Color(128, 128, 128));
		btnCrearSegumiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearSegumiento.setBounds(244, 342, 193, 43);
		getContentPane().add(btnCrearSegumiento);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCrearSeguimiento.class.getResource("/view/pexels-francesco-ungaro-281260 (4).jpg")));
		lblBackground.setBounds(0, 0, 688, 424);
		getContentPane().add(lblBackground);
	}
}
