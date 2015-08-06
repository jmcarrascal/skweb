package jmc.skweb.core.dao;

import java.util.List;

import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.EstadisticaPedido;
import jmc.skweb.core.model.report.TransacJoin;



public interface TransacDAO extends GenericDAO<Transac> {

	List<Transac> getCuentaCorrienteVenta(Integer[] listTipoComprVentas,
			Integer genteNr);

	List<Transac> getResumenCuentaCorriente(Integer[] listTipoComprVentas,
			Integer genteNr, String fechaDesde, String fechaHasta);

	Double getSaldoPorAgendadoTipoCompr(Integer tipoCompr, Integer genteNr,
			String fechaDesde);
	
	public Double getTotalPorAgendadoTipoCompr(Integer tipoCompr,
			Integer genteNr, String fechaDesde) ;


	List<Transac> getTransacPorTipoComprob(Integer genteNr, int nr, String fechaDesde, String fechaHasta);

	Double getTotalDisponibleByArtComprob(String clave,
			Integer idTipoCompPedidoCompra);

	List<TransacJoin> getTransacJoin(int nr, String clave);

	List<Transac> getRemitosPendientesPorClienteTraza(Gente genteSession);

	List<TransacJoin> getTransacJoinOByFEntregaTransac(
			Integer idTipoCompPedidoCompra, String clave);

	List<Transac> getPedidosPendientes(Gente gente);

	List<Items> getEstadisticaArtGente(Integer genteNr, String fechaDesde,
			String fechaHasta, int idTipoComprob);


	public List<EstadisticaPedido> getEstadisticaPedido(DatosReporte datosReporte) ;

	List<EstadisticaPedido> getEstadisticaVentas(DatosReporte datosReporte);

	List<Transac> getPedidosVtaPendienteAprobar(Gente gente);

}
