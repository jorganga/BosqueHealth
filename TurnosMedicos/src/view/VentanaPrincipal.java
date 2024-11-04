package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAsignarTurnos;
	public JButton btnReporteTurnos;
	public JButton btnCita;
	public JButton btnMostrarCita;
	public JLabel lblBienvenido;

	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAsignarTurnos = new JButton("Asignar Turnos");
		btnAsignarTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAsignarTurnos.setBounds(44, 56, 143, 42);
		contentPane.add(btnAsignarTurnos);
		
		btnReporteTurnos = new JButton("Reporte Turnos");
		btnReporteTurnos.setBounds(44, 109, 143, 48);
		contentPane.add(btnReporteTurnos);
		
		btnCita = new JButton("Citas");
		btnCita.setBounds(44, 168, 143, 48);
		contentPane.add(btnCita);
		
		btnMostrarCita = new JButton("Mostrar Citas");
		btnMostrarCita.setBounds(44, 227, 143, 48);
		contentPane.add(btnMostrarCita);
		
		lblBienvenido = new JLabel("Bienvenido xxxx");
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBienvenido.setBounds(44, 12, 426, 33);
		contentPane.add(lblBienvenido);
	}
}
