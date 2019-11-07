package logica;

import java.util.ArrayList;

public class Combo {
	private ArrayList<Componente> componentes;
	private String codigo;
	private float precio;
	private int descuento;
	
	public Combo(ArrayList<Componente> componentes, String codigo, float precio, int descuento) {
		super();
		this.componentes = componentes;
		this.codigo = codigo;
		this.precio = precio;
		this.descuento = descuento;
	}

	public ArrayList<Componente> getComponentes() {
		return componentes;
	}

	public String getCodigo() {
		return codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setComponentes(ArrayList<Componente> componentes) {
		this.componentes = componentes;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	
}
