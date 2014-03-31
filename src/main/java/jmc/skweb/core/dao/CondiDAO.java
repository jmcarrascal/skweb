package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Vendedor;



public interface CondiDAO extends GenericDAO<Condi> {

	List<Condi> getCondiAll();
	
}
