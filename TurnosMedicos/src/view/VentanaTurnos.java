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
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaTurnos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tableTurnos;
	private JLabel lblBackground;
	private JLabel lblLogo;
	private JLabel lblTurnos;

	public VentanaTurnos() {
		setBounds(100, 100, 766, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTurnos = new JLabel("TURNOS\r\n");
		lblTurnos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurnos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTurnos.setBounds(60, 11, 647, 33);
		contentPane.add(lblTurnos);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaTurnos.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
		lblLogo.setBounds(650, 0, 100, 100);
		contentPane.add(lblLogo);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(334, 474, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 143, 529, 292);
		contentPane.add(scrollPane);
		
		tableTurnos = new JTable();
		tableTurnos.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(tableTurnos);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaTurnos.class.getResource("/view/pexels-francesco-ungaro-281260 (3).jpg")));
		lblBackground.setBounds(0, 0, 750, 523);
		contentPane.add(lblBackground);
	}
}
