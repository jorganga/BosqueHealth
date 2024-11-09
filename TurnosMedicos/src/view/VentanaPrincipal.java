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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAsignarTurnos;
	public JButton btnReporteTurnos;
	public JButton btnCita;
	public JButton btnMostrarCita;
	public JLabel lblBienvenido;
	private JLabel lblBackground;
	private JLabel lblLogo;

	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAsignarTurnos = new JButton("Asignar Turnos");
		btnAsignarTurnos.setBackground(new Color(128, 128, 128));
		btnAsignarTurnos.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnAsignarTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(391, 144, 100, 100);
		contentPane.add(lblLogo);
		btnAsignarTurnos.setBounds(342, 313, 181, 48);
		contentPane.add(btnAsignarTurnos);
		
		btnReporteTurnos = new JButton("Reporte Turnos");
		btnReporteTurnos.setBackground(new Color(128, 128, 128));
		btnReporteTurnos.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnReporteTurnos.setBounds(118, 313, 181, 48);
		contentPane.add(btnReporteTurnos);
		
		btnCita = new JButton("Citas");
		btnCita.setBackground(new Color(128, 128, 128));
		btnCita.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnCita.setBounds(567, 313, 143, 48);
		contentPane.add(btnCita);
		
		btnMostrarCita = new JButton("Mostrar Citas");
		btnMostrarCita.setBackground(new Color(128, 128, 128));
		btnMostrarCita.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnMostrarCita.setBounds(342, 394, 181, 48);
		contentPane.add(btnMostrarCita);
		
		lblBienvenido = new JLabel("BIENVENIDO A SOFTHEALTH xxxx");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblBienvenido.setBounds(118, 89, 647, 33);
		contentPane.add(lblBienvenido);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
		lblBackground.setBounds(0, 0, 894, 572);
		contentPane.add(lblBackground);
	}
}
