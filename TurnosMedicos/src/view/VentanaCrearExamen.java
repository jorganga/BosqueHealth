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

public class VentanaCrearExamen extends JFrame{
	private JLabel lblBackground;
	private JLabel lblLogo;
	public JButton btnCrearExamen;
	public JLabel lblIdMedico ;
	public JLabel lblIdPaciente;
	public JTextArea txtSeguimiento;
	public JComboBox cboTipoExamen;
	
	public VentanaCrearExamen() {
		setTitle("Examen");
		setResizable(false);
		setBounds(100, 100, 573, 463);
		getContentPane().setLayout(null);
		
		txtSeguimiento = new JTextArea();
		txtSeguimiento.setBounds(10, 141, 536, 212);
		getContentPane().add(txtSeguimiento);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaCrearExamen.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(452, 4, 100, 100);
		getContentPane().add(lblLogo);
		
		lblIdPaciente = new JLabel("ID Paciente");
		lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdPaciente.setBounds(9, 31, 432, 20);
		getContentPane().add(lblIdPaciente);
		
		lblIdMedico = new JLabel("ID Medico");
		lblIdMedico.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdMedico.setBounds(9, 62, 432, 20);
		getContentPane().add(lblIdMedico);
		
		btnCrearExamen = new JButton("Crear Examen");
		btnCrearExamen.setBackground(new Color(128, 128, 128));
		btnCrearExamen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearExamen.setBounds(154, 361, 275, 43);
		getContentPane().add(btnCrearExamen);
		
		cboTipoExamen = new JComboBox();
		cboTipoExamen.setBounds(10, 108, 406, 22);
		getContentPane().add(cboTipoExamen);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCrearExamen.class.getResource("/view/pexels-francesco-ungaro-281260 (4).jpg")));
		lblBackground.setBounds(0, 0, 688, 424);
		getContentPane().add(lblBackground);
		
		JLabel lblNewLabel = new JLabel("Seleccione el Tipo de Examen");
		lblNewLabel.setBounds(10, 93, 406, 14);
		getContentPane().add(lblNewLabel);
	}
}
