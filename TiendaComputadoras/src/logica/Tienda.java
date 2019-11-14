package logica;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Tienda {
	private ArrayList<Componente> losComponentes;
	private ArrayList <Cliente> losClientes;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Combo> losCombo;
	private ArrayList<Proveedor> losProveedores;
	private ArrayList<OrdenCompra> lasOrdenes;
	private ArrayList<OrdenCompra> ordenesSinProcesar;
	private int generadorCodigoCombo;
	private int generadorCodigoFactura;
	private int generadorCodigoComponentes;
	private static Tienda tienda=null;

	private Tienda() {
		super();
		losComponentes = new ArrayList<>();
		losClientes = new ArrayList<>();
		lasFacturas = new ArrayList<>();
		losCombo = new ArrayList<>();
		losProveedores = new ArrayList<>();
		lasOrdenes = new ArrayList<>();
		ordenesSinProcesar = new ArrayList<>();
		generadorCodigoCombo = 1;
		generadorCodigoFactura = 1;
		generadorCodigoComponentes = 1;
	}

	public static Tienda getInstance() {
		if(tienda==null){
			tienda= new Tienda();
		}
		return tienda;
	}

	public ArrayList<Componente> getLosComponentes() {
		return losComponentes;
	}

	public void setLosComponentes(ArrayList<Componente> losComponentes) {
		this.losComponentes = losComponentes;
	}

	public ArrayList<Cliente> getLosClientes() {
		return losClientes;
	}

	public void setLosClientes(ArrayList<Cliente> losClientes) {
		this.losClientes = losClientes;
	}

	public ArrayList<Factura> getLasFacturas() {
		return lasFacturas;
	}

	public void setLasFacturas(ArrayList<Factura> lasFacturas) {
		this.lasFacturas = lasFacturas;
	}

	public ArrayList<Combo> getLosCombo() {
		return losCombo;
	}

	public void setElCombo(ArrayList<Combo> elCombo) {
		this.losCombo = elCombo;
	}

	public ArrayList<Proveedor> getLosProveedores() {
		return losProveedores;
	}

	public void setLosProveedores(ArrayList<Proveedor> losProveedores) {
		this.losProveedores = losProveedores;
	}

	public ArrayList<OrdenCompra> getLasOrdenes() {
		return lasOrdenes;
	}

	public void setLasOrdenes(ArrayList<OrdenCompra> lasOrdenes) {
		this.lasOrdenes = lasOrdenes;
	}

	public int getGeneradorCodigoCombo() {
		return generadorCodigoCombo;
	}

	public void setGeneradorCodigoCombo(int generadorCodigoCombo) {
		this.generadorCodigoCombo = generadorCodigoCombo;
	}

	public int getGeneradorCodigoFactura() {
		return generadorCodigoFactura;
	}

	public void setGeneradorCodigoFactura(int generadorCodigoFactura) {
		this.generadorCodigoFactura = generadorCodigoFactura;
	}

	public int getGeneradorCodigoComponentes() {
		return generadorCodigoComponentes;
	}

	public void setGeneradorCodigoComponentes(int generadorCodigoComponentes) {
		this.generadorCodigoComponentes = generadorCodigoComponentes;
	}

	public Cliente findClientebyCedula(String cedula) {
		Cliente clienteFound = null;
		boolean find = false;
		int i=0;
		while (i<losClientes.size()&&!find) {
			if(losClientes.get(i).getCedula().equalsIgnoreCase(cedula)){
				clienteFound = losClientes.get(i);
				find = true;
			}
			i++;
		}
		return clienteFound;
	}
	
	public Componente findComponentebyNumeroSerie(String NumeroSerie) {
		Componente componenteFound = null;
		boolean find = false;
		int i=0;
		while (i<losComponentes.size()&&!find) {
			if(losComponentes.get(i).getNumeroSerie().equalsIgnoreCase(NumeroSerie)){
				componenteFound = losComponentes.get(i);
				find = true;
			}
			i++;
		}
		return componenteFound;
	}
	
	public int cantComponentes(ArrayList<Componente> aux, ArrayList<Integer> cant1) {
		int cant = 0;
		int cant2 = 0;
		if(!aux.isEmpty()) {
			for (Componente componente : aux) {
				cant2 = cant1.get(aux.lastIndexOf(componente));
				cant += cant2;
			}
		}
		return cant;
	}
	
	public int cantCombos(ArrayList<Combo> aux, ArrayList<Integer> cant1) {
		int cant = 0;
		int cant2 = 0;
		if(!aux.isEmpty()) {
			for (Combo combo : aux) {
				cant2 = cant1.get(aux.lastIndexOf(combo));
				cant += cant2;
			}
		}
		return cant;
	}

	public float precioTotalComponentes(ArrayList<Componente> aux ) {
		float precioTotal = 0;
		if(!aux.isEmpty()) {
			for (Componente componente : aux) {
				precioTotal += componente.getPrecioVentaActual();
			}
		}
		return precioTotal;
	}
	public float precioTotalCombos(ArrayList<Combo> aux ) {
		float precioTotal = 0;
		if(!aux.isEmpty()) {	
			for (Combo combo : aux) {
				precioTotal += combo.precioCombo();
			}
		}
		return precioTotal;
	}
	public float costoFactura(ArrayList<Componente> losComponentes,ArrayList<Combo> losCombos) {
		float total=precioTotalCombos(losCombos)+precioTotalComponentes(losComponentes);
		return total;
	}

	public void pagoDeuda(String cedulaCliente, float monto) {
		Cliente cliente1=findClientebyCedula(cedulaCliente);
		if (cliente1!=null) {
			cliente1.setCredito(cliente1.getCredito()-monto);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Este cliente no existe");
		}
	}

	public void restaCantiComponentes(ArrayList<Componente> componentes, ArrayList<Integer> cantiCompo) {
		int i=0;
		for (Componente componente : componentes) {
			componente.setCantDisponible(componente.getCantDisponible()-cantiCompo.get(i));
			i++;
		}
	}
	public boolean relacionFactura(Cliente elCliente,float precio, ArrayList<Componente> misComponentes, ArrayList<Integer> cantComponentes,ArrayList<Combo> misCombos, ArrayList<Integer> cantCombos, boolean tipo) {
		boolean cantidad=true;
		boolean facturar=false;
		boolean limite= true;
		
		for (Componente elComponente : misComponentes) {
			if(elComponente.getCantDisponible() < cantComponentes.get(misComponentes.lastIndexOf(elComponente))) {
				cantidad=false;
			}
		}
		if(tipo==true) {
			if(elCliente.getLimCredito() < precio+ elCliente.getCredito()) {
				limite=false;
			}
		}
		if(cantidad==true && limite==true) {
			facturar= true;
		}
		return facturar;
		
	}
	public void hacerCompra(OrdenCompra orden,Proveedor aux) {
		orden.getCompCompra().getPrecios().add(new Precio(orden.getCompCompra().getPrecioVentaActual(), aux.getPreciosCompos().get(aux.getMisCompos().lastIndexOf(orden.getCompCompra())), false));
		aux.setDebito(orden.getCantiCompos()*aux.getPreciosCompos().get(aux.getMisCompos().lastIndexOf(orden.getCompCompra())));
		orden.setRealizada(true);
		orden.getCompCompra().setCantDisponible(orden.getCompCompra().getCantDisponible()+orden.getCantiCompos());
		lasOrdenes.add(orden);
		ordenesSinProcesar.remove(orden);
		
	}
	
	public void comboMas(Combo combo, int cantidad) {
		combo.setCantidad(combo.getCantidad()+cantidad);
		for (Componente componente : combo.getComponentes()) {
			componente.setCantDisponible(componente.getCantDisponible()-cantidad);
			if(componente.getCantDisponible()<componente.getCantMin()) {
				OrdenCompra aux = new OrdenCompra(componente, componente.getCantMax()-componente.getCantDisponible());
				Tienda.getInstance().getOrdenesSinProcesar().add(aux);
			}
		}
	}

	public ArrayList<OrdenCompra> getOrdenesSinProcesar() {
		return ordenesSinProcesar;
	}

	public void setOrdenesSinProcesar(ArrayList<OrdenCompra> ordenesSinProcesar) {
		this.ordenesSinProcesar = ordenesSinProcesar;
	}
	
}