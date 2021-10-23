/**
 * 
 */
package com.servisoft.impl.dao;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.util.SystemOutLogger;

import com.servisoft.bean.LoginBean;
import com.servisoft.impl.Conexion;
import com.servisoft.impl.LoginImpl;
import com.servisoft.model.ClienteModelo;

/**
 * @author chema Dao de Login.
 */
public class LoginDao extends Conexion implements LoginImpl {

	@Override
	public void newPassword(String correo) throws Exception {
		String sql = "UPDATE CLIENTE SET PSWCLI = ? WHERE MAILCLI = ?";
		String[] symbols = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "~", "`", "!", "@", "#", "$", "%", "^",
				"&", "*", "(", ")", "-", "_", "=", "+", "[", "{", "]", "}", "|", ";", ":", ",", "<", ".", ">", "/",
				"?" };
		int length = (int) (Math.random() * (16 - 8)) + 8;
		Random random = SecureRandom.getInstanceStrong();
		StringBuilder sb = new StringBuilder(length);
		try {
			for (int i = 0; i < length; i++) {
				int indexRandom = random.nextInt(symbols.length);
				sb.append(symbols[indexRandom]);
			}
			String password = sb.toString();
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, correo);
			ps.executeUpdate();
			
			String sql2 = "SELECT USERCLI, NOMCLI, APECLI FROM CLIENTE WHERE MAILCLI = '" + correo + "'";
			Statement st = Conexion.conectar().createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while (rs.next()) {
				String user = rs.getString("USERCLI");
				String nombre = rs.getString("NOMCLI");
				String apellido = rs.getString("APECLI");

				System.out.println(
						"Se enviará el usuario: " + user + ", el nombre: " + nombre + " y el apellido: " + apellido);

				String remitente = "fernandocanales554@gmail.com";
				String clave = "Konosub4";

				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.user", remitente);
				props.put("mail.smtp.clave", clave);

				Session sesion = Session.getDefaultInstance(props);
				MimeMessage mensaje = new MimeMessage(sesion);

				mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
				mensaje.setSubject("Recuperación de contraseña - Servisoft");
				mensaje.setText(	"Hola" + nombre + " " + apellido
								+ 	"\n\n"
								+ 	"Hemos recibido tu mensaje para recuperar tu contraseña."
								+ 	"\n\n"
								+ 	"Tus nuevos datos de acceso son:"
								+ 	"\n\n"
								+ 	"	Usuario: "+ user
								+	"	Contraseña: "+ password
								+ 	"\n\n"
								+ 	"Que tengas un buen día, saludos.");

				Transport transport = sesion.getTransport("smtp");
				transport.connect("smtp.gmail.com", remitente, clave);
				transport.sendMessage(mensaje, mensaje.getAllRecipients());
				transport.close();
			}
			ps.close();
			rs.close();
			st.close();
		} catch (Exception e) {
			System.out.println("LoginDao-Error en newPassword: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public boolean checkLogin(String user, String password) throws Exception {

		String sql = "SELECT COUNT(*) AS OPC FROM CLIENTE WHERE USERCLI = '" + user + "' AND PSWCLI = '" + password
				+ "'";

		try {
			Statement st = Conexion.conectar().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int n = rs.getInt("OPC");
				if (n == 0) {
					LoginBean.intentos++;
					if (LoginBean.intentos == 1) {
						LoginBean.intentos = 1;
					}
					if (LoginBean.intentos == 2) {
						LoginBean.intentos = 2;
					}
					if (LoginBean.intentos == 3) {
						LoginBean.intentos = 4;
						if (LoginBean.intentos == 4) {
							LoginBean.intentos = 0;
						}
						return true;
					}
				} else if (n == 1) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("faces/vistas/Principal.xhtml");
				}
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.out.println("LoginDao-Error en checkLogin: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return false;
	}

	@Override
	public void checkUser(String user) throws Exception {

		String sql = "SELECT COUNT(*) AS S FROM CLIENTE WHERE USERCLI = LOWER( '" + user + "' )";

		try {
			Statement st = Conexion.conectar().createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				rs.getInt("S");

				int n = rs.getInt("S");

				System.out.println(n);

				if (n == 1) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario encontrado", null));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado", null));
				}
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.out.println("LoginDao-Error en checkUser: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}
}