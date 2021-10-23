/**
 * 
 */
package com.servisoft.impl;

import java.util.List;

import com.servisoft.model.UbigeoModelo;

/**
 * @author chema
 * Interface de Ubigeo.
 */
public interface UbigeoImpl {
	
	public List<UbigeoModelo> listar() throws Exception;
}