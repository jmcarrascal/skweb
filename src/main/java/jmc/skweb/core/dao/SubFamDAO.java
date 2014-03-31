package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.SubFam;



public interface SubFamDAO extends GenericDAO<SubFam> {

	public List<SubFam> getSubFamEnable()throws Exception ;
	
	public List<SubFam> getSubFamByFam(Integer clave)throws Exception ;
}
