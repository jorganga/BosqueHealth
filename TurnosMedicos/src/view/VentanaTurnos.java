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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VentanaTurnos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableTurnos;
	private JLabel lblBackground;

	public VentanaTurnos() {
		setBounds(100, 100, 665, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(287, 352, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 37, 529, 292);
		contentPane.add(scrollPane);
		
		tableTurnos = new JTable();
		tableTurnos.setBackground(new Color(0, 164, 164));
		scrollPane.setViewportView(tableTurnos);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaTurnos.class.getResource("/view/pexels-francesco-ungaro-281260 (3).jpg")));
		lblBackground.setBounds(0, 0, 649, 389);
		contentPane.add(lblBackground);
	}
}
