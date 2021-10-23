package com.servisoft.impl;

import com.servisoft.model.ProductoModelo;
import java.util.List;

/**
 * @author chema
 * Interface de Producto.
 */
public interface ProductoImpl {
	
	public void registrar(ProductoModelo prod) throws Exception;
	
	public void modificar(ProductoModelo prod) throws Exception;
	
	public void eliminar(ProductoModelo prod) throws Exception;
	
	public List<ProductoModelo> listar() throws Exception;
	
	public List<ProductoModelo> listarInactivos() throws Exception;
	
	public List<ProductoModelo> listarActivos() throws Exception;
	
	public List<ProductoModelo> listarVinos() throws Exception;
	
	public List<ProductoModelo> listarPiscos() throws Exception;
}