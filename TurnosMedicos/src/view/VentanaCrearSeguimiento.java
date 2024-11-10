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
import javax.swing.JTextArea;

/**
 * Ventana para crear y registrar un seguimiento médico de un paciente. Permite
 * ingresar un seguimiento a través de un área de texto y asignar un ID de
 * paciente y un ID de médico. La ventana también incluye un botón para guardar
 * el seguimiento y un logo de la clínica.
 */
public class VentanaCrearSeguimiento extends JFrame {

	private JLabel lblBackground;
	private JLabel lblLogo;
	public JButton btnCrearSegumiento;
	public JLabel lblIdMedico;
	public JLabel lblIdPaciente;
	public JTextArea txtSeguimiento;
	private JLabel lblSeguimientoMedico;

	public VentanaCrearSeguimiento() {
		setResizable(false);
		setBounds(100, 100, 826, 553);
		getContentPane().setLayout(null);

		lblSeguimientoMedico = new JLabel("SEGUIMIENTOS MEDICOS");
		lblSeguimientoMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguimientoMedico.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSeguimientoMedico.setBounds(65, 32, 647, 33);
		getContentPane().add(lblSeguimientoMedico);

		txtSeguimiento = new JTextArea();
		txtSeguimiento.setBackground(new Color(192, 192, 192));
		txtSeguimiento.setBounds(138, 160, 536, 247);
		getContentPane().add(txtSeguimiento);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(
				VentanaCrearSeguimiento.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(710, 0, 100, 100);
		getContentPane().add(lblLogo);

		lblIdPaciente = new JLabel("ID Paciente");
		lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdPaciente.setBounds(109, 98, 565, 20);
		getContentPane().add(lblIdPaciente);
	
		lblIdMedico = new JLabel("ID Medico");
		lblIdMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMedico.setBounds(109, 129, 565, 20);
		getContentPane().add(lblIdMedico);

		btnCrearSegumiento = new JButton("Crear Seguimiento");
		btnCrearSegumiento.setBackground(new Color(128, 128, 128));
		btnCrearSegumiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearSegumiento.setBounds(271, 439, 275, 43);
		getContentPane().add(btnCrearSegumiento);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(
				VentanaCrearSeguimiento.class.getResource("/view/pexels-francesco-ungaro-281260 (3).jpg")));
		lblBackground.setBounds(0, 0, 810, 514);
		getContentPane().add(lblBackground);
	}
}
