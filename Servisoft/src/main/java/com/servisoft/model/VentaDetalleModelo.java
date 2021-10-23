/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de Venta Detalle.
 */
public class VentaDetalleModelo {
	private int codigo;

	private int cant;

	private int codProd;

	private int codVent;

	private ProductoModelo producto;

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
	 * @return the cant
	 */
	public int getCant() {
		return cant;
	}

	/**
	 * @param cant the cant to set
	 */
	public void setCant(int cant) {
		this.cant = cant;
	}

	/**
	 * @return the codProd
	 */
	public int getCodProd() {
		return codProd;
	}

	/**
	 * @param codProd the codProd to set
	 */
	public void setCodProd(int codProd) {
		this.codProd = codProd;
	}

	/**
	 * @return the codVent
	 */
	public int getCodVent() {
		return codVent;
	}

	/**
	 * @param codVent the codVent to set
	 */
	public void setCodVent(int codVent) {
		this.codVent = codVent;
	}

	/**
	 * @return the producto
	 */
	public ProductoModelo getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ProductoModelo producto) {
		this.producto = producto;
	}

	/**
	 * 
	 */
	public VentaDetalleModelo() {
		super();
	}

	/**
	 * @param codigo
	 * @param cant
	 * @param codProd
	 * @param codVent
	 * @param producto
	 */
	public VentaDetalleModelo(int codigo, int cant, int codProd, int codVent, ProductoModelo producto) {
		super();
		this.codigo = codigo;
		this.cant = cant;
		this.codProd = codProd;
		this.codVent = codVent;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "VentaDetalleModelo [codigo=" + codigo + ", cant=" + cant + ", codProd=" + codProd + ", codVent="
				+ codVent + ", producto=" + producto + "]";
	}
}