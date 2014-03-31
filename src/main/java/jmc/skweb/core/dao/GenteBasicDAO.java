package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.shortEntities.GenteBasic;



public interface GenteBasicDAO extends GenericDAO<GenteBasic> {

	public List<GenteBasic> getProveedorBasicPorNombre(String nombre)throws Exception ;

	public List<GenteBasic> getProveedorBasicPorNombreVendedor(String nombreProveedor,
			Integer vendedorNr);

	public GenteBasic getProveedorBasicPorCodVendedor(Integer genteNr,
			Integer vendedorNr);
	
	public List<GenteBasic> getClienteBasicPorVendedor(Integer vendedorNr);
}
