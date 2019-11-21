package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Componente;
import logica.DiscoDuro;
import logica.Micro;
import logica.MotherBoard;
import logica.Proveedor;
import logica.RAM;
import logica.Tienda;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;


public class AgregarComponente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnDiscoDuro;
	private JRadioButton rdbtnMicro;
	private JRadioButton rdbtnMotherBoard;
	private JRadioButton rdbtnRAM;
	private JScrollPane scrollPane;
	private JPanel panel_DiscoDuro;
	private JPanel panel_Micro;
	private JPanel panel_MotherBoard;
	private JPanel panel_RAM;
	private JPanel panel_Proveedor;
	private JButton btnAgregarVendedor;
	private JLabel lblAlmacenamiento;
	private JLabel lblTipoConexion;
	private JTextField txtNoSerie;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtVelocidad;
	private JComboBox cbxAlmacenamiento;
	private JComboBox cbxTipoConexionDD;
	private JComboBox cbxTipoConexionMicro;
	private JComboBox cbxTipoConectorMB;
	private JComboBox cbxTipoRAM;
	private JComboBox cbxTipo;
	private JFormattedTextField ftxtCantMemoria;
	private JSpinner spnPrecioVenta;
	private JSpinner spnCantMax;
	private JSpinner spnCantMin;
	private static DefaultTableModel model;
	private static Object[] row;
	private String codigo="";
	private JTable table;


	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			AgregarComponente dialog = new AgregarComponente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AgregarComponente(boolean b) {
		setLocationRelativeTo(null);
		setTitle("Crear Componente");
		setBounds(100, 100, 460, 492);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JPanel panel_DatosGeneralesComponentes = new JPanel();
		panel_DatosGeneralesComponentes.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos generales del componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DatosGeneralesComponentes.setBounds(10, 11, 216, 161);
		contentPanel.add(panel_DatosGeneralesComponentes);
		panel_DatosGeneralesComponentes.setLayout(null);

		JLabel lblId = new JLabel("N\u00BA de serie:");
		lblId.setBounds(10, 29, 71, 14);
		panel_DatosGeneralesComponentes.add(lblId);
		

		JLabel lblPrecioBase = new JLabel("Marca: ");
		lblPrecioBase.setBounds(10, 72, 64, 14);
		panel_DatosGeneralesComponentes.add(lblPrecioBase);

		JLabel lblPrecioUnitario = new JLabel("Modelo: ");
		lblPrecioUnitario.setBounds(10, 115, 64, 14);
		panel_DatosGeneralesComponentes.add(lblPrecioUnitario);
		
		txtNoSerie = new JTextField();
		txtNoSerie.setEditable(false);
		txtNoSerie.setBounds(110, 26, 96, 20);
		panel_DatosGeneralesComponentes.add(txtNoSerie);
		txtNoSerie.setColumns(10);
		txtNoSerie.setText("N�-"+Tienda.getInstance().getGeneradorCodigoComponentes());
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(110, 69, 96, 20);
		panel_DatosGeneralesComponentes.add(txtMarca);
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		txtModelo.setBounds(110, 112, 96, 20);
		panel_DatosGeneralesComponentes.add(txtModelo);

		JPanel panel_TipoComponente = new JPanel();
		panel_TipoComponente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_TipoComponente.setBounds(10, 183, 216, 150);
		contentPanel.add(panel_TipoComponente);
		panel_TipoComponente.setLayout(null);

		
		
		rdbtnDiscoDuro = new JRadioButton("Disco Duro");
		rdbtnDiscoDuro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(true);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(true);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(false);

			}
		});
		rdbtnDiscoDuro.setBounds(6, 19, 92, 23);
		panel_TipoComponente.add(rdbtnDiscoDuro);

		rdbtnMicro = new JRadioButton("Microprocesador");
		rdbtnMicro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(true);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(true);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(false);
			}
		});
		rdbtnMicro.setBounds(6, 53, 128, 23);
		panel_TipoComponente.add(rdbtnMicro);

		rdbtnMotherBoard = new JRadioButton("Mother Board");
		rdbtnMotherBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(true);
				rdbtnRAM.setSelected(false);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(true);
				panel_RAM.setVisible(false);
			}
		});
		rdbtnMotherBoard.setBounds(6, 87, 109, 23);
		panel_TipoComponente.add(rdbtnMotherBoard);

		rdbtnRAM = new JRadioButton("RAM");
		rdbtnRAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDiscoDuro.setSelected(false);
				rdbtnMicro.setSelected(false);
				rdbtnMotherBoard.setSelected(false);
				rdbtnRAM.setSelected(true);
				panel_DiscoDuro.setVisible(false);
				panel_Micro.setVisible(false);
				panel_MotherBoard.setVisible(false);
				panel_RAM.setVisible(true);
			}
		});
		rdbtnRAM.setBounds(6, 120, 65, 23);
		panel_TipoComponente.add(rdbtnRAM);
		
		panel_RAM = new JPanel();
		panel_RAM.setVisible(false);
		
		panel_Micro = new JPanel();
		panel_Micro.setVisible(false);
		
		panel_DiscoDuro = new JPanel();
		panel_DiscoDuro.setVisible(false);
		panel_DiscoDuro.setLayout(null);
		panel_DiscoDuro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DiscoDuro.setBounds(10, 344, 424, 70);
		contentPanel.add(panel_DiscoDuro);
		
		lblAlmacenamiento = new JLabel("Almacenamiento: ");
		lblAlmacenamiento.setBounds(12, 27, 104, 14);
		panel_DiscoDuro.add(lblAlmacenamiento);
		
		lblTipoConexion = new JLabel("Tipo Conexion:");
		lblTipoConexion.setBounds(207, 27, 91, 14);
		panel_DiscoDuro.add(lblTipoConexion);
		
		cbxAlmacenamiento = new JComboBox();
		cbxAlmacenamiento.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "256 GB", "512 GB", "1 TB", "2 TB"}));
		cbxAlmacenamiento.setBounds(115, 23, 80, 22);
		panel_DiscoDuro.add(cbxAlmacenamiento);
		
		cbxTipoConexionDD = new JComboBox();
		cbxTipoConexionDD.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "IDE", "SATA", "SCSI", "SAS"}));
		cbxTipoConexionDD.setBounds(303, 23, 104, 22);
		panel_DiscoDuro.add(cbxTipoConexionDD);
		panel_DiscoDuro.setVisible(true);
		panel_Micro.setLayout(null);
		panel_Micro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Micro.setBounds(10, 344, 424, 70);
		contentPanel.add(panel_Micro);
		
		JLabel lblVelocidad = new JLabel("Velocidad:");
		lblVelocidad.setBounds(12, 28, 70, 14);
		panel_Micro.add(lblVelocidad);
		
		JLabel label_4 = new JLabel("Tipo Conexion:");
		label_4.setBounds(207, 28, 91, 14);
		panel_Micro.add(label_4);
		
		cbxTipoConexionMicro = new JComboBox();
		cbxTipoConexionMicro.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "PGA", "BGA", "LGA"}));
		cbxTipoConexionMicro.setBounds(303, 24, 104, 22);
		panel_Micro.add(cbxTipoConexionMicro);
		
		txtVelocidad = new JTextField();
		txtVelocidad.setBounds(92, 25, 65, 20);
		panel_Micro.add(txtVelocidad);
		txtVelocidad.setColumns(10);
		panel_Micro.setVisible(false);
		
		panel_MotherBoard = new JPanel();
		panel_MotherBoard.setVisible(false);
		panel_MotherBoard.setLayout(null);
		panel_MotherBoard.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_MotherBoard.setBounds(10, 344, 424, 70);
		contentPanel.add(panel_MotherBoard);
		
		JLabel lblTipoConector = new JLabel("Tipo Conector");
		lblTipoConector.setBounds(12, 27, 104, 14);
		panel_MotherBoard.add(lblTipoConector);
		
		JLabel lblTipoRam = new JLabel("Tipo RAM");
		lblTipoRam.setBounds(228, 27, 74, 14);
		panel_MotherBoard.add(lblTipoRam);
		
		cbxTipoConectorMB = new JComboBox();
		cbxTipoConectorMB.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>"}));
		cbxTipoConectorMB.setBounds(104, 23, 104, 22);
		panel_MotherBoard.add(cbxTipoConectorMB);
		
		cbxTipoRAM = new JComboBox();
		cbxTipoRAM.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>"}));
		cbxTipoRAM.setBounds(293, 23, 114, 22);
		panel_MotherBoard.add(cbxTipoRAM);
		panel_MotherBoard.setVisible(false);
		panel_RAM.setBounds(10, 344, 424, 70);
		contentPanel.add(panel_RAM);
		panel_RAM.setLayout(null);
		panel_RAM.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Especificaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 27, 49, 14);
		panel_RAM.add(lblTipo);
		
		JLabel lblCantidadDeMemoria = new JLabel("Cantidad de Memoria: ");
		lblCantidadDeMemoria.setBounds(207, 27, 142, 14);
		panel_RAM.add(lblCantidadDeMemoria);
		
		cbxTipo = new JComboBox();
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Escoja>", "DDR SDRAM", "DDR2 SDRAM", "DDR3 SDRAM ", "DDR4 SDRAM"}));
		cbxTipo.setBounds(53, 23, 127, 22);
		panel_RAM.add(cbxTipo);
		
		ftxtCantMemoria = new JFormattedTextField();
		ftxtCantMemoria.setBounds(349, 23, 58, 22);
		panel_RAM.add(ftxtCantMemoria);
		panel_RAM.setVisible(false);
		
		JPanel panel_DatosTienda = new JPanel();
		panel_DatosTienda.setLayout(null);
		panel_DatosTienda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la Tienda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DatosTienda.setBounds(230, 11, 204, 161);
		contentPanel.add(panel_DatosTienda);
		
		JLabel lblCantidadMin = new JLabel("Cantidad Min:");
		lblCantidadMin.setBounds(10, 33, 96, 14);
		panel_DatosTienda.add(lblCantidadMin);
		
		JLabel lblCantidadMax = new JLabel("Cantidad Max: ");
		lblCantidadMax.setBounds(10, 80, 96, 14);
		panel_DatosTienda.add(lblCantidadMax);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta: ");
		lblPrecioVenta.setBounds(10, 127, 96, 14);
		panel_DatosTienda.add(lblPrecioVenta);
		
		spnPrecioVenta = new JSpinner();
		spnPrecioVenta.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnPrecioVenta.setBounds(105, 125, 64, 22);
		panel_DatosTienda.add(spnPrecioVenta);
		
		spnCantMax = new JSpinner();
		spnCantMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));		
		spnCantMax.setBounds(105, 76, 64, 22);
		panel_DatosTienda.add(spnCantMax);
		
		spnCantMin = new JSpinner();
		spnCantMin.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantMin.setBounds(105, 27, 64, 22);
		panel_DatosTienda.add(spnCantMin);
		
		
		
		rdbtnDiscoDuro.setSelected(true);
		rdbtnMicro.setSelected(false);
		rdbtnMotherBoard.setSelected(false);
		rdbtnRAM.setSelected(false);
		
		panel_Proveedor = new JPanel();
		panel_Proveedor.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Proveedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Proveedor.setBounds(230, 183, 204, 150);
		contentPanel.add(panel_Proveedor);
		panel_Proveedor.setLayout(new BorderLayout(0, 0));
		
		btnAgregarVendedor = new JButton("Lista de Proveedores");
		btnAgregarVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProveedores aux = new ListaProveedores();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		panel_Proveedor.add(btnAgregarVendedor, BorderLayout.SOUTH);
		
		scrollPane = new JScrollPane();
		panel_Proveedor.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		{
			model = new DefaultTableModel();
			String[] header = {"RNC","Nombre", "Precio"};
			model.setColumnIdentifiers(header);
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setModel(model);
			scrollPane.setViewportView(table);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCrear = new JButton("Crear");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Componente aux = null;
						String numeroSerie = txtNoSerie.getText();
						String modelo = txtModelo.getText();
						String marca = txtMarca.getText();
						int cantMax = new Integer(spnCantMax.getValue().toString());  
						int cantMin = new Integer(spnCantMin.getValue().toString());
						float precioVentaI = new Float(spnPrecioVenta.getValue().toString());
						String capacidadAlma = cbxAlmacenamiento.getSelectedItem().toString();
						String tipoConexionDD = cbxTipoConexionDD.getSelectedItem().toString();
						String tipoConexionMicro = cbxTipoConexionMicro.getSelectedItem().toString();
						String tipoConectorMB = cbxTipoConectorMB.getSelectedItem().toString();
						String tipoRAM = cbxTipoRAM.getSelectedItem().toString();
						String tipo = cbxTipo.getSelectedItem().toString();
						String velocidad = txtVelocidad.getText();
						String cantMemoria = ftxtCantMemoria.getText();
						
						
						if (rdbtnDiscoDuro.isSelected()) {
							//aux = new DiscoDuro(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, capacidadAlma, tipoConexionDD);
						}
						if (rdbtnMicro.isSelected()) {
							//aux = new Micro(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, tipoConexionMicro, velocidad);							
						}
						if (rdbtnMotherBoard.isSelected()) {
							//aux = new MotherBoard(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, tipoConectorMB, tipoRAM);
						}
						if (rdbtnRAM.isSelected()) {
							//aux = new RAM(numeroSerie, marca, modelo, cantMin, cantMax, precioVentaI, cantMemoria, tipo);							
						}
						Tienda.getInstance().agregarComponente(aux);
						clean();
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);			}
		}
		
		if(b != true) {
			panel_Proveedor.setEnabled(b);
			btnAgregarVendedor.setEnabled(b);
		}
		
	}
	
	public static void cargarProveedores() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

		for (Proveedor aux : Tienda.getInstance().getLosProveedores()) {
			row[0] = aux.getCedula();
			row[1] = aux.getNombre();
			row[2] = aux.getPreciosCompos();
			model.addRow(row);
		}
	}
	
	private void clean() {
		txtNoSerie.setText("N�-"+Tienda.getInstance().getGeneradorCodigoComponentes());
		txtModelo.setText("");
		txtMarca.setText("");
		spnCantMax.setValue(1);  
		spnCantMin.setValue(1);
		spnPrecioVenta.setValue(1);
		cbxAlmacenamiento.setSelectedIndex(0);
		cbxTipoConexionDD.setSelectedIndex(0);;
		cbxTipoConexionMicro.setSelectedIndex(0);
		cbxTipoConectorMB.setSelectedIndex(0);
		cbxTipoRAM.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
		txtVelocidad.setText("");
		ftxtCantMemoria.setText("");
		
	}
}