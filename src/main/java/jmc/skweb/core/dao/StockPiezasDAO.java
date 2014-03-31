package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.StockPiezas;



public interface StockPiezasDAO extends GenericDAO<StockPiezas> {

	public List<StockPiezas> getStockPiezasPorArticulo(String clave) ;

	
}
