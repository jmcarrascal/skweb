package jmc.skweb.util;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import jmc.skweb.core.model.Items;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.StockPiezas;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.report.TransacJoin;
import jmc.skweb.core.model.shortEntities.GenteBasic;
import jmc.skweb.core.model.shortEntities.OptionsSelect;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FormatUtil {
	
	
    private static String llenarConCero(String s, int width)
    {
        String formattedString;

        // La serie es m�s corta que la anchura especificada,
        // por lo que tenemos que rellenarla con blancos.
        if (s.length() < width) {
            StringBuffer buffer = new StringBuffer (s);
            for (int i = s.length(); i < width; ++i){
                buffer.append ("0");
            }
            formattedString = buffer.toString();
        }else{
            formattedString = s.substring (0, width);
        }
        return formattedString;
    }

	public static Long formatCuit(String cuit) throws Exception
	{
	    Long cuitFormated = 0l;
		System.out.println("Cuit " + cuit);
	    cuit = cuit.trim().replaceAll("-", "");
	    cuit = cuit.trim().replaceAll(" ", "");
	    try{
	    	cuitFormated = Long.parseLong(cuit);
	    }catch(NumberFormatException e){
	    	e.printStackTrace();
	    }		
		return cuitFormated;
	}

	public static boolean validar(String cadena, Long[] valores){
        
    	char[] nroClave = cadena.toCharArray();
        int checksuma=0;
        
        for(int i=0;i<11;i++){
            checksuma  += Integer.parseInt( String.valueOf( nroClave[i] ))*i;
        }                
        
        for(Long valor : valores){
	        System.out.println("comparo valor: " + valor + "checksuma =" + checksuma);
        	if (checksuma==valor)
	                return true;                	
        }
        return false;
    }
	
	public static String getError(String valorInt){		
		if (valorInt != null){
			int i = valorInt.indexOf("|", 1);
			while (i != -1){			 
				valorInt = valorInt.substring(i);
				i = valorInt.indexOf("|", 1);
				
			}
			return valorInt.replaceFirst("\\|", "");					
		}else{
			return null;
		}
	}

	public static String llenoConCeros(String valor, int longitud){
		while(valor.length() < longitud){
			valor = "0" + valor;
		}
		return valor;
	}

	public static String llenoDosCeros(String valor){
		int valorN = 0;
		String result = "00"; 
		try{
			valorN = Integer.parseInt(valor);
			result = llenoConCeros(String.valueOf(valorN), 2);
		}catch(Exception e){
			
		}
		return result;
	}

	public static double redondearEn2(double numero)
	{
	       return Math.rint(numero*100)/100;
	}
	
	public static double redondearEn6(double numero)
	{
	       return Math.rint(numero*1000000)/1000000;
	}

	public static byte[] getFacturasExcel(List<String> facturasPerdidas){
		
        HSSFWorkbook libro = new HSSFWorkbook();

        // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet();

        // Se crea una fila dentro de la hoja
        HSSFRow fila = hoja.createRow(1);

        // Se crea una celda dentro de la fila.
        HSSFCell prefijo = fila.createCell((short) 0);
        HSSFCell tipoCompr = fila.createCell((short) 1);	        
        HSSFCell nroComprobante = fila.createCell((short) 2);
        HSSFCell cae = fila.createCell((short) 3);
        HSSFCell vtoCae = fila.createCell((short) 4);
        HSSFCell impTotal = fila.createCell((short) 5);
        HSSFCell impNeto = fila.createCell((short) 6);	        
        HSSFCell impTrib = fila.createCell((short) 7);
        HSSFCell impIVA = fila.createCell((short) 8);
        HSSFCell docNro = fila.createCell((short) 9);
        HSSFCell docTipo = fila.createCell((short) 10);
        HSSFCell cbteFch = fila.createCell((short) 11);               
        
        // Se crea el contenido de la celda y se mete en ella.        
        prefijo.setCellValue("Punto de Vta");
        tipoCompr.setCellValue("Tipo Comprobante");
        nroComprobante.setCellValue("Numero Comprobante");        
        cae.setCellValue("Nro CAE");
        vtoCae.setCellValue("Vto. CAE");
        impTotal.setCellValue("Importe Total");
        impNeto.setCellValue("Importe Neto");
        impTrib.setCellValue("Importe Tributo");        
        impIVA.setCellValue("Importe IVA");
        docNro.setCellValue("Numero Doc");
        docTipo.setCellValue("Tipo de Doc");
        cbteFch.setCellValue("Fecha Cbte");
        
        int i = 2;
        for(String factura : facturasPerdidas){
            HSSFRow filaNew = hoja.createRow(i);

            // Se crea una celda dentro de la fila
            HSSFCell prefijoNew = filaNew.createCell((short) 0);
            HSSFCell tipoComprNew = filaNew.createCell((short) 1);	        
            HSSFCell nroComprobanteNew = filaNew.createCell((short) 2);
            HSSFCell caeNew = filaNew.createCell((short) 3);
            HSSFCell vtoCaeNew = filaNew.createCell((short) 4);
            HSSFCell impTotalNew = filaNew.createCell((short) 5);
            HSSFCell impNetoNew = filaNew.createCell((short) 6);	        
            HSSFCell impTribNew = filaNew.createCell((short) 7);
            HSSFCell impIVANew = filaNew.createCell((short) 8);
            HSSFCell docNroNew = filaNew.createCell((short) 9);
            HSSFCell docTipoNew = filaNew.createCell((short) 10);
            HSSFCell cbteFchNew = filaNew.createCell((short) 11);               
            
            
            // Se crea el contenido de la celda y se mete en ella.
            //HSSFRichTextString texto = new HSSFRichTextString("hola mundo");
            
        	String[] campos = factura.split("\\;");
        	try{
        		prefijoNew.setCellValue(campos[0]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		tipoComprNew.setCellValue(campos[1]);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		nroComprobanteNew.setCellValue(campos[2]);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{
        		caeNew.setCellValue(campos[3]);        		
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		vtoCaeNew.setCellValue(campos[4]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}

        	try{        		
        		impTotalNew.setCellValue(campos[5]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		impNetoNew.setCellValue(campos[6]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		impTribNew.setCellValue(campos[7]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{        		
        		impIVANew.setCellValue(campos[8]);	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{
        		docNroNew.setCellValue(campos[9]);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	try{
        		docTipoNew.setCellValue(campos[10]);
        	}catch(Exception e){
        		e.printStackTrace();
        	}try{
        		cbteFchNew.setCellValue(campos[11]);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	i++;
        }
        // Se salva el libro.
        FileOutputStream elFichero = null;
        String nombreArchivo = "c:\\T_" +System.currentTimeMillis()+".txt";
        byte[] array = null;
        try {
        	
        	elFichero = new FileOutputStream(nombreArchivo);
            libro.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		try {
			array = FileUtil.getBytesFromFile(new File(nombreArchivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File fileDelete = new File(nombreArchivo);
		fileDelete.delete();

        return array;
    }
	

	

		
		
	public static byte[] mergeJPG(byte[] image1, byte[] image2){
		ByteArrayInputStream bi1 = new ByteArrayInputStream (image1);
		ByteArrayInputStream bi2 = new ByteArrayInputStream (image2);
		BufferedImage image = null;
		BufferedImage overlay = null;
		try {
		image = ImageIO.read(bi1);
		overlay= ImageIO.read(bi2);
		} catch (IOException e) {
		e.printStackTrace();
		}

		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h*2, BufferedImage.TYPE_BYTE_GRAY);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, h, null);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
		ImageIO.write(combined, "jpg", bas);
		} catch (IOException e) {
		e.printStackTrace();
		}
		byte[] data = bas.toByteArray();
		return data;
		}
	
	public static String getSignoRow(Integer tipoComprob){
		if (tipoComprob == 2 || tipoComprob == 4 || tipoComprob == 6 || tipoComprob == 10){
			return "red";
		}else{
			return "black";
		}
	}
	
	public static String getSignoPorNegativoRow(Float valor){
		if (valor <  0){
			return "red";
		}else{
			return "black";
		}
	}
	
	public static String getSignoPorNegativoRow(Double valor){
		if (valor == null){
			return "black";
		}
		if (valor <  0){
			return "red";
		}else{
			return "black";
		}
	}

	public static BigDecimal getSaldoCalculado(Integer tipoComprob, BigDecimal saldo){
		if (tipoComprob == 2 || tipoComprob == 4 || tipoComprob == 6 || tipoComprob == 10){			
			return saldo.multiply(BigDecimal.valueOf(-1l));
		}else{
			return saldo;
		}
	}
	
	public static BigDecimal getSaldoCalculado(Short factCtaCte, BigDecimal saldo){
					
		return saldo.multiply(BigDecimal.valueOf(factCtaCte));
		
	}
	

	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	public static String getListHTMLGenteBasic(List<GenteBasic> genteList){			

		String data = "";
		int sizeList= genteList.size();
		if (sizeList > 10)
			sizeList = 10;
		String header = " <select onclick=\"callSelectList();\" name=\"drop1\" id=\"idAutoCompleteManual\" size=\""+sizeList+"\" >";
		String options = "";
		String footer = " </select> ";
		for(GenteBasic gente: genteList){
			String option = "<option value=\""+gente.getGenteNr()+"\">" + gente.getDescripC()+"</option>";
			options = options + option;
		}		
		data = header + options + footer;
		
		return data;
	}

	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	public static String getListHTMLStock(List<Stock> stockList){			

		String data = "";
		int sizeList= stockList.size();
		if (sizeList > 10)
			sizeList = 10;
		//String header = " <select onclick=\"callSelectList();\" name=\"drop1\" id=\"idAutoCompleteManual\" size=\""+sizeList+"\" class = \"AutoComplete\">";
		String header = " <select onchange=\"callSelectList();\" name=\"drop1\" id=\"idAutoCompleteManual\" class = \"AutoComplete\">";
		String options = "";
		String footer = " </select> ";
		if (stockList.size() > 0){  
			options = options + "<option value=>Seleccione un articulo</option>";
			for(Stock stock: stockList){
				String option = "<option value=\""+stock.getClave()+"\">" + stock.getDescripC()+"</option>";
				options = options + option;
			}		
			data = header + options + footer;
			}
		return data;
	}
	
/*	
	<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class = \"uiTableBlue\"><tr><th>Agendado</th><th>Razon Social</th><th>NrComprob/Transac</th><th>Fecha</th><th>IntNr</th><th>CantPedido</th><th>CantEntregado</th><th>Saldo</th><th>Fecha Entrega</th><th><img alt=\"\" src=\"images/general/delete.png\" title=\"Ocultar Tabla\" onclick=\"CloseUiTableBlue();\"></th></tr>
	
	<tr><td>122</td><td>La Filomena</td><td>0000090/123432</td><td>10/01/2012</td><td>1</td><td>1232.00</td><td>123.00</td><td>123.89</td><td>10/08/2012</td></tr></table>
	*/
	
	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	public static String getTableHTMLPedidoResumen(List<TransacJoin> transacJoinList, String tipoComprob, boolean usaLogicaColor){			

		
		String data = "";
		String sizeVertical = "auto";
		int sizeList= transacJoinList.size();
		if (sizeList > 8)
			sizeVertical = "200px";
		
		String style = "<style type=\"text/css\">#scroller {width:450px;height:"+sizeVertical+";overflow:auto;}</style>";
		String header ="";
				
		if (!usaLogicaColor){
			header = "<div id=\"scroller\">" +
					"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class = \"uiTableBlue\">" +
					"<tr><th width=\"100%\" colspan=\"9\">"+tipoComprob+"</th></tr>" +
					"<th>Saldo</th>" +
					"<th>Fecha Entrega</th></tr>";
			
		}else{
			header = "<div id=\"scroller\">" +
					"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class = \"uiTableBlue\">" +
					"<tr><th width=\"100%\" colspan=\"9\">"+tipoComprob+"</th></tr>" +
					"<th>Saldo</th>" +
					"<th>Fecha Entrega</th>"+
					"<th>Colores</th>"
					+ "</tr>";

		}
		String options = "";
		int i = 0;
		for(TransacJoin transac : transacJoinList){
			
			String color = "";
			if (MathUtil.esPar(i))
				color = "#BDBDBD";
			else
				color = "#83aec0";
			String option = "";
			
			if(usaLogicaColor){
				String resumenColor = "[" + transac.getColores().getNr() + "] " + transac.getColores().getDescrip() + "   ";
//				for(Items item: transac.getItemsList()){
//					if (resumenColor == ""){
//						resumenColor = "[" + item.getColores().getNr() + "] " + item.getColores().getDescrip() + " Cant: " + item.getCant1();
//					}else{
//						resumenColor = resumenColor + "<br>" +  "[" + item.getColores().getNr() + "] " + item.getColores().getDescrip() + " Cant: " + item.getCant1();
//					}
//				
//					
//				}
				
				option = "<tr>" +
						"<td bgcolor=\""+color+ "\">"+MathUtil.redondearEn2BD(transac.getSaldoCantidad())+"</td>" +
						"<td bgcolor=\""+color+ "\">"+transac.getFormatFechaEntrega()+"</td>" +
						"<td bgcolor=\""+color+ "\">"+resumenColor+"</td>" +
						"</tr>";
				
			}else{
				option = "<tr>" +
						"<td bgcolor=\""+color+ "\">"+transac.getSaldoCantidad()+"</td>" +
						"<td bgcolor=\""+color+ "\">"+transac.getFormatFechaEntrega()+"</td>" +
						"</tr>";
				
			}
			options = options + option;
			i++;
		}
		
		String footer = " </table> </div>";
				
		data = style + header + options + footer;
		
		return data;
	}

	
	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	public static String getTableHTMLPedido(List<TransacJoin> transacJoinList, String tipoComprob){			

		
		String data = "";
		String sizeVertical = "auto";
		int sizeList= transacJoinList.size();
		if (sizeList > 8)
			sizeVertical = "200px";
		
		String style = "<style type=\"text/css\">#scroller {width:700px;height:"+sizeVertical+";overflow:auto;}</style>";
		
		String header = "<div id=\"scroller\">" +
				"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class = \"uiTableBlue\">" +
				"<tr><th width=\"100%\" colspan=\"9\">Tipo de Comprobante: "+tipoComprob+"&nbsp;&nbsp;&nbsp;&nbsp;<img alt=\"\" src=\"images/general/delete.png\" title=\"Ocultar Tabla\" onclick=\"CloseUiTableBlue();\"></th></tr>" +
				"<tr><th>Agendado</th><th>Razon Social</th><th>NrComprob/Transac</th><th>Fecha</th><th>IntNr</th><th>CantPedido</th><th>CantEntregado</th><th>Saldo</th><th>Fecha Entrega</th></tr>";
//		String options = "<tr><td>122</td><td>La Filomena</td><td>0000090/123432</td><td>10/01/2012</td><td>1</td><td>1232.00</td><td>123.00</td><td>123.89</td><td>10/08/2012</td></tr></table>";
		String options = "";
		int i = 0;
		for(TransacJoin transac : transacJoinList){
			String color = "";
			if (MathUtil.esPar(i))
				color = "#BDBDBD";
			else
				color = "#83aec0";
				
			
			String option = "<tr><td bgcolor=\""+color+ "\">"+transac.getTransacNr()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getRazonSocial()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getNrComprob()+"/"+transac.getTransacNr()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getFormatFecha()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getNrInt()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getCant1()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getCant1Entregado()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getSaldoCantidad()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+transac.getFormatFechaEntrega()+"</td>" +
					"</tr>";
			options = options + option;
			i++;
		}
		
		String footer = " </table> </div>";
				
		data = style + header + options + footer;
		
		return data;
	}

	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 * @param transac 
	 */
	public static String getTableHTMLItemsCarrito(List<Items> itemsList, String cliente, Transac transac){					
		String data = "";
		String sizeVertical = "auto";
		int sizeList= itemsList.size();
		if (sizeList > 8)
			sizeVertical = "200px";
		
		String style = "<style type=\"text/css\">#scroller {width:750px;height:"+sizeVertical+";overflow:auto;}</style>";
		
		String header = "<div id=\"scroller\">" +
				"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class = \"uiTableBlue\">" +
				"<tr><th width=\"100%\" colspan=\"9\">Agendado: "+cliente+"&nbsp;&nbsp;&nbsp;&nbsp;</th></tr>" +
				"<tr><th>Eliminar</th>" +
				"<th>Orden</th>" +
				"<th>Clave</th>" +
				"<th>Cantidad</th>" +
				"<th>Descripci�n</th>" +
				"<th>Precio</th>" +
				"<th>Por Impuesto</th>" +
				"<th>Bonif</th>" +
				"<th>Total Item</th></tr>";
		String options = "";
		int i = 0;
		for(Items items : itemsList){
			String color = "";
			if (MathUtil.esPar(i))
				color = "#FE9A2E";
			else
				color = "#FF8000";
			String option = 
					"<tr><td onclick='deleteItem("+items.getId().getItemNr()+");' bgcolor=\""+color+ "\"><img alt='' src='images/general/cerrarX_mini.png'></td>" +
					"<td bgcolor=\""+color+ "\">"+items.getId().getItemNr()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getClave()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getCant1()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getDescrip()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+MathUtil.redondearEn2BD(items.getPrecio())+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getPorcentajeImpuesto()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getBonif()+"</td>" +
					"<td bgcolor=\""+color+ "\">"+items.getTotalItem()+"</td>" +
					"</tr>";
			options = options + option;
			i++;
		}
		
		String footer = " </table> </div> <br>";
		
		String totales = "" +
		"<div align=\"center\">" + 
		"<div><b><label class=\"subTotalesTitle\">SubTotal:</label></b>" +
		"<label class=\"subTotales\"  id=\"transac.netoGrav\">"+transac.getNetoGrav() +"</label>" +
		"</div><div><b><label class=\"subTotalesTitle\">Impuestos:</label></b>" +
		"<label class=\"subTotales\" id=\"transac.iva\">"+transac.getIva() +" </label>" +
		"</div><div><b><label class=\"totalesTitle\">Total:</label></b>" +
		"<label class=\"totales\" id=\"transac.iva\">"+transac.getTotal() +"</label></div></div>";
				
				
		data = style + header + options + footer + totales;
		
		return data;
	}

	public static String getListHTMLStockPiezas(
			List<StockPiezas> stockPiezasList, Double semaforizado, boolean muestroEntradaCantidad, boolean isCarrito, String urlBase, boolean muestroBarraLateral) {
		
		String data = "";
		String sizeVertical = "auto";
		int sizeList= stockPiezasList.size();
		if (muestroBarraLateral){
			if (sizeList > 8)
				sizeVertical = "120px";
		}else{
			sizeVertical = "auto";
		}
		String style = "<style type=\"text/css\">#scroller {width:400px;height:"+sizeVertical+";overflow:auto;}</style>";
		
		String header = "<div id=\"scroller\">" +
				"<table cellpadding=\"0\" cellspacing=\"0\" border=\"1\" class = \"uiTableBlue\">" +
				"<tr><th width=\"100%\" colspan=\"18\">"+"Stock Por Piezas"+"</th></tr>" +
				"<th>Color</th>" +
				"<th>Stock</th>" +
				"<th>Compra - Venta</th>" ;
		if (isCarrito){
			header = header + "<th>Cantidad</th>";
			header = header + "<th>Accion</th></tr>";	
		}else{
			if (muestroEntradaCantidad){
				header = header + "<th>Cantidad</th></tr>";
			}else{
				header = header + "</tr>";
			}
		}
		
			
//		String options = "<tr><td>122</td><td>La Filomena</td><td>0000090/123432</td><td>10/01/2012</td><td>1</td><td>1232.00</td><td>123.00</td><td>123.89</td><td>10/08/2012</td></tr></table>";
		String options = "";
		int i = 0;
		for(StockPiezas stockPiezas : stockPiezasList){
			String color = "";
			if (MathUtil.esPar(i))
				color = "#BDBDBD";
			else
				color = "#83aec0";
			
			stockPiezas.getId().getColores().getDescrip();
			String option = "<tr>" +
					"<td bgcolor=\""+color+ "\">"+"["+stockPiezas.getId().getColores().getNr() + "]" +stockPiezas.getId().getColores().getDescrip()+"</td>";
					if (semaforizado == 0d){
						option = option + "<td bgcolor=\""+color+ "\">"+stockPiezas.getImagenSemaforo()+"</td>";
					}else{
						option = option + "<td align = \"right\" bgcolor=\""+color+ "\">"+MathUtil.redondearEn2(stockPiezas.getCant1())+"</td>";
					}
					option = option + "<td bgcolor=\""+color+ "\">"+" " + stockPiezas.getComprasmenosventas() + " " + "</td>";
					if (isCarrito){
						option = option + "<td bgcolor=\""+color+ "\">"+ "<input type=\"text\" size=\"10\" maxlength=\"10\" name=\"piezas\" id = '"+ "cantCarr" + stockPiezas.getId().getStock().getClaveSBarras()+ stockPiezas.getId().getColores().getNr() +"'>"+"</td>";
						//Agrego imagen para adicionar item.
						option = option + "<td bgcolor=\""+color+ "\">"+ " <input name='button' style='background:url(\"" + urlBase + "/images/general/add.png\") 0 0 no-repeat;border:none; width:50px;' value='' type='button' onclick=\"addItemColor_CARR('"+ stockPiezas.getId().getStock().getClave() + "', '"+ stockPiezas.getId().getStock().getClaveSBarras() + "', '"+stockPiezas.getId().getColores().getNr()+"' );\">"+"</td></tr>";
					}else{
						if (muestroEntradaCantidad){
							option = option + "<td bgcolor=\""+color+ "\">"+ "<input type=\"text\" size=\"10\" maxlength=\"10\" name=\"piezas\">"+"</td></tr>";
						}else{
							option = option + "</tr>";
						}		
					}
			options = options + option;
			i++;
		}
		
		String footer = " </table> </div>";
				
		data = style + header + options + footer;
		
		return data;
	}

	public static String getOptionsSelectListHTML(
			List<OptionsSelect> optionsList) {
		String options = "";
		for(OptionsSelect optionsSelect: optionsList){
			String option = "<option value=\""+optionsSelect.getId()+"\">" + optionsSelect.getValue()+"</option>";
			options = options + option;
		}		
		return options;
	}

}

