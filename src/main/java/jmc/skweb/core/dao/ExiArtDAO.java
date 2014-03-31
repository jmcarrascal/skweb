package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Stock;


public interface ExiArtDAO extends GenericDAO<ExiArt> {

	public List<ExiArt> getExistenciaPorNombre(String clave)throws Exception ;

	public Double getStockByExiClave(Double idExistencia, String clave);

	public List<ExiArt> getExistenciaPorNombre(String clave,
			Double idPreferenciaDefault);
}
