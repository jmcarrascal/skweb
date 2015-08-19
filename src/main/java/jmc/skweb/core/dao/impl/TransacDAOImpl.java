package jmc.skweb.core.dao.impl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.cfg.SettingsFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Table;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;


import jmc.skweb.core.dao.FamDAO;
import jmc.skweb.core.dao.ParametrizacionDAO;
import jmc.skweb.core.dao.StockDAO;
import jmc.skweb.core.dao.TransacDAO;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.EstadisticaPedido;
import jmc.skweb.core.model.report.TransacJoin;
import jmc.skweb.util.Constants;
import jmc.skweb.util.DateUtil;

	/**
	 * 
	 * @author Juan Manuel
	 * 
	 */

	public class TransacDAOImpl extends GenericDAOImpl<Transac> implements TransacDAO {

		public TransacDAOImpl() {
			super(Transac.class);		 
		}

		
		public List<Transac> getCuentaCorrienteVenta(
				Integer[] listTipoComprVentas, Integer genteNr) {
			List<Transac> transacList = null; 
			Boolean primerReg = true;
			String whereTipoComprSql = "";
			for(Integer tipoCompr: listTipoComprVentas){
				if (primerReg){
					whereTipoComprSql = "(t.tipoComprob = "+ tipoCompr ;	
					primerReg = false;
				}else{
					whereTipoComprSql = whereTipoComprSql + " or t.tipoComprob = "+ tipoCompr ;
				}	
				
			}
			whereTipoComprSql = whereTipoComprSql + ")";
			try{				
				String sql ="select t from Transac t where t.gente.genteNr = " + genteNr + " and t.saldo <> 0 and " + whereTipoComprSql + " order by t.fecha, t.transacNr asc";				 
			      
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;

		}

		public List<Transac> getResumenCuentaCorriente(
				Integer[] listTipoComprVentas, Integer genteNr,
				String fechaDesde, String fechaHasta) {
			List<Transac> transacList = null; 
			Boolean primerReg = true;
			String whereTipoComprSql = "";
			for(Integer tipoCompr: listTipoComprVentas){
				if (primerReg){
					whereTipoComprSql = "(t.tipoComprob = "+ tipoCompr ;	
					primerReg = false;
				}else{
					whereTipoComprSql = whereTipoComprSql + " or t.tipoComprob = "+ tipoCompr ;
				}	
				
			}
			whereTipoComprSql = whereTipoComprSql + ")";
			try{								
				String sql ="select t from Transac t where t.gente.genteNr = " + genteNr + 
				" and " + whereTipoComprSql + "and fecha >= convert(smalldatetime,CONVERT(varchar(6),'" + fechaDesde + 
				"',112),112) and fecha <= convert(smalldatetime,CONVERT(varchar(6),'"+ fechaHasta + "', 112),112) order by t.fecha, t.transacNr asc";				 
			      
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;


		}

	
		public Double getSaldoPorAgendadoTipoCompr(Integer tipoCompr,
				Integer genteNr, String fechaDesde) {
			Double saldo = 0d;
			try{								
				String sql ="select SUM(t.saldo) from Transac t where t.gente.genteNr = " + genteNr + 
				" and fecha < convert(smalldatetime,CONVERT(varchar(6),'" + fechaDesde + 
				"',112),112) and t.tipoComprob.nr = " + tipoCompr;				 
			      
				List<BigDecimal> saldoList= getHibernateTemplate().find(sql);
				saldo = saldoList.get(0).doubleValue();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return saldo;

		}
		
		public Double getTotalPorAgendadoTipoCompr(Integer tipoCompr,
				Integer genteNr, String fechaDesde) {
			Double saldo = 0d;
			try{								
				String sql ="select SUM(t.total) from Transac t where t.gente.genteNr = " + genteNr + 
				" and fecha < convert(smalldatetime,CONVERT(varchar(6),'" + fechaDesde + 
				"',112),112) and t.tipoComprob.nr = " + tipoCompr;				 
			      
				List<BigDecimal> saldoList= getHibernateTemplate().find(sql);
				saldo = saldoList.get(0).doubleValue();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return saldo;

		}


		public List<Transac> getPedidos(Integer genteNr, int idPedido) {
			
			List<Transac> transacList = null; 
			Boolean primerReg = true;
			try{								
				String sql ="select t from Transac t where t.gente.genteNr = " + genteNr + "and t.tipoComprob = " +idPedido;   
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}


		public List<Transac> getTransacPorTipoComprob(Integer genteNr, int nr, String fechaDesde, String fechaHasta) {
			List<Transac> transacList = null; 
			try{								 	
				String sql ="select t from Transac t where t.gente.genteNr = " + genteNr + 
				" and t.tipoComprob.nr = " + nr + " and fecha >= convert(smalldatetime,CONVERT(varchar(6),'" + fechaDesde + 
				"',112),112) and fecha <= convert(smalldatetime,CONVERT(varchar(6),'"+ fechaHasta + "', 112),112) order by t.fecha, t.transacNr ";				 			      
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}



		public Double getTotalDisponibleByArtComprob(String clave,
				Integer idTipoCompPedidoCompra) {
			Double saldo = 0d;
			try{								
				String sql ="SELECT  SUM(I.cant1-I.cant1entregado) " +
						"FROM Items I, Transac T WHERE I.id.transac.transacNr = T.transacNr AND ((I.cant1 - I.cant1entregado) > 0 ) " +
						"AND I.articulo = '"+ clave+ "' " +
						"AND (T.tipoComprob.nr = " + idTipoCompPedidoCompra + ") " +
						"AND (T.destino= -1) "; 
								 
			      
				List<BigDecimal> saldoList = getHibernateTemplate().find(sql);
				saldo = saldoList.get(0).doubleValue();
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return saldo;
			
		}


		
		public List<TransacJoin> getTransacJoin(int nr, String clave) {
			List<TransacJoin> transacList = null; 
			try{						
				
				String sql ="select new jmc.skweb.core.model.report.TransacJoin(T.gente.genteNr, G.razonSocial," +
						" T.nrComprob, T.transacNr, T.fecha, I.nrInt, I.cant1, I.cant1entregado, T.fechaEntrega) " +
						"FROM Items I, Transac T, Gente G WHERE " +
						"I.id.transac.transacNr = T.transacNr " +
						"AND T.gente.genteNr = G.genteNr " +
						"AND ((I.cant1 - I.cant1entregado) > 0 ) " +
						"AND I.articulo = '"+ clave +"' " +
						"AND (T.tipoComprob.nr = " + nr +" ) " +
						"AND (T.destino= -1)";	  
				
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}


		public List<TransacJoin> getTransacJoinOByFEntregaTransac(Integer nr, String clave) {
			List<TransacJoin> transacList = null; 
			try{						
				
				String sql ="select new jmc.skweb.core.model.report.TransacJoin(T.gente.genteNr, G.razonSocial," +
						" T.nrComprob, T.transacNr, T.fecha, I.nrInt, I.cant1, I.cant1entregado, T.fechaEntrega) " +
						"FROM Items I, Transac T, Gente G WHERE " +
						"I.id.transac.transacNr = T.transacNr " +
						"AND T.gente.genteNr = G.genteNr " +
						"AND ((I.cant1 - I.cant1entregado) > 0 ) " +
						"AND I.articulo = '"+ clave +"' " +
						"AND (T.tipoComprob.nr = " + nr +" ) " +
						"AND (T.destino= -1) " +
						"ORDER BY T.fechaEntrega, T.transacNr";	  
				
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}

		public List<Transac> getRemitosPendientesPorClienteTraza(
				Gente genteSession) {
			List<Transac> transacList = null; 
			try{				
				String sql = "select tr from Transac tr where transacnr in (select distinct(t.transacNr) from Transac t, Trazabi i " +
						"where t.gente.genteNr = " + genteSession.getGenteNr() + " " +
						"and t.tipoComprob.nr = " + Constants.ID_TIPO_COMP_REMITO+ " and t.recepcionClienteTraza = -1 " +
						"and ( (t.prefijo + '-' + t.nrComprob) = i.nrRemitoPropio))";
				
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}


		
		public List<Transac> getPedidosPendientes(Gente gente) {
			List<Transac> transacList = null; 	
			try{								
				String sql ="select tr from Transac tr where tr.transacNr in ( " +
				" select distinct(t.transacNr) from  Transac t, Items i " +
				" where t.transacNr = i.id.transac.transacNr " + 
				" and ((i.cant1 - i.cant1entregado) > 0 ) " +
				" and i.tipoComprob = 8 " +
				" and destino = -1) " +
				" and tr.gente.genteNr = " + gente.getGenteNr() + " order by tr.fecha, tr.transacNr";
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}
		
		public List<Transac> getPedidosVtaPendienteAprobar(Gente gente) {
			List<Transac> transacList = null; 	
			try{								
				String sql ="select tr from Transac tr where tr.transacNr in ( " +
				" select distinct(t.transacNr) from  Transac t, Items i " +
				" where t.transacNr = i.id.transac.transacNr " + 				
				" and i.tipoComprob = 8 " +
				" and destino = -5) " +
				" and tr.gente.genteNr = " + gente.getGenteNr() + " order by tr.fecha, tr.transacNr";
				transacList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return transacList;
		}


		public List<Items> getEstadisticaArtGente(Integer genteNr,
				String fechaDesde, String fechaHasta, int idTipoComprob) {
			
			List<Items> itemsList = null;
			
			try{								
				String sql = "select new Items(i.articulo, i.descrip, sum(i.cant1), sum(i.cant1entregado), (sum(i.cant1) - sum (i.cant1entregado))) " +
				"from Items i where " +
				"i.fecha >= convert(smalldatetime,CONVERT(varchar(6),'" + fechaDesde +"',112),112) and " +
				"i.fecha <= convert(smalldatetime,CONVERT(varchar(6),'" + fechaHasta +"',112),112) and " +
				"i.genteNr = " + genteNr + " and " +
				"i.tipoComprob.nr = " + idTipoComprob + " " +
				"group by i.articulo, i.descrip";
				
				itemsList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return itemsList;
			
		}

		public List<EstadisticaPedido> getEstadisticaPedido(DatosReporte datosReporte) {
			
			List<EstadisticaPedido> estadisticaPedidoList = null;
			String fechaDesde = DateUtil.composeCanonicalAAAAString(datosReporte.getFechaDesde());
			String fechaHasta = DateUtil.composeCanonicalAAAAString(datosReporte.getFechaHasta());
			
			try{								
				String sql = "select new jmc.skweb.core.model.report.EstadisticaPedido(g.razonSocial, s.descripcion,i.articulo, g.genteNr,  " + 
				" sum(i.cant1) as cantidad, sum(i.cant1entregado) as entregado, (sum(i.cant1) - sum (i.cant1entregado)) as saldo," +
				" avg(i.precio - (i.precio * (i.bonif / 100)))) from Items i, Gente g, " +
				" Stock s, Transac t where " + 
				" i.id.transac.transacNr = t.transacNr and " + 
				" t.destino = -1 and " +
				" i.articulo = s.clave and " + 
				" i.genteNr = g.genteNr and " +
				" i.tipoComprob.nr = " + datosReporte.getOrigen() + " and ";
				if (datosReporte.getPedidosPendientes().equals("true")){
					sql = sql + " (i.cant1 - i.cant1entregado) > 0  and ";
				}
				sql = sql + 
				" (i.fecha Between  convert(smalldatetime,CONVERT(varchar(6),'"+ fechaDesde + "', 112),112) And convert(smalldatetime,CONVERT(varchar(6),'"+ fechaHasta + "', 112),112))  and ";
				
				
				if (datosReporte.getTodosClientes().equals("true")){
					sql = sql + " g.genteNr in " + datosReporte.getClientes() + " and ";
				}else{
					sql = sql + " g.genteNr Between " + datosReporte.getAgendadoDesde() + " And " + datosReporte.getAgendadoHasta() + " and ";
				}
				
				sql = sql +
				" i.articulo Between '" + datosReporte.getArtDesde() + "' And '" + datosReporte.getArtHasta() + "'" +
				" group by g.genteNr,g.razonSocial,i.articulo, s.descripcion " + 
				" order by g.genteNr,i.articulo";
				
				estadisticaPedidoList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return estadisticaPedidoList;
			
		}
		
		public List<EstadisticaPedido> getEstadisticaVentas(DatosReporte datosReporte) {
			
			List<EstadisticaPedido> estadisticaPedidoList = null;
			String fechaDesde = DateUtil.composeCanonicalAAAAString(datosReporte.getFechaDesde());
			String fechaHasta = DateUtil.composeCanonicalAAAAString(datosReporte.getFechaHasta());
//			public EstadisticaPedido(String razonSocial, String descripArt,
//					String articulo, Integer genteNr, BigDecimal cantidad,BigDecimal cant2,
//					BigDecimal precio,BigDecimal saldo,Number precioProme) {

			try{								
				String sql = "select new jmc.skweb.core.model.report.EstadisticaPedido(g.razonSocial, s.descripcion,i.articulo, g.genteNr,  " + 
				" Sum(i.cant1 * (CASE i.tipoComprob.nr WHEN 2 THEN -1 WHEN 12 THEN -1 WHEN 11 THEN -1 ELSE 1 END)) , " +
				" Sum(i.cant2 * (CASE i.tipoComprob.nr WHEN 2 THEN -1 WHEN 12 THEN -1 WHEN 11 THEN -1 ELSE 1 END)) , " +
				" Sum(CASE i.cant1 WHEN 0 THEN (i.cant1entregado * (i.precio - (i.precio *(i.bonif / 100))) * (CASE i.tipoComprob.nr WHEN 2 THEN -1 WHEN 12 THEN -1 WHEN 11 THEN -1 ELSE 1 END)) ELSE " +
				" (i.cant1 * ((i.precio - (i.precio *(i.bonif / 100)))) * (CASE i.tipoComprob.nr WHEN 2 THEN -1 WHEN 12 THEN -1 WHEN 11 THEN -1 ELSE 1 END)) END), " +
				"(sum(i.cant1) - sum (i.cant1entregado)) as saldo," +
				" avg(i.precio - (i.precio * (i.bonif / 100)))) " +
				" from Items i, Gente g, " +
				" Stock s where " + 
				" i.articulo  = s.clave and " + 
				" i.genteNr = g.genteNr and " +
				" i.tipoComprob.nr IN (1,2,7,9) and " +
				" (i.fecha Between  convert(smalldatetime,CONVERT(varchar(6),'"+ fechaDesde + "', 112),112) And convert(smalldatetime,CONVERT(varchar(6),'"+ fechaHasta + "', 112),112))  and ";
				
				if (datosReporte.getTodosClientes().equals("true")){
					sql = sql + " g.genteNr in " + datosReporte.getClientes() + " and ";
				}else{
					sql = sql + " g.genteNr Between " + datosReporte.getAgendadoDesde() + " And " + datosReporte.getAgendadoHasta() + " and ";
				}
				sql = sql + " i.articulo Between '" + datosReporte.getArtDesde() + "' And '" + datosReporte.getArtHasta() + "'" +
				" group by g.genteNr,g.razonSocial,i.articulo, s.descripcion " + 
				" order by g.genteNr,i.articulo";
				
				estadisticaPedidoList = getHibernateTemplate().find(sql);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				}
			return estadisticaPedidoList;
			
		}

		
		
	}
