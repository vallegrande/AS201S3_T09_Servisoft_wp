/**
 * 
 */
package com.servisoft.impl;

import java.util.List;

import com.servisoft.model.VendedorModelo;

/**
 * @author chema
 * Interface de vendedor.
 */
public interface VendedorImpl {
	
	public List<VendedorModelo> listarVendedor() throws Exception;
}