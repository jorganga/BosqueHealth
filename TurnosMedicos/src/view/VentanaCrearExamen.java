package view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * Ventana para la creación de un examen médico.
 * Permite seleccionar el tipo de examen, ingresar detalles sobre el paciente y médico,
 * y crear un nuevo examen.
 */
public class VentanaCrearExamen extends JFrame {
    
    private JLabel lblBackground;
    private JLabel lblLogo;
    public JButton btnCrearExamen;
    public JLabel lblIdMedico;
    public JLabel lblIdPaciente;
    public JTextArea txtSeguimiento;
    public JComboBox cboTipoExamen;

    public VentanaCrearExamen() {
        setTitle("Examen");
        setResizable(false);
        setBounds(100, 100, 685, 538);
        getContentPane().setLayout(null);

        txtSeguimiento = new JTextArea();
        txtSeguimiento.setBackground(new Color(192, 192, 192));
        txtSeguimiento.setBounds(74, 198, 536, 212);
        getContentPane().add(txtSeguimiento);

        lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(VentanaCrearExamen.class.getResource("/view/—Pngtree—medical logo_3558939 (1).png")));
        lblLogo.setBounds(569, 0, 100, 100);
        getContentPane().add(lblLogo);

        lblIdPaciente = new JLabel("ID Paciente");
        lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblIdPaciente.setBounds(71, 89, 432, 20);
        getContentPane().add(lblIdPaciente);

        lblIdMedico = new JLabel("ID Medico");
        lblIdMedico.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblIdMedico.setBounds(71, 120, 432, 20);
        getContentPane().add(lblIdMedico);

        btnCrearExamen = new JButton("Crear Examen");
        btnCrearExamen.setBackground(new Color(128, 128, 128));
        btnCrearExamen.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnCrearExamen.setBounds(218, 418, 275, 43);
        getContentPane().add(btnCrearExamen);

        cboTipoExamen = new JComboBox();
        cboTipoExamen.setBounds(74, 151, 406, 22);
        getContentPane().add(cboTipoExamen);

        lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(VentanaCrearExamen.class.getResource("/view/pexels-francesco-ungaro-281260 (2).jpg")));
        lblBackground.setBounds(0, 0, 669, 499);
        getContentPane().add(lblBackground);

        JLabel lblNewLabel = new JLabel("Seleccione el Tipo de Examen");
        lblNewLabel.setBounds(10, 93, 406, 14);
        getContentPane().add(lblNewLabel);
    }
}

