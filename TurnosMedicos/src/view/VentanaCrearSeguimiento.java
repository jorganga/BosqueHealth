package view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaCrearSeguimiento extends JFrame{
	private JTextField textIdPaciente;
	private JTextField textIdMedico;
	private JTextField textTratamiento;
	public VentanaCrearSeguimiento() {
		setBounds(100, 100, 665, 428);
		getContentPane().setLayout(null);
		
		textIdPaciente = new JTextField();
		textIdPaciente.setBounds(165, 11, 164, 20);
		getContentPane().add(textIdPaciente);
		textIdPaciente.setColumns(10);
		
		JLabel lblIdPaciente = new JLabel("ID Paciente:");
		lblIdPaciente.setBounds(91, 11, 64, 20);
		getContentPane().add(lblIdPaciente);
		
		JLabel lblIdMedico = new JLabel("ID Medico:");
		lblIdMedico.setBounds(91, 42, 64, 20);
		getContentPane().add(lblIdMedico);
		
		textIdMedico = new JTextField();
		textIdMedico.setColumns(10);
		textIdMedico.setBounds(165, 42, 164, 20);
		getContentPane().add(textIdMedico);
		
		textTratamiento = new JTextField();
		textTratamiento.setBounds(70, 133, 536, 133);
		getContentPane().add(textTratamiento);
		textTratamiento.setColumns(10);
		
		JButton btnCrearSegumiento = new JButton("Crear Segumiento");
		btnCrearSegumiento.setBounds(278, 312, 132, 23);
		getContentPane().add(btnCrearSegumiento);
	}
}
