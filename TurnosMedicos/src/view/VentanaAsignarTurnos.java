package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaAsignarTurnos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnGenerarTurnos;
	public JComboBox cboxPeriodo;
	
	public VentanaAsignarTurnos() {
		setTitle("Generar Turnos para Especialistas");
		setBounds(100, 100, 260, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cboxPeriodo = new JComboBox();
		cboxPeriodo.setBounds(32, 50, 170, 22);
		contentPane.add(cboxPeriodo);
		
		JLabel lblNewLabel = new JLabel("Indique el periodo a generar");
		lblNewLabel.setBounds(32, 21, 170, 28);
		contentPane.add(lblNewLabel);
		
		btnGenerarTurnos = new JButton("Generar Turnos");
		btnGenerarTurnos.setBounds(54, 117, 126, 23);
		contentPane.add(btnGenerarTurnos);
	}
}
