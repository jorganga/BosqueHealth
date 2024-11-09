package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaBuscarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tablePaciente;
	public JTextField txtBuscar;
	public JButton btnBuscar;
	public JButton btnSeleccionar;
	
	public VentanaBuscarPaciente() {
		setTitle("Buscar Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 61, 641, 220);
		contentPane.add(scrollPane);
		
		tablePaciente = new JTable();
		scrollPane.setViewportView(tablePaciente);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(276, 293, 114, 34);
		contentPane.add(btnSeleccionar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(98, 30, 285, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Identificación");
		lblNewLabel.setBounds(9, 32, 79, 17);
		contentPane.add(lblNewLabel);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(393, 27, 89, 23);
		contentPane.add(btnBuscar);
	}
}
