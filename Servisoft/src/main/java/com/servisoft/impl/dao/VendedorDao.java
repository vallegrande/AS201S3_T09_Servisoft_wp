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
import com.servisoft.impl.VendedorImpl;
import com.servisoft.model.VendedorModelo;

/**
 * @author chema
 * Dao de Vendedor.
 */
public class VendedorDao extends Conexion implements VendedorImpl {

	@Override
	public List<VendedorModelo> listarVendedor() throws Exception {
		List<VendedorModelo> lista = new ArrayList<>();
		VendedorModelo vend;
		String sql = "SELECT CODVEND, NOMVEND, APEVEND FROM VENDEDOR where ESTVEND = 'A'";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				vend = new VendedorModelo();
				vend.setCodigo(rs.getInt("CODVEND"));
				vend.setNombre(rs.getString("NOMVEND"));
				vend.setApellido(rs.getString("APEVEND"));
				lista.add(vend);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Vendedor: Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}
}