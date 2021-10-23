/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de Venta.
 */
public class VentaModelo {

	private int codigo;

	private String fecha;

	private String tipo;

	private int codVend;

	private int codCli;
	
	private String cliente;
	
	private int total;

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
	 * @return the codVend
	 */
	public int getCodVend() {
		return codVend;
	}

	/**
	 * @param codVend the codVend to set
	 */
	public void setCodVend(int codVend) {
		this.codVend = codVend;
	}

	/**
	 * @return the codCli
	 */
	public int getCodCli() {
		return codCli;
	}

	/**
	 * @param codCli the codCli to set
	 */
	public void setCodCli(int codCli) {
		this.codCli = codCli;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 
	 */
	public VentaModelo() {
		super();
	}

	/**
	 * @param codigo
	 * @param fecha
	 * @param tipo
	 * @param codVend
	 * @param codCli
	 * @param cliente
	 * @param total
	 */
	public VentaModelo(int codigo, String fecha, String tipo, int codVend, int codCli, String cliente, int total) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.tipo = tipo;
		this.codVend = codVend;
		this.codCli = codCli;
		this.cliente = cliente;
		this.total = total;
	}

	@Override
	public String toString() {
		return "VentaModelo [codigo=" + codigo + ", fecha=" + fecha + ", tipo=" + tipo + ", codVend=" + codVend
				+ ", codCli=" + codCli + ", cliente=" + cliente + ", total=" + total + "]";
	}
}