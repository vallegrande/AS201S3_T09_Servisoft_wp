/**
 * 
 */
package com.servisoft.impl;

import java.util.List;

import com.servisoft.model.ClienteModelo;

/**
 * @author chema
 * Interface de Cliente.
 */
public interface ClienteImpl {
	
	public void registrar(ClienteModelo cli) throws Exception;
	
	public void modificar(ClienteModelo cli) throws Exception;
	
	public void eliminar(ClienteModelo cli) throws Exception;
	
	public List<ClienteModelo> listar() throws Exception;
	
	public void buscarDNI(ClienteModelo cli) throws Exception;
	
	public void buscarRUC(ClienteModelo cli) throws Exception;
}