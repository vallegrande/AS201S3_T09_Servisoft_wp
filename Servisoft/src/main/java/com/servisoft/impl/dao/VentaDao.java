/**
 * 
 */
package com.servisoft.impl.dao;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.servisoft.impl.Conexion;
import com.servisoft.impl.VentaImpl;
import com.servisoft.model.VentaDetalleModelo;
import com.servisoft.model.VentaModelo;

/**
 * @author chema
 * Dao de Venta.
 */
public class VentaDao extends Conexion implements VentaImpl {

	@Override
	public void registrar(VentaModelo vent) throws Exception {
		String sql = "INSERT INTO VENTA (CODVENT, FECVENT, TIPVENT, CODVEND, CODCLI) "
					+ " VALUES(NULL, DEFAULT, ?, ?, ?) ";
		try {
			CallableStatement cs = Conexion.conectar().prepareCall(sql);
			cs.setString(1, vent.getTipo());
			cs.setInt(2, vent.getCodVend());
			cs.setInt(3, vent.getCodCli());
			cs.executeUpdate();
			cs.close();
		} catch (Exception e) {
			System.out.println("Venta-Error en RegistrarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public void registrarVD(VentaDetalleModelo ventdet) throws Exception {
		String sql = "INSERT INTO VENTA_DETALLE (CODVENT, CODPRO, CANTPROD) "
					+ " VALUES(?, ?, ?)";
		try {
			CallableStatement cs = Conexion.conectar().prepareCall(sql);
			cs.setInt(1, ventdet.getCodVent());
			cs.setInt(2, ventdet.getProducto().getCodigo());
			cs.setInt(3, ventdet.getCant());
			cs.executeUpdate();
			cs.close();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada con éxito", ""));
		} catch (Exception e) {
			System.out.println("VentaDetalle-Error en RegistrarVDDao: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas al realizar la compra...", ""));
		} finally{
			Conexion.cerrarCnx();
		}
	}

	@Override
	public int obtenerCodVent() {
		int result = 0;
		String sql = "SELECT MAX(CODVENT) AS CODVENT FROM VENTA";
		try {
			Statement ps = Conexion.conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				result = rs.getInt("CODVENT");
			}
		} catch (Exception e) {
			System.out.println("obtenerCodVent - Error en RegistrarDao: " + e.getMessage());
		}
		return result;
	}
}