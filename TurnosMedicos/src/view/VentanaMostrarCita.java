package view;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaMostrarCita extends JFrame{
	public JTable tableMostrarCita;
	
	public VentanaMostrarCita() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 46, 317, 174);
		getContentPane().add(scrollPane);
		
		tableMostrarCita = new JTable();
		scrollPane.setViewportView(tableMostrarCita);
		
	}
}
