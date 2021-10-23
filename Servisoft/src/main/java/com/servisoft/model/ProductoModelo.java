package com.servisoft.model;

/**
 * @author chema
 * Modelo de Productos.
 */

public class ProductoModelo {

	private int codigo;

	private String nombre;

	private String fecha;

	private long precio;

	private String tipo;

	private String tam;

	private int stock;

	private String estado;

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the precio
	 */
	public long getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(long precio) {
		this.precio = precio;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tam
	 */
	public String getTam() {
		return tam;
	}

	/**
	 * @param tam the tam to set
	 */
	public void setTam(String tam) {
		this.tam = tam;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * 
	 */
	public ProductoModelo() {
	}

	/**
	 * @param codigo
	 * @param nombre
	 * @param fecha
	 * @param precio
	 * @param tipo
	 * @param tam
	 * @param stock
	 * @param estado
	 */
	public ProductoModelo(int codigo, String nombre, String fecha, long precio, String tipo, String tam, int stock,
			String estado) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.precio = precio;
		this.tipo = tipo;
		this.tam = tam;
		this.stock = stock;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ProductoModelo [codigo=" + codigo + ", nombre=" + nombre + ", fecha=" + fecha + ", precio=" + precio
				+ ", tipo=" + tipo + ", tam=" + tam + ", stock=" + stock + ", estado=" + estado + "]";
	}
}