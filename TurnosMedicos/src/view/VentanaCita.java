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

public class VentanaCita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableTurnos;
	public JList listaPaciente;
	public JList listaPacientes;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VentanaCita() {
		setBounds(100, 100, 657, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(230, 352, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(263, 11, 368, 330);
		contentPane.add(scrollPane);
		
		tableTurnos = new JTable();
		scrollPane.setViewportView(tableTurnos);
		
		listaPacientes = new JList<Object>();
		listaPacientes.setBounds(10, 11, 211, 310);
		contentPane.add(listaPacientes);
	}
}
