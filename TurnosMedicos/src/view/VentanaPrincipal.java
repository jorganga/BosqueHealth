package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAsignarTurnos;
	public JButton btnReporteTurnos;
	public JButton btnCita;
	public JButton btnMostrarCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
		btnAsignarTurnos.setBounds(44, 37, 143, 42);
		contentPane.add(btnAsignarTurnos);
		
		btnReporteTurnos = new JButton("Reporte Turnos");
		btnReporteTurnos.setBounds(44, 90, 143, 48);
		contentPane.add(btnReporteTurnos);
		
		btnCita = new JButton("Citas");
		btnCita.setBounds(44, 149, 143, 48);
		contentPane.add(btnCita);
		
		btnMostrarCita = new JButton("Mostrar Citas");
		btnMostrarCita.setBounds(44, 208, 143, 48);
		contentPane.add(btnMostrarCita);
	}
}
