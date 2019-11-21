package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Combo;
import logica.Componente;
import logica.Tienda;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaCombos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private String codigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaCombos dialog = new ListaCombos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaCombos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel();
					String[] encabezado = {"Codigo","Cantidad Disponible","Descuento","Precio"};
					model.setColumnIdentifiers(encabezado);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							int index = table.getSelectedRow();
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
							codigo = String.valueOf(table.getValueAt(index, 0));
						}
					});
					scrollPane.setViewportView(table);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					/*	NuevoCombo aux = new NuevoCombo(Tienda.getInstance().findClientebyCedula(codigo));
						aux.setModal(true);
						aux.setVisible(true);
						cargarClientes();*/
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Combo aux = Tienda.getInstance().findCombobyCodigo(codigo);
						for (Componente componente : aux.getComponentes()) {
							componente.setCantDisponible(componente.getCantDisponible()+aux.getCantidad());
						}
						Tienda.getInstance().getLosCombo().remove(aux);
						cargarCombos();
					}
				});
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
			}
		}
		cargarCombos();
	}

	public static void cargarCombos() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];

		for (Combo combo : Tienda.getInstance().getLosCombo()) {
			row[0] = combo.getCodigo();
			row[1] = combo.getCantidad();
			row[2] = combo.getDescuento()+"%";
			row[3] = combo.precioCombo();
			model.addRow(row);
		}

	}

}