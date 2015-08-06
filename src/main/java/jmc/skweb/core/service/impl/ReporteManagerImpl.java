package jmc.skweb.core.service.impl;

import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.service.ReporteManager;

public class ReporteManagerImpl implements ReporteManager {


	private GenericDAO<Parametrizacion> parametrizacionDAO;
	

	public GenericDAO<Parametrizacion> getParametrizacionDAO() {
		return parametrizacionDAO;
	}

	public void setParametrizacionDAO(GenericDAO<Parametrizacion> parametrizacionDAO) {
		this.parametrizacionDAO = parametrizacionDAO;
	}

}
