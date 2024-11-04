package view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaMostrarCita extends JFrame{
	public JTable tableMostrarCita;
	
	public VentanaMostrarCita() {
		setBounds(100, 100, 900, 600);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 764, 369);
		getContentPane().add(scrollPane);
		
		tableMostrarCita = new JTable();
		scrollPane.setViewportView(tableMostrarCita);
		
	}
}
