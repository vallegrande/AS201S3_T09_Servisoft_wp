/**
 * 
 */
package com.servisoft.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.servisoft.impl.Conexion;
import com.servisoft.impl.ProductoImpl;
import com.servisoft.model.ProductoModelo;

/**
 * @author chema
 * Dao de productos.
 */
public class ProductoDao extends Conexion implements ProductoImpl {
	
	@Override
	public void registrar(ProductoModelo prod) throws Exception {
		String sql = "INSERT INTO PRODUCTO"
					+ " (CODPRO, NOMPRO, COSPRO, PREPRO, TIPPRO, TAMPRO, STOCKPRO, ESTPRO)"
					+ " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setString(1, prod.getNombre());
			ps.setString(2, prod.getFecha());
			ps.setFloat(3, prod.getPrecio());
			ps.setString(4, prod.getTipo());
			ps.setString(5, prod.getTam());
			ps.setInt(6, prod.getStock());
			ps.setString(7, prod.getEstado());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("ProductoDao-Error en registrar: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public void modificar(ProductoModelo prod) throws Exception {
		String sql = "UPDATE PRODUCTO SET NOMPRO=?, COSPRO=?, PREPRO=?, TIPPRO=?, TAMPRO=?, STOCKPRO=? WHERE CODPRO=? ";
		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setString(1, prod.getNombre());
			ps.setString(2, prod.getFecha());
			ps.setFloat(3, prod.getPrecio());
			ps.setString(4, prod.getTipo());
			ps.setString(5, prod.getTam());
			ps.setInt(6, prod.getStock());
			ps.setInt(7, prod.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ModificarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public void eliminar(ProductoModelo prod) throws Exception {
		String sql = "UPDATE PRODUCTO SET ESTPRO = 'I' WHERE CODPRO = ?";
		try {
			PreparedStatement ps = Conexion.conectar().prepareStatement(sql);
			ps.setInt(1, prod.getCodigo());
			System.out.println("se envió el código: "+ prod.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en EliminarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
	}

	@Override
	public List<ProductoModelo> listar() throws Exception, SQLException{
		List<ProductoModelo> lista = new ArrayList<>();
		ProductoModelo prod;
		String sql = "SELECT * FROM PRODUCTO";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new ProductoModelo();
				prod.setCodigo(rs.getInt("CODPRO"));
				prod.setNombre(rs.getString("NOMPRO"));
				prod.setFecha(rs.getString("COSPRO"));
				prod.setPrecio(rs.getLong("PREPRO"));
				prod.setTipo(rs.getString("TIPPRO"));
				prod.setTam(rs.getString("TAMPRO"));
				prod.setStock(rs.getInt("STOCKPRO"));
				prod.setEstado(rs.getString("ESTPRO"));
				lista.add(prod);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	@Override
	public List<ProductoModelo> listarInactivos() throws Exception {
		List<ProductoModelo> lista = new ArrayList<>();
		ProductoModelo prod;
		String sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'I' ";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new ProductoModelo();
				prod.setCodigo(rs.getInt("CODPRO"));
				prod.setNombre(rs.getString("NOMPRO"));
				prod.setFecha(rs.getString("COSPRO"));
				prod.setPrecio(rs.getLong("PREPRO"));
				prod.setTipo(rs.getString("TIPPRO"));
				prod.setTam(rs.getString("TAMPRO"));
				prod.setStock(rs.getInt("STOCKPRO"));
				prod.setEstado(rs.getString("ESTPRO"));
				lista.add(prod);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	@Override
	public List<ProductoModelo> listarActivos() throws Exception {
		List<ProductoModelo> lista = new ArrayList<>();
		ProductoModelo prod;
		String sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' ";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new ProductoModelo();
				prod.setCodigo(rs.getInt("CODPRO"));
				prod.setNombre(rs.getString("NOMPRO"));
				prod.setFecha(rs.getString("COSPRO"));
				prod.setPrecio(rs.getLong("PREPRO"));
				prod.setTipo(rs.getString("TIPPRO"));
				prod.setTam(rs.getString("TAMPRO"));
				prod.setStock(rs.getInt("STOCKPRO"));
				prod.setEstado(rs.getString("ESTPRO"));
				lista.add(prod);
			} 
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	@Override
	public List<ProductoModelo> listarVinos() throws Exception {
		List<ProductoModelo> lista = new ArrayList<>();
		ProductoModelo prod;
		String sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'V' ";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new ProductoModelo();
				prod.setCodigo(rs.getInt("CODPRO"));
				prod.setNombre(rs.getString("NOMPRO"));
				prod.setFecha(rs.getString("COSPRO"));
				prod.setPrecio(rs.getLong("PREPRO"));
				prod.setTipo(rs.getString("TIPPRO"));
				prod.setTam(rs.getString("TAMPRO"));
				prod.setStock(rs.getInt("STOCKPRO"));
				prod.setEstado(rs.getString("ESTPRO"));
				lista.add(prod);
			} 
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}

	@Override
	public List<ProductoModelo> listarPiscos() throws Exception {
		List<ProductoModelo> lista = new ArrayList<>();
		ProductoModelo prod;
		String sql = "SELECT * FROM PRODUCTO WHERE TIPPRO = 'P' ";
		try {
			Statement stmt = Conexion.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				prod = new ProductoModelo();
				prod.setCodigo(rs.getInt("CODPRO"));
				prod.setNombre(rs.getString("NOMPRO"));
				prod.setFecha(rs.getString("COSPRO"));
				prod.setPrecio(rs.getLong("PREPRO"));
				prod.setTipo(rs.getString("TIPPRO"));
				prod.setTam(rs.getString("TAMPRO"));
				prod.setStock(rs.getInt("STOCKPRO"));
				prod.setEstado(rs.getString("ESTPRO"));
				lista.add(prod);
			} 
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println("Producto - Error en ListarDao: " + e.getMessage());
		} finally {
			Conexion.cerrarCnx();
		}
		return lista;
	}
}