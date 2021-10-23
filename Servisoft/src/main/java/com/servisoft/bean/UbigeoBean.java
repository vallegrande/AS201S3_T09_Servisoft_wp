/**
 * 
 */
package com.servisoft.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.servisoft.impl.dao.UbigeoDao;
import com.servisoft.model.UbigeoModelo;

import lombok.Data;

/**
 * @author chema
 * Controlador de Ubigeo.
 */

@ManagedBean
@SessionScoped
public class UbigeoBean {

	private UbigeoModelo modelo = new UbigeoModelo();

	private UbigeoDao dao = new UbigeoDao();

	private List<UbigeoModelo> listarUbi = new ArrayList<>();

	@PostConstruct
	public void construir() {
		try {
			listar();
		} catch (Exception e) {
			System.out.println("UbigeoBean-Error en constructor: " + e.getMessage());
		}
	}

	public void listar() {
		try {
			listarUbi = dao.listar();
		} catch (Exception e) {
			System.out.println("UbigeoBean-Error en listar: " + e.getMessage());
		}
	}

	/**
	 * @return the modelo
	 */
	public UbigeoModelo getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(UbigeoModelo modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the dao
	 */
	public UbigeoDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(UbigeoDao dao) {
		this.dao = dao;
	}

	/**
	 * @return the listarUbi
	 */
	public List<UbigeoModelo> getListarUbi() {
		return listarUbi;
	}

	/**
	 * @param listarUbi the listarUbi to set
	 */
	public void setListarUbi(List<UbigeoModelo> listarUbi) {
		this.listarUbi = listarUbi;
	}
}