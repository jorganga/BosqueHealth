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

public class VentanaCita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableTurnos;
	public JList listaPacientes;
	public JComboBox cboxEspecialidad;
	public JButton btnCrearCita;

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
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 34, 644, 290);
		contentPane.add(scrollPane);
		
		tableTurnos = new JTable();
		scrollPane.setViewportView(tableTurnos);
		
		listaPacientes = new JList<Object>();
		listaPacientes.setBounds(10, 34, 215, 179);
		contentPane.add(listaPacientes);
		
		cboxEspecialidad = new JComboBox();
		cboxEspecialidad.setBounds(230, 11, 149, 22);
		contentPane.add(cboxEspecialidad);
		
		btnCrearCita = new JButton("Crear Cita");
		btnCrearCita.setBounds(230, 346, 188, 23);
		contentPane.add(btnCrearCita);
	}
}
