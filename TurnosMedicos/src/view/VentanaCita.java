package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VentanaCita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableTurnos;
	public JList listaPacientes;
	public JComboBox cboxEspecialidad;
	public JButton btnCrearCita;
	private JLabel lblFlecha;
	private JLabel lblAsignacionDeCitas;
	private JLabel lblBackground;
	private JLabel lblLogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCita frame = new VentanaCita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaCita() {
		setResizable(false);
		setBounds(100, 100, 951, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaCita.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(835, 0, 100, 100);
		contentPane.add(lblLogo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(484, 127, 429, 290);
		contentPane.add(scrollPane);
		
		tableTurnos = new JTable();
		tableTurnos.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(tableTurnos);
		
		listaPacientes = new JList<Object>();
		listaPacientes.setBackground(new Color(192, 192, 192));
		listaPacientes.setBounds(49, 127, 288, 290);
		contentPane.add(listaPacientes);
		
		cboxEspecialidad = new JComboBox();
		cboxEspecialidad.setBounds(484, 104, 149, 22);
		contentPane.add(cboxEspecialidad);
		
		btnCrearCita = new JButton("Crear Cita");
		btnCrearCita.setBackground(new Color(128, 128, 128));
		btnCrearCita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCrearCita.setBounds(337, 472, 163, 40);
		contentPane.add(btnCrearCita);
		
		lblFlecha = new JLabel("");
		lblFlecha.setIcon(new ImageIcon(VentanaCita.class.getResource("/view/pngwing.com (1) (1).png")));
		lblFlecha.setBounds(359, 235, 108, 82);
		contentPane.add(lblFlecha);
		
		lblAsignacionDeCitas = new JLabel("Asignacion De Citas");
		lblAsignacionDeCitas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignacionDeCitas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAsignacionDeCitas.setBounds(107, 33, 647, 40);
		contentPane.add(lblAsignacionDeCitas);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaCita.class.getResource("/view/pexels-francesco-ungaro-281260 (3).jpg")));
		lblBackground.setBounds(0, 0, 935, 561);
		contentPane.add(lblBackground);
	}
}
