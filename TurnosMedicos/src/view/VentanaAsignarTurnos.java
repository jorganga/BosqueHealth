package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

/**
 * Ventana para generar turnos para especialistas.
 * Contiene un combo box para seleccionar un periodo y un botón para generar los turnos.
 */
public class VentanaAsignarTurnos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnGenerarTurnos;
	public JComboBox cboxPeriodo;
	private JLabel lblBackground;

	public VentanaAsignarTurnos() {
		setResizable(false);
		setTitle("Generar Turnos para Especialistas");
		setBounds(100, 100, 260, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		cboxPeriodo = new JComboBox();
		cboxPeriodo.setBackground(new Color(192, 192, 192));
		cboxPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboxPeriodo.setBounds(33, 62, 179, 28);
		contentPane.add(cboxPeriodo);

		JLabel lblPeriodoGenerar = new JLabel("Indique el periodo a generar");
		lblPeriodoGenerar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeriodoGenerar.setBounds(10, 21, 224, 28);
		contentPane.add(lblPeriodoGenerar);

		btnGenerarTurnos = new JButton("Generar Turnos");
		btnGenerarTurnos.setBackground(new Color(192, 192, 192));
		btnGenerarTurnos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGenerarTurnos.setBounds(33, 126, 179, 33);
		contentPane.add(btnGenerarTurnos);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(VentanaAsignarTurnos.class.getResource("/view/pexels-francesco-ungaro-281260 (1).jpg")));
		lblBackground.setBounds(0, 0, 244, 219);
		contentPane.add(lblBackground);
	}
}

