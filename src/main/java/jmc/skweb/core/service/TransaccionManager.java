package jmc.skweb.core.service;


import java.math.BigDecimal;
import java.util.List;

import jmc.skweb.core.model.ClieArticPrecio;
import jmc.skweb.core.model.Colores;
import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.EstadiTipoReporte;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.EstadisticaPedido;
import jmc.skweb.core.model.report.TipoReporte;
import jmc.skweb.core.model.traza.Trazabi;






/**
 * @author Juan Manuel Carrascal
 *
 */
public interface TransaccionManager {

	List<Items> addItem(List<Items> itemsSessionList, Items items, Usuario usuario, boolean setPrecio);

	List<Items> removeItem(List<Items> itemsSessionList, Items items);

	Transac calculoTotales(List<Items> itemsSessionList);

	BigDecimal getPrecioClienteArticulo(Integer genteNr, String clave, Usuario usuario, boolean muestraEnPesos);

	String saveTransac_OP(List<Items> itemsSessionList, Integer genteNr, Integer i, String fechaEntrega, Transac transac, int tipoComprobNr, Usuario usuario) throws Exception;

	String getArticuloPorClave(String clave);

	BigDecimal getBonifPorCadena(String bonif1, String bonif2, String bonif3,
			String bonif4);

	String getFormatCadena(String bonif1, String bonif2, String bonif3,
			String bonif4);

	
	public List<Items> editItem(List<Items> itemsSessionList, Items items, Usuario usuario) ;
	List<TipoComprob> getTipoComprobEnable(Integer rol);

	TipoComprob getTipoComprobByPK(int nr);

	List<TipoComprob> getTipoComprobAll();

	List<TipoReporte> getTipoReporteAll();

	Gente getGenteConDomiPrinc(Integer genteNr);

	List<TipoReporte> getTipoReporteTesoreria();

	String getArticuloPorClave(String clave, boolean articuloActivo);

	String getTransacJoin(int nr, String clave, Usuario usuarioSesion);

	Transac getTransacByPK(Integer transacNr);

	Domicilios getDomiciliosAdm(Gente gente);

	List<Transac> getRemitosPendientesPorClienteTraza(Usuario usuarioSesion);

	List<Trazabi> getTrazabiPorComprobante(Integer transacNr);

	List<Transac> getPedidosPendientes(Gente gente, Usuario usuario);

	List<Items> getEstadisticaArtGente(Integer genteNr, Usuario usuarioSesion,
			String fechaDesde, String fechaHasta);

	List<TipoReporte> getTipoReporteEstadistica();

	List<Domicilios> getDomiciliosPorGente(Gente gente);

	List<Condi> getCondiAll();

	List<EstadisticaPedido> getEstadisticaPedido();

	List<EstadiTipoReporte> getEstadiTipoReporte();

	byte[] generateEstadiReport(DatosReporte datosReporte, Usuario usuarioSesion);

	List<Items> changeValorMasivo(List<Items> itemsSessionList, Items items, Integer tipoValorMasivo,
			String itemsSelecionados, Usuario usuarioSesion);

	List<Transac> getPedidosVtaPendienteAprobar(Gente gente,
			Usuario usuarioSesion);

	Colores getColor(Integer colo);

	}
