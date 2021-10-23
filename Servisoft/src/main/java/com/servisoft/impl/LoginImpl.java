/**
 * 
 */
package com.servisoft.impl;

import com.servisoft.model.ClienteModelo;

/**
 * @author chema
 * Interface de Login.
 */
public interface LoginImpl {
	
	public boolean checkLogin(String user, String password) throws Exception;
	
	public void newPassword(String correo) throws Exception;
	
	public void checkUser(String user) throws Exception;
}