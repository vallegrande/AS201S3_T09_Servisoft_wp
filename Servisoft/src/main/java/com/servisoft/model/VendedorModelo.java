/**
 * 
 */
package com.servisoft.model;

/**
 * @author chema
 * Modelo de Vendedor.
 */
public class VendedorModelo {

	private int codigo;

	private String fecha;

	private String nombre;

	private String apellido;

	private String celular;
	
	private String usuario;
	
	private String password;
	
	private String dni;
	
	private String mail;
	
	private String estado;
	
	private String direc;
	
	private int codUbi;

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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the codUbi
	 */
	public int getCodUbi() {
		return codUbi;
	}

	/**
	 * @param codUbi the codUbi to set
	 */
	public void setCodUbi(int codUbi) {
		this.codUbi = codUbi;
	}

	/**
	 * 
	 */
	public VendedorModelo() {
		super();
	}

	/**
	 * @param codigo
	 * @param fecha
	 * @param nombre
	 * @param apellido
	 * @param celular
	 * @param usuario
	 * @param password
	 * @param dni
	 * @param mail
	 * @param estado
	 * @param direc
	 * @param codUbi
	 */
	public VendedorModelo(int codigo, String fecha, String nombre, String apellido, String celular, String usuario,
			String password, String dni, String mail, String estado, String direc, int codUbi) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.usuario = usuario;
		this.password = password;
		this.dni = dni;
		this.mail = mail;
		this.estado = estado;
		this.direc = direc;
		this.codUbi = codUbi;
	}

	@Override
	public String toString() {
		return "VendedorModelo [codigo=" + codigo + ", fecha=" + fecha + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", celular=" + celular + ", usuario=" + usuario + ", password=" + password + ", dni=" + dni
				+ ", mail=" + mail + ", estado=" + estado + ", direc=" + direc + ", codUbi=" + codUbi + "]";
	}
}