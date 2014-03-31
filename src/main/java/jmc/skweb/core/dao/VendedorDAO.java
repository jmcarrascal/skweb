package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Vendedor;



public interface VendedorDAO extends GenericDAO<Vendedor> {

	List<Vendedor> getVendedorEnable();
	
	
}
