package view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VentanaCrearSeguimiento extends JFrame{
	public JTextField textTratamiento;
	private JLabel lblBackground;
	private JLabel lblLogo;
	public JButton btnCrearSegumiento;
	public JLabel lblIdMedico ;
	public JLabel lblIdPaciente;
	
	public VentanaCrearSeguimiento() {
		setResizable(false);
		setBounds(100, 100, 704, 463);
		getContentPane().setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaCrearSeguimiento.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(588, 0, 100, 100);
		getContentPane().add(lblLogo);
		
		lblIdPaciente = new JLabel("ID Paciente");
		lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdPaciente.setBounds(198, 62, 254, 20);
		getContentPane().add(lblIdPaciente);
		
		lblIdMedico = new JLabel("ID Medico");
		lblIdMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMedico.setBounds(198, 93, 254, 20);
		getContentPane().add(lblIdMedico);
		
		textTratamiento = new JTextField();
		textTratamiento.setHorizontalAlignment(SwingConstants.LEFT);
		textTratamiento.setBackground(new Color(0, 164, 164));
		textTratamiento.setBounds(75, 149, 536, 166);
		getContentPane().add(textTratamiento);
		textTratamiento.setColumns(10);
		
		btnCrearSegumiento = new JButton("Crear Segumiento");
		btnCrearSegumiento.setBackground(new Color(128, 128, 128));
		btnCrearSegumiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearSegumiento.setBounds(198, 339, 275, 43);
		getContentPane().add(btnCrearSegumiento);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCrearSeguimiento.class.getResource("/view/pexels-francesco-ungaro-281260 (4).jpg")));
		lblBackground.setBounds(0, 0, 688, 424);
		getContentPane().add(lblBackground);
	}
}
