package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Autocar;
import net.miginfocom.swing.MigLayout;

public class VentanaAutocar extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtMarca;
	private JTextField txtKilometros;
	private JTextField txtModelo;
	private JTable table;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private ArrayList<Autocar> listaAutocares;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAutocar frame = new VentanaAutocar();
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
	public VentanaAutocar() {
		listaAutocares = new ArrayList<Autocar>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][162.00,grow][][grow]", "[][][][][][fill][grow]"));
		
		JLabel lblTitulo = new JLabel("Gestión autocares");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setOpaque(true);
		lblTitulo.setBackground(new Color(0, 64, 128));
		lblTitulo.setForeground(new Color(255, 128, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblTitulo, "cell 0 0 4 1,growx");
		
		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblMatricula, "cell 0 1,alignx trailing");
		
		txtMatricula = new JTextField();
		contentPane.add(txtMatricula, "cell 1 1,growx");
		txtMatricula.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblMarca, "cell 0 2,alignx trailing");
		
		txtMarca = new JTextField();
		contentPane.add(txtMarca, "cell 1 2,growx");
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblModelo, "cell 2 2,alignx trailing");
		
		txtModelo = new JTextField();
		contentPane.add(txtModelo, "cell 3 2,growx");
		txtModelo.setColumns(10);
		
		JLabel lblKilometros = new JLabel("Kilómetros:");
		lblKilometros.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblKilometros, "cell 0 3,alignx trailing");
		
		txtKilometros = new JTextField();
		contentPane.add(txtKilometros, "cell 1 3,growx");
		txtKilometros.setColumns(10);
		
		JLabel lblPlazas = new JLabel("Plazas:");
		lblPlazas.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblPlazas, "cell 2 3,alignx trailing");
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(30, 1, 80, 1));
		contentPane.add(spinner, "cell 3 3");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 6 4 1,grow");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Marca", "Modelo", "Kil\u00F3metros", "Plazas", "Matr\u00EDcula"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		contentPane.add(btnInsertar, "flowx,cell 1 4,alignx right");
		
		JButton btnMostrar = new JButton("Mostrar Datos");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatos();
			}
		});
		contentPane.add(btnMostrar, "cell 2 4 2 1,alignx left");
	}

	protected void insertar() {
		String matricula = txtMatricula.getText();
		String marca = txtMarca.getText();
		String modelo = txtModelo.getText();
		int kilometros = 0;
		int plazas = (int) spinner.getValue();
		
		Autocar a = new Autocar(matricula, marca, modelo, kilometros, plazas);
		if (matricula == null || matricula.isBlank()
				|| marca==null || marca.isBlank()
				|| modelo==null || modelo.isBlank()
			 || txtKilometros.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Hay datos sin introducir. Por favor, introduzca todos los datos requeridos.", "Faltan datos", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Integer.parseInt(txtKilometros.getText());
		
		if (!listaAutocares.contains(a)) {
			listaAutocares.add(a);
		} else {
			JOptionPane.showMessageDialog(this, "La matrícula introducida ya existe en la base de datos.", "Matrícula incorrecta", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	protected void mostrarDatos() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0); 
		for (Autocar a : listaAutocares) {
			Object fila [] = {
					a.getMarca(), a.getModelo(), a.getKilometros(), a.getNum_plazas(), a.getMatricula()
			};
			modelo.addRow(fila);
		}
	}
}
