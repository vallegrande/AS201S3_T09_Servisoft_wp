/**
 * 
 */
package com.servisoft.impl;

import com.servisoft.model.VentaDetalleModelo;
import com.servisoft.model.VentaModelo;

/**
 * @author chema
 * Interface de venta.
 */
public interface VentaImpl {
	
	public void registrar(VentaModelo vent) throws Exception;
	
	public void registrarVD(VentaDetalleModelo ventdet) throws Exception;
	
	public int obtenerCodVent();
}