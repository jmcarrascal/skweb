package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Stock;



public interface StockDAO extends GenericDAO<Stock> {
	
	public List<Stock> getArticuloPorClaveList(String clave,
			boolean articuloActivo) ;

	public List<Stock> getArticuloPorNombre(String filtro, boolean activos)throws Exception ;

	public List<Stock> getArticuloPorNrSubFam(Long nrsubfam, boolean activos);

	public List<Stock> getArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam, boolean activos);

	public List<Stock> getArticuloPorNrFam(Long nrfam, boolean activos);

	public List<Stock> getArticuloPorNrProveedor(Integer genteNr, boolean activos);

	public List<Stock> getArticuloPorNombreLimit(String filter, int i);

	public String getArticuloPorPK(String clave, boolean articuloActivo);

	public List<Stock> getImagenArticuloPorNrSubFam(Long nrsubfam,
			boolean activos);

	public List<Stock> getImagenArticuloPorNrFamSubFam(Long nrfam,
			Long nrsubfam, boolean activos);

	public List<Stock> getImagenArticuloPorNrFam(Long nrfam, boolean activos);

	public List<Stock> getArticuloPorNrSubFam(Long nrsubfam, boolean activos,
			String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public Long getCountArticuloPorNrSubFam(Long nrsubfam, boolean activos,
			String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public List<Stock> getArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam,
			boolean activos, String propertySort, String orderSort,
			String[] propertyFilter, String[] valueFilter, Integer min,
			int pageSize);

	public Long getCountArticuloPorNrFamSubFam(Long nrfam, Long nrsubfam,
			boolean activos, String propertySort, String orderSort,
			String[] propertyFilter, String[] valueFilter, Integer min,
			int pageSize);

	public Long getCountArticuloPorNrFam(Long nrfam, boolean activos,
			String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public List<Stock> getArticuloPorNrFam(Long nrfam, boolean activos,
			String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public Long getCountArticuloPorNombre(String descripcion,
			boolean articuloActivo, String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public List<Stock> getArticuloPorNombre(String descripcion,
			boolean activos, String propertySort, String orderSort,
			String[] propertyFilter, String[] valueFilter, Integer min,
			int pageSize);

	public List<Stock> getArticuloPorCodigoLike(String clave,
			boolean articuloActivo);

	public Long getCountArticuloPorCodigo(String clave, boolean articuloActivo,
			String propertySort, String orderSort, String[] propertyFilter,
			String[] valueFilter, Integer min, int pageSize);

	public List<Stock> getArticuloPorClave(String clave,
			boolean articuloActivo, String propertySort, String orderSort,
			String[] propertyFilter, String[] valueFilter, Integer min,
			int pageSize);
}
