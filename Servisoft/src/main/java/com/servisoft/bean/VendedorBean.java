/**
 * 
 */
package com.servisoft.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.servisoft.impl.dao.VendedorDao;
import com.servisoft.model.VendedorModelo;

/**
 * @author chema
 *
 */
@ManagedBean
@SessionScoped
public class VendedorBean implements Serializable{
	
	private VendedorModelo model = new VendedorModelo();
	
	private VendedorDao dao = new VendedorDao();
	
	private List<VendedorModelo> listVend = new ArrayList<>();
	
	@PostConstruct
	public void contructor() {
		try {
			listar();
		} catch (Exception e) {
			System.out.println("VendBean-Error en contructor: "+ e.getMessage());
		}
	}
	
	public void listar() {
		try {
			listVend = dao.listarVendedor();
		} catch (Exception e) {
			System.out.println("VendBean-Error en listar: "+ e.getMessage());
		}
	}
	
	public void limpiar() {
		model = new VendedorModelo();
	}
	
	/**
	 * @return the model
	 */
	public VendedorModelo getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(VendedorModelo model) {
		this.model = model;
	}

	/**
	 * @return the dao
	 */
	public VendedorDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(VendedorDao dao) {
		this.dao = dao;
	}

	/**
	 * @return the listVend
	 */
	public List<VendedorModelo> getListVend() {
		return listVend;
	}

	/**
	 * @param listVend the listVend to set
	 */
	public void setListVend(List<VendedorModelo> listVend) {
		this.listVend = listVend;
	}
}