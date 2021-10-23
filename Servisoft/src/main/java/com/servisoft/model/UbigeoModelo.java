/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de ubigeo.
 */

public class UbigeoModelo {

	private String codigo;
	
	private String departamento;
	
	private String provincia;
	
	private String distrito;

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	/**
	 * 
	 */
	public UbigeoModelo() {
		super();
	}

	/**
	 * @param codigo
	 * @param departamento
	 * @param provincia
	 * @param distrito
	 */
	public UbigeoModelo(String codigo, String departamento, String provincia, String distrito) {
		super();
		this.codigo = codigo;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
	}

	@Override
	public String toString() {
		return "UbigeoModelo [codigo=" + codigo + ", departamento=" + departamento + ", provincia=" + provincia
				+ ", distrito=" + distrito + "]";
	}
}