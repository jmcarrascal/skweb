package jmc.skweb.ui.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.service.ArticuloManager;


@WebService
public class ServiceArticuloWS{
	
	private ArticuloManager articuloManager;
	private FamDAO extendedFamDAO;

	
	
	@WebMethod(exclude=true)
	public FamDAO getExtendedFamDAO() {
		return extendedFamDAO;
	}
	@WebMethod(exclude=true)
	public void setExtendedFamDAO(FamDAO extendedFamDAO) {
		this.extendedFamDAO = extendedFamDAO;
	}
	

	@WebMethod(exclude=true)
	public ArticuloManager getArticuloManager() {
		return articuloManager;
	}

	@WebMethod(exclude=true)
	public void setArticuloManager(ArticuloManager articuloManager) {
		this.articuloManager = articuloManager;
	}


	@WebMethod(operationName="getArticuloPorNombre")
	public String getArticuloPorNombre(String nombreArticulo) {

		String articuloList = articuloManager.getListFam().get(0).getDesfam();
		
		return articuloList;
		
	}
	
	@WebMethod(operationName="getAllFam")
	public List<Fam> getAllFam() {

		List<Fam> famList = articuloManager.getListFam();
		
		return famList;
		
	}
 
}