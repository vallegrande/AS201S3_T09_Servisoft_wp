/**
 * 
 */
package com.servisoft.impl.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.servisoft.model.ProductoModelo;

/**
 * @author chema
 *
 */
public class ProductoDaoTest {
	
	ProductoDao productoDao = new ProductoDao();

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#registrar(com.servisoft.model.ProductoModelo)}.
	 * @throws Exception 
	 */
	@Test
	public void testRegistrar() throws Exception {
		ProductoModelo producto = new ProductoModelo();
		producto.setNombre("Tintado");
		producto.setFecha("2018");
		producto.setPrecio(10);
		producto.setTipo("V");
		producto.setTam("500 ML");
		producto.setStock(10);
		producto.setEstado("A");
		this.productoDao.registrar(producto);
	}

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#modificar(com.servisoft.model.ProductoModelo)}.
	 * @throws Exception 
	 */
	@Test
	public void testModificar() throws Exception {
		ProductoModelo prod = new ProductoModelo();
		prod.setCodigo(23);
		prod.setNombre("Arropado");
		prod.setFecha("2020");
		prod.setPrecio(50);
		prod.setTipo("V");
		prod.setTam("250 ML");
		prod.setStock(20);
		prod.setEstado("A");
		this.productoDao.modificar(prod);
	}

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#eliminar(com.servisoft.model.ProductoModelo)}.
	 * @throws Exception 
	 */
	@Test
	public void testEliminar() throws Exception {
		ProductoModelo producto = new ProductoModelo();
		producto.setCodigo(17);
		this.productoDao.eliminar(producto);
	}

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#listar()}.
	 */
	@Test
	public void testListar() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#listarInactivos()}.
	 */
	@Test
	public void testListarInactivos() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.servisoft.impl.dao.ProductoDao#listarActivos()}.
	 */
	@Test
	public void testListarActivos() {
		fail("Not yet implemented"); // TODO
	}

}
