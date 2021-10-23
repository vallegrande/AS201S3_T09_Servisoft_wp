/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de Clientes.
 */

public class ClienteModelo {

	private int codigo;
	
	private String nombre;

	private String apellido;

	private String celular;

	private String dni;

	private String correo;

	private String direc;

	private String estado;

	private String usuario;

	private String password;

	private String codubi;

	private String ruc;

	private String razsoc;
	
	private String comment;

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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the direc
	 */
	public String getDirec() {
		return direc;
	}

	/**
	 * @param direc the direc to set
	 */
	public void setDirec(String direc) {
		this.direc = direc;
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
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the codubi
	 */
	public String getCodubi() {
		return codubi;
	}

	/**
	 * @param codubi the codubi to set
	 */
	public void setCodubi(String codubi) {
		this.codubi = codubi;
	}

	/**
	 * @return the ruc
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * @param ruc the ruc to set
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	/**
	 * @return the razsoc
	 */
	public String getRazsoc() {
		return razsoc;
	}

	/**
	 * @param razsoc the razsoc to set
	 */
	public void setRazsoc(String razsoc) {
		this.razsoc = razsoc;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 */
	public ClienteModelo() {
		super();
	}

	/**
	 * @param codigo
	 * @param nombre
	 * @param apellido
	 * @param celular
	 * @param dni
	 * @param correo
	 * @param direc
	 * @param estado
	 * @param usuario
	 * @param password
	 * @param codubi
	 * @param ruc
	 * @param razsoc
	 * @param comment
	 */
	public ClienteModelo(int codigo, String nombre, String apellido, String celular, String dni, String correo,
			String direc, String estado, String usuario, String password, String codubi, String ruc, String razsoc,
			String comment) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.dni = dni;
		this.correo = correo;
		this.direc = direc;
		this.estado = estado;
		this.usuario = usuario;
		this.password = password;
		this.codubi = codubi;
		this.ruc = ruc;
		this.razsoc = razsoc;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ClienteModelo [codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", celular="
				+ celular + ", dni=" + dni + ", correo=" + correo + ", direc=" + direc + ", estado=" + estado
				+ ", usuario=" + usuario + ", password=" + password + ", codubi=" + codubi + ", ruc=" + ruc
				+ ", razsoc=" + razsoc + ", comment=" + comment + "]";
	}
}