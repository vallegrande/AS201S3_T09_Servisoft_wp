/**
 * 
 */
package com.servisoft.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.*;

import org.primefaces.event.FlowEvent;

import com.servisoft.impl.dao.ClienteDao;
import com.servisoft.impl.dao.UbigeoDao;
import com.servisoft.model.ClienteModelo;
import com.servisoft.model.UbigeoModelo;

/**
 * @author chema
 * Controlador de Clientes.
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

	private ClienteModelo modelo = new ClienteModelo();
	private ClienteDao dao = new ClienteDao();
	private List<ClienteModelo> listarCli = new ArrayList<>();

	private UbigeoDao daoUbi = new UbigeoDao();
	private List<UbigeoModelo> listarUbi = new ArrayList<>();

	private ClienteModelo selectedClient = new ClienteModelo();

	private boolean skip;

	@PostConstruct
	public void construir() {
		try {
			listar();
		} catch (Exception e) {
			System.out.println("ClienteBean-Error en el constructor: " + e.getMessage());
		}
	}

	public void listar() {
		try {
			listarCli = dao.listar();
			listarUbi = daoUbi.listar();
		} catch (Exception e) {
			System.out.println("ClienteBean-Error en Listar: " + e.getMessage());
		}
	}

	public void registrar() {
		try {
			dao.registrar(modelo);
			FacesMessage msg = new FacesMessage("Successful",
					"Bienvenido: " + modelo.getApellido() + ", " + modelo.getNombre());
			FacesContext.getCurrentInstance().addMessage(null, msg);

			String accountUser = modelo.getUsuario();
			String accountPswd = modelo.getPassword();
			String remitente = "fernandocanales554@gmail.com";
			String clave = "Konosub4";
			String destino = modelo.getCorreo();

			System.out.println("Correo: " + remitente + ", Usuario: " + accountUser + ", Contraseña: " + accountPswd);
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.user", remitente);
			props.put("mail.smtp.clave", clave);

			Session sesion = Session.getDefaultInstance(props);
			MimeMessage mensaje = new MimeMessage(sesion);

			try {
				mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
				mensaje.setSubject("Bienvenido a Servisoft");
				mensaje.setText(	"Hola " + modelo.getNombre() + " " + modelo.getApellido()
								+ 	"\n\n"
								+ 	"Gracias por registrarte a Servisoft, mediante este correo te enviamos tus datos de acceso."
								+ 	"\n\n"
								+   "USUARIO: "+ accountUser
								+ 	"\n\n"
								+   "CONTRASEÑA: "+ accountPswd
								+ 	"\n\n"
								+   "Ya puedes acceder a nuestro portal y hacer tus pedidos de vinos y piscos.");

				Transport transport = sesion.getTransport("smtp");
				transport.connect("smtp.gmail.com", remitente, clave);
				transport.sendMessage(mensaje, mensaje.getAllRecipients());
				transport.close();

				System.out.println("El correo ha sido enviado");
				FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
			} catch (Exception e) {
				System.out.println("Error en Mail: " + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Cliente-Error en registrar: " + e.getMessage());
		}
	}

	public void eliminar() {
		try {
			dao.eliminar(modelo);
			listar();
		} catch (Exception e) {
			System.out.println("Cliente-Error en eliminar: " + e.getMessage());
		}
	}

	public void modificar() {
		try {
			System.out.println(modelo.getCodigo());
			dao.modificar(modelo);
			System.out.println(modelo);
			listar();
		} catch (Exception e) {
			System.out.println("Cliente-Error en modificar: " + e.getMessage());
		}
	}

	public void mostrarDatosDNI() throws IOException, InterruptedException {
		try {
			dao.buscarDNI(modelo);
		} catch (Exception e) {
			System.out.println("Cliente-Error en MostrarDNI: " + e.getMessage());
		}
	}

	public void mostrarDatosRUC() throws IOException, InterruptedException {
		try {
			dao.buscarRUC(modelo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void validarCorreo(){
		String email = modelo.getCorreo();
		Pattern pt = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mt = pt.matcher(email);
		if(mt.find() == true) {
			System.out.println("El correo es válido");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "El correo es válido"));
		} else {
			System.out.println("El correo no es válido");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "FATAL", "El correo es inválido"));
		}
	}

	public void datos() {
		this.modelo = selectedClient;
		System.out.println(selectedClient);
	}

	public void limpiar() {
		modelo = new ClienteModelo();
	}

	public void comment() {
		try {
			String msg;
			msg = modelo.getComment();
			System.out.println(msg);
			dao.sendMsgSlack(msg);
			limpiar();
		} catch (Exception e) {
			System.out.println("ClienteBean-Error en comment: "+ e.getMessage());
		}
	}

	/**
	 * @return the modelo
	 */
	public ClienteModelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(ClienteModelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the dao
	 */
	public ClienteDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(ClienteDao dao) {
		this.dao = dao;
	}

	/**
	 * @return the listarCli
	 */
	public List<ClienteModelo> getListarCli() {
		return listarCli;
	}

	/**
	 * @param listarCli the listarCli to set
	 */
	public void setListarCli(List<ClienteModelo> listarCli) {
		this.listarCli = listarCli;
	}

	/**
	 * @return the skip
	 */
	public boolean isSkip() {
		return skip;
	}

	/**
	 * @param skip the skip to set
	 */
	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	/**
	 * @return the listarUbi
	 */
	public List<UbigeoModelo> getListarUbi() {
		return listarUbi;
	}

	/**
	 * @param listarUbi the listarUbi to set
	 */
	public void setListarUbi(List<UbigeoModelo> listarUbi) {
		this.listarUbi = listarUbi;
	}

	/**
	 * @return the daoUbi
	 */
	public UbigeoDao getDaoUbi() {
		return daoUbi;
	}

	/**
	 * @param daoUbi the daoUbi to set
	 */
	public void setDaoUbi(UbigeoDao daoUbi) {
		this.daoUbi = daoUbi;
	}

	/**
	 * @return the selectedClient
	 */
	public ClienteModelo getSelectedClient() {
		return selectedClient;
	}

	/**
	 * @param selectedClient the selectedClient to set
	 */
	public void setSelectedClient(ClienteModelo selectedClient) {
		this.selectedClient = selectedClient;
	}
}