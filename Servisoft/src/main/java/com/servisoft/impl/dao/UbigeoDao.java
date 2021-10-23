/**
 * 
 */
package com.servisoft.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.servisoft.impl.Conexion;
import com.servisoft.impl.UbigeoImpl;
import com.servisoft.model.UbigeoModelo;

/**
 * @author chema
 * Dao de Ubigeo.
 */
public class UbigeoDao extends Conexion implements UbigeoImpl {

	@Override
	public List<UbigeoModelo> listar() throws Exception {
		List<UbigeoModelo> lista = new ArrayList<>();
		UbigeoModelo ubi;
		String sql = "SELECT * FROM UBIGEO";
		try {
			Statement ps = Conexion.conectar().createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				ubi = new UbigeoModelo();
				ubi.setCodigo(rs.getString("CODUBI"));
				ubi.setDepartamento(rs.getString("DEPUBI"));
				ubi.setProvincia(rs.getString("PROUBI"));
				ubi.setDistrito(rs.getString("DISUBI"));
				lista.add(ubi);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			System.out.println("Ubigeo-Error en Listar el ubigeo: "+ e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}
}