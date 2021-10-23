/**
 * 
 */
package com.servisoft.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.servisoft.impl.Conexion;
import com.servisoft.impl.dao.ProductoDao;
import com.servisoft.impl.dao.VentaDao;
import com.servisoft.model.ProductoModelo;
import com.servisoft.model.VentaDetalleModelo;
import com.servisoft.model.VentaModelo;
import com.servisoft.servicios.ReporteFactura;

/**
 * @author chema
 * Controlador de Ventas.
 */

@ManagedBean
@SessionScoped
public class VentaBean implements Serializable {

	private VentaDao daoVent = new VentaDao();

	private VentaModelo modelVent = new VentaModelo();

	private List<VentaModelo> listVent = new ArrayList<>();

	private VentaDetalleModelo modelVentDet = new VentaDetalleModelo();

	private List<VentaDetalleModelo> listVentDet = new ArrayList<>();

	private ProductoDao daoProd = new ProductoDao();

	private ProductoModelo modelProd = new ProductoModelo();

	private List<ProductoModelo> listProd = new ArrayList<>();

	private int idVenta;

	private Long total;

	private int cantidad;
	
	@PostConstruct
	public void contructor() {
		listarProd();
	}

	public void registrar() {
		try {
			daoVent.registrar(modelVent);
			idVenta = daoVent.obtenerCodVent();
			System.out.println("Código de venta: " + idVenta);
			limpiar();
		} catch (Exception e) {
			System.out.println("VentaBean- Error en Registrar: " + e.getMessage());
		}
	}
	
	public void registrarVD() {
		try {
			daoVent.registrar(modelVent);
			idVenta = daoVent.obtenerCodVent();
			System.out.println("Código de venta: " + idVenta);
			for (VentaDetalleModelo ventaDetalleModelo : listVentDet) {
				if (ventaDetalleModelo.getCant() >= 1) {
					ventaDetalleModelo.setCodVent(idVenta);
					daoVent.registrarVD(ventaDetalleModelo);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada", "El producto ha sido registrado correctamente"));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Está intentando registrar nada"));
				}
			}
		} catch (Exception e) {
			System.out.println("VentaBean- Error en RegistrarVD: " + e.getMessage());
		}
	}

	public void listar() {
		try {
			listVent = listUltVent();
			listVentDet = listUltVentDet();
		} catch (Exception e) {
			System.out.println("VentDet-Error en listar: "+ e.getMessage());
		}
	}

	public List<VentaModelo> listUltVent() throws Exception {
		List<VentaModelo> lista = new ArrayList<>();
		VentaModelo vent;
		String sql = "SELECT * FROM vFactura order by CODVENT DESC";
		try {
			PreparedStatement ps = Conexion.conectar().prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vent = new VentaModelo();
				vent.setCodigo(rs.getInt("CODVENT"));
				vent.setCliente(rs.getString("CLIENTE"));
				vent.setFecha(rs.getString("FECHA"));
				vent.setTotal(rs.getInt("TOTCOMPRA"));
				lista.add(vent);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			System.out.println("VentaBean- Error en listUltVent");
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	public List<VentaDetalleModelo> listUltVentDet() throws Exception {
		List<VentaDetalleModelo> lista = new ArrayList<>();
		VentaDetalleModelo ventdet = new VentaDetalleModelo();
		String sql = "SELECT * FROM VENTA_DETALLE where CODVENT = (select MAX(CODVENT) from VENTA_DETALLE)";
		try {
			PreparedStatement ps = Conexion.conectar().prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ventdet.setCodigo(rs.getInt("CODVENTDET"));
				ventdet.setCant(rs.getInt("CANTPROD"));
				ventdet.setCodProd(rs.getInt("CODPRO"));
				ventdet.setCodVent(rs.getInt("CODVENT"));
				lista.add(ventdet);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			System.out.println("VentaBean-Error en listUltVentDet: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	public void listarProd() {
		try {
			listar();
			daoProd.listar().forEach((t) -> {
				VentaDetalleModelo mod = new VentaDetalleModelo();
				mod.setProducto(t);
				listVentDet.add(mod);
			});
		} catch (Exception e) {
			System.out.println("VentaBean-Error en listarProd: " + e.getMessage());
		}
	}
	
	public void verReporteFACT(VentaModelo modelo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ReporteFactura rFactura = new ReporteFactura();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("resources/reports/Comprobante.jasper");
        String ni = String.valueOf(modelo.getCodigo());
        rFactura.getReportePdf(root, ni);
        FacesContext.getCurrentInstance().responseComplete();
    }

	public void calcular(long precio) {
		try {
			long calcular = precio * cantidad;
			setTotal(calcular);
		} catch (Exception e) {
		}
	}
	
	public void datos(VentaModelo vent) {
		this.modelVent = vent;
	}
	
	public void limpiar() {
		modelVent = new VentaModelo();
		modelVentDet = new VentaDetalleModelo();
	}

	/**
	 * @return the daoVent
	 */
	public VentaDao getDaoVent() {
		return daoVent;
	}

	/**
	 * @param daoVent the daoVent to set
	 */
	public void setDaoVent(VentaDao daoVent) {
		this.daoVent = daoVent;
	}

	/**
	 * @return the modelVent
	 */
	public VentaModelo getModelVent() {
		return modelVent;
	}

	/**
	 * @param modelVent the modelVent to set
	 */
	public void setModelVent(VentaModelo modelVent) {
		this.modelVent = modelVent;
	}

	/**
	 * @return the listVent
	 */
	public List<VentaModelo> getListVent() {
		return listVent;
	}

	/**
	 * @param listVent the listVent to set
	 */
	public void setListVent(List<VentaModelo> listVent) {
		this.listVent = listVent;
	}

	/**
	 * @return the modelVentDet
	 */
	public VentaDetalleModelo getModelVentDet() {
		return modelVentDet;
	}

	/**
	 * @param modelVentDet the modelVentDet to set
	 */
	public void setModelVentDet(VentaDetalleModelo modelVentDet) {
		this.modelVentDet = modelVentDet;
	}

	/**
	 * @return the listVentDet
	 */
	public List<VentaDetalleModelo> getListVentDet() {
		return listVentDet;
	}

	/**
	 * @param listVentDet the listVentDet to set
	 */
	public void setListVentDet(List<VentaDetalleModelo> listVentDet) {
		this.listVentDet = listVentDet;
	}

	/**
	 * @return the daoProd
	 */
	public ProductoDao getDaoProd() {
		return daoProd;
	}

	/**
	 * @param daoProd the daoProd to set
	 */
	public void setDaoProd(ProductoDao daoProd) {
		this.daoProd = daoProd;
	}

	/**
	 * @return the modelProd
	 */
	public ProductoModelo getModelProd() {
		return modelProd;
	}

	/**
	 * @param modelProd the modelProd to set
	 */
	public void setModelProd(ProductoModelo modelProd) {
		this.modelProd = modelProd;
	}

	/**
	 * @return the listProd
	 */
	public List<ProductoModelo> getListProd() {
		return listProd;
	}

	/**
	 * @param listProd the listProd to set
	 */
	public void setListProd(List<ProductoModelo> listProd) {
		this.listProd = listProd;
	}

	/**
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}

	/**
	 * @param idVenta the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}