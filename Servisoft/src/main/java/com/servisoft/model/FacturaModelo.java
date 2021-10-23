/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de Factura.
 */
public class FacturaModelo {
	
	private int codvent;
	
	private String cliente;
	
	private String fecha;
	
	private int totCompra;

	/**
	 * @return the codvent
	 */
	public int getCodvent() {
		return codvent;
	}

	/**
	 * @param codvent the codvent to set
	 */
	public void setCodvent(int codvent) {
		this.codvent = codvent;
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
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
	 * @return the totCompra
	 */
	public int getTotCompra() {
		return totCompra;
	}

	/**
	 * @param totCompra the totCompra to set
	 */
	public void setTotCompra(int totCompra) {
		this.totCompra = totCompra;
	}

	/**
	 * 
	 */
	public FacturaModelo() {
	}

	/**
	 * @param codvent
	 * @param cliente
	 * @param fecha
	 * @param totCompra
	 */
	public FacturaModelo(int codvent, String cliente, String fecha, int totCompra) {
		super();
		this.codvent = codvent;
		this.cliente = cliente;
		this.fecha = fecha;
		this.totCompra = totCompra;
	}

	@Override
	public String toString() {
		return "FacturaModelo [codvent=" + codvent + ", cliente=" + cliente + ", fecha=" + fecha + ", totCompra="
				+ totCompra + "]";
	}
}