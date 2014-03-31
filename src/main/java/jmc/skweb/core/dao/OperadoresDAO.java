package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Operadores;



public interface OperadoresDAO extends GenericDAO<Operadores> {

	public List<Operadores> getOperadorPorNombre(String nombre)throws Exception ;

}
