package com.servisoft.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import com.servisoft.model.ClienteModelo;
import com.servisoft.model.VendedorModelo;

/**
 * @author chema
 * Clase de conexión con la base de datos Oracle.
 */
public class Conexion {

	public static Connection cnx = null;
	public static Connection conectar() throws Exception {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cnx = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "USDEV", "root");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Error en la conexión de la base de datos en: " + ex.getMessage());
		}
		return cnx;
	}

	public static void cerrarCnx() throws Exception {
		if (Conexion.cnx != null) {
			cnx.close();
		}
	}

	public static void main(String[] args) throws Exception {
		List<VendedorModelo> lista = new ArrayList<>();
		VendedorModelo vend = new VendedorModelo();
		String sql = "SELECT CODVEND, NOMVEND, APEVEND FROM VENDEDOR where ESTVEND = 'A'";
		try {
			PreparedStatement ps = Conexion.conectar().prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vend.setCodigo(rs.getInt("CODVEND"));
				vend.setNombre(rs.getString("NOMVEND"));
				vend.setApellido(rs.getString("APEVEND"));
				lista.add(vend);
				System.out.println(lista);
			}
			rs.close();
			ps.close();
		}catch (Exception e) {
		}
	}
}