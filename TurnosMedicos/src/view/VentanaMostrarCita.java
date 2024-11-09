package view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VentanaMostrarCita extends JFrame{
	public JTable tableMostrarCita;
	public JButton btnCancelarCita;
	private JLabel lblSeleccioneLaCita;
	private JLabel lblBackground;
	
	public VentanaMostrarCita() {
		setBounds(100, 100, 900, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 77, 764, 402);
		getContentPane().add(scrollPane);
		
		tableMostrarCita = new JTable();
		tableMostrarCita.setBackground(new Color(0, 164, 164));
		scrollPane.setViewportView(tableMostrarCita);
		
		btnCancelarCita = new JButton("CANCELAR CITA");
		btnCancelarCita.setBackground(new Color(128, 128, 128));
		btnCancelarCita.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCancelarCita.setBounds(336, 491, 202, 42);
		getContentPane().add(btnCancelarCita);
		
		lblSeleccioneLaCita = new JLabel("Seleccione la cita a cancelar");
		lblSeleccioneLaCita.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneLaCita.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSeleccioneLaCita.setBounds(115, 17, 647, 33);
		getContentPane().add(lblSeleccioneLaCita);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaMostrarCita.class.getResource("/view/pexels-francesco-ungaro-281260 (3).jpg")));
		lblBackground.setBounds(0, 0, 884, 561);
		getContentPane().add(lblBackground);
		
	}
}
