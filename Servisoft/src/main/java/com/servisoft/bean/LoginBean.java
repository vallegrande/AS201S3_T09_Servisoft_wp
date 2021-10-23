/**
 * 
 */
package com.servisoft.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.servisoft.impl.dao.ClienteDao;
import com.servisoft.impl.dao.LoginDao;
import com.servisoft.model.ClienteModelo;

/**
 * @author chema
 * controlador de Login.
 */

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	
	private ClienteModelo modelo = new ClienteModelo();
	private LoginDao dao = new LoginDao();
	
	public static int intentos = 0;
	private boolean bloquear;
	private int value = 3;
	
	public static boolean listar = false;
	
	public void validarLogin() {
		try {
			String user = modelo.getUsuario();
			String password = modelo.getPassword();
			
			System.out.println("Bloquear "+ bloquear);
			
			if(dao.checkLogin(user, password) == true) {
				bloquear = true;
				delaySegundo();
			}else {
				bloquear = false;
			}
		} catch (Exception e) {
			System.out.println("LoginBean-Error en validarLogin: "+ e.getMessage());
		}
	}
	
	private static void delaySegundo() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void validarUser() {
		try {
			String user = modelo.getUsuario();
			System.out.println("El usuario que está listando es: "+ user);
			dao.checkUser(user);
		} catch (Exception e) {
			System.out.println("LoginBean-Error en validarUser: "+ e.getMessage());
		}
	}
	
	public void chancePswd() {
		try {
			String correo = modelo.getCorreo();
			dao.newPassword(correo);
		} catch (Exception e) {
			System.out.println("LoginBean-Error al cambiar contraseña: "+ e.getMessage());
		}
	}
	
	public void validarCaracteres() {
		try {
			String password = modelo.getPassword();
			System.out.println(password);
			
			if(password.length() > 8 && password.length() < 15) {
				System.out.println("Puedes validarlo");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña válida", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Contraseña inválida", ""));
			}
		} catch (Exception e) {
			System.out.println("LoginBean-Errror al validar cantidad de caractéres");
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
	public LoginDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(LoginDao dao) {
		this.dao = dao;
	}

	/**
	 * @return the listar
	 */
	public static boolean isListar() {
		return listar;
	}

	/**
	 * @param listar the listar to set
	 */
	public static void setListar(boolean listar) {
		LoginBean.listar = listar;
	}

	/**
	 * @return the intentos
	 */
	public int getIntentos() {
		return intentos;
	}

	/**
	 * @param intentos the intentos to set
	 */
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	/**
	 * @return the bloquear
	 */
	public boolean isBloquear() {
		return bloquear;
	}

	/**
	 * @param bloquear the bloquear to set
	 */
	public void setBloquear(boolean bloquear) {
		this.bloquear = bloquear;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
}