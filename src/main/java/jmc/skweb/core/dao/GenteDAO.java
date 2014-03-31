package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;



public interface GenteDAO extends GenericDAO<Gente> {

	public List<Gente> getProveedorPorNombre(String nombre)throws Exception ;

	public List<Gente> getProveedorPorNombreVendedor(String nombreProveedor,
			Integer vendedorNr);
}
