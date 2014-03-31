package jmc.skweb.core.service;

import java.util.List;

import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.util.exception.DataAccessDPIv1Exception;

public interface ParametrizacionManager {

	Parametrizacion getParametrizacionByPrimaryKey(Long idParametrizacion)throws DataAccessDPIv1Exception;
	
	List<Parametrizacion> getAllParametrizacion() throws DataAccessDPIv1Exception;
	
	void updateParametrizacion(Parametrizacion parametrizacion)throws DataAccessDPIv1Exception;
}
