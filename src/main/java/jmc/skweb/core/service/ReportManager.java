package jmc.skweb.core.service;


import java.math.BigDecimal;
import java.util.List;

import jmc.skweb.core.model.ClieArticPrecio;
import jmc.skweb.core.model.Condi;
import jmc.skweb.core.model.Domicilios;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.TipoComprob;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.report.DatosReporte;
import jmc.skweb.core.model.report.TipoReporte;
import jmc.skweb.core.model.traza.Trazabi;






/**
 * @author Juan Manuel Carrascal
 *
 */
public interface ReportManager {

	byte[] generateEstadiReport(DatosReporte datosReporte, Usuario usuarioSesion);

	

	}
