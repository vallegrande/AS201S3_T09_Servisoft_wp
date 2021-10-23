/**
 * 
 */
package com.servisoft.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.SQLException;
import java.io.IOException;

import com.servisoft.impl.dao.ProductoDao;
import com.servisoft.model.ProductoModelo;
import com.servisoft.servicios.ReporteProducto;

import javax.servlet.ServletContext;

/**
 * @author chema
 * Controlador de Productos.
 */

@ManagedBean
@SessionScoped
public class ProductoBean implements Serializable {

	private ProductoModelo modelo = new ProductoModelo();

	private ProductoDao dao = new ProductoDao();

	private List<ProductoModelo> listarProd = new ArrayList<>();

	private ProductoModelo selectedProduct = new ProductoModelo();

	private int opc;

	@PostConstruct
	public void constructor() {
		try {
			listar();
		} catch (Exception e) {
			System.out.println("Error en ConstruirC: " + e.getMessage());
		}
	}
	
	public void cerrarSesion() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("CloseSession.xhtml");
		} catch (Exception e) {
			System.out.println("ProdBean-Error en cerrarSesion: "+ e.getMessage());
		}
	}

	public void opcListar() throws Exception {
		try {
			switch (opc) {
				case 1:
					listarProd = dao.listar();
					System.out.println("Está listando todo");
					break;
				case 2:
					listarProd = dao.listarActivos();
					System.out.println("Está listando solo los activos");
					break;
				case 3:
					listarProd = dao.listarInactivos();
					System.out.println("Usted está listando solo los inactivos");
					break;
				default:
					System.out.println("Elija con cuidado...");
			}
			System.out.println("Opción elegida: " + opc);
		} catch (Exception e) {
			System.out.println("ProdBean-Error en listar por opciones: " + e.getMessage());
		}
	}
	
	public void listarTipos() {
		try {
			
			switch(opc) {
				case 1:
					listarProd = dao.listar();
					System.out.println("Está listando todos los tipos de productos");
					break;
				case 2:
					listarProd = dao.listarVinos();
					System.out.println("Está listando solo los vinos");
					break;
				case 3:
					listarProd = dao.listarPiscos();
					System.out.println("Está listando solo los piscos");
					break;
				default:
					System.out.println("Eslija con cuidado...");
			}
			System.out.println("Opción elejida: " + opc);
		} catch (Exception e) {
			System.out.println("ProdBean-Error en listarTipos: " + e.getMessage());
		}
	}

	public void registrar() {
		try {
			dao.registrar(modelo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registró correctamente"));
		} catch (Exception e) {
			System.out.println("ProductoC-Error en registrar: " + e.getMessage());
		}
	}

	public void eliminar() {
		try {
			dao.eliminar(selectedProduct);
		} catch (Exception e) {
			System.out.println("ProductoC-Error en eliminar: " + e.getMessage());
		}
	}

	public void modificar() {
		try {
			System.out.println(modelo);
			dao.modificar(modelo);
		} catch (Exception e) {
			System.out.println("ProductoC-Error en modificar: " + e.getMessage());
		}
	}

	public void listar() {
		try {
			listarProd = dao.listar();
		} catch (Exception e) {
			System.out.println("ProductoC-Error en: " + e.getMessage());
		}
	}

	/*
	 * Generación de reportes: - Piscos - Vinos
	 */
	public void verReporteVinoPDF() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		try {
			modelo.setTipo("V");
			ReporteProducto rProducto = new ReporteProducto();
			FacesContext facescontext = FacesContext.getCurrentInstance();
			ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
			String root = servletcontext.getRealPath("resources/reports/Producto.jasper");
			String numeroinformesocial = String.valueOf(modelo.getTipo());
			System.out.println("El tipo de V es: " + numeroinformesocial);
			rProducto.getReportePdf(root, numeroinformesocial);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ProdBean-Error en verReporteVino: "+e.getMessage());
		}
	}
	
	public void verReportePiscoPDF() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		try {
			modelo.setTipo("P");
			ReporteProducto rProducto = new ReporteProducto();
			FacesContext facescontext = FacesContext.getCurrentInstance();
			ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
			String root = servletcontext.getRealPath("resources/reports/Producto.jasper");
			String numeroinformesocial = String.valueOf(modelo.getTipo());
			System.out.println("El tipo de V es: " + numeroinformesocial);
			rProducto.getReportePdf(root, numeroinformesocial);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ProdBean-Error en verReportePisco: "+e.getMessage());
		}
	}

	public void datos() {
		this.modelo = selectedProduct;
		System.out.println(selectedProduct);
	}

	public void limpiar() {
		modelo = new ProductoModelo();
	}

	/**
	 * @return the modelo
	 */
	public ProductoModelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(ProductoModelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the dao
	 */
	public ProductoDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(ProductoDao dao) {
		this.dao = dao;
	}

	/**
	 * @return the listarProd
	 */
	public List<ProductoModelo> getListarProd() {
		return listarProd;
	}

	/**
	 * @param listarProd the listarProd to set
	 */
	public void setListarProd(List<ProductoModelo> listarProd) {
		this.listarProd = listarProd;
	}

	/**
	 * @return the selectedProduct
	 */
	public ProductoModelo getSelectedProduct() {
		return selectedProduct;
	}

	/**
	 * @param selectedProduct the selectedProduct to set
	 */
	public void setSelectedProduct(ProductoModelo selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	/**
	 * @return the opc
	 */
	public int getOpc() {
		return opc;
	}

	/**
	 * @param opc the opc to set
	 */
	public void setOpc(int opc) {
		this.opc = opc;
	}
}