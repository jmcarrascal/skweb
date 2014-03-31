package jmc.skweb.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Transient;



/**
 * @author Juan Manuel Carrascal
 * @version 1.0
 * @created 12-Jan-2010 11:19:57 AM
 */


public class DateUtil {
	
	@Transient
	public static boolean getDaysCompareEntryToNow(Timestamp fecEntrada, long days){
		boolean out = false;
		Date date = new Date();
		Timestamp fechaActual = new Timestamp(date.getTime());
		//84600000 milliseconds in a day
		long oneDay = (1 * 24 * 60 * 60 * 1000);
		long tsEntrada = fecEntrada.getTime();
		long tsActual = fechaActual.getTime();
		
		long tsResult =   tsActual - tsEntrada;
									
		long day = tsResult / oneDay;
		
		if (day > days){
			out = true;
		} 
		return out;		
	}
	
	public static Timestamp restaTimestampByDays(Timestamp fecha, Long days) {
		Long oneDay = (1l * 24l * 60l * 60l * 1000l);
		days = oneDay * days;
		Long tsResult = fecha.getTime() - days;
		fecha.setTime(tsResult);		
		return fecha;
	}
	
	public static Timestamp sumaTimestampByDays(Timestamp fecha, Long days) {
		Long oneDay = (1l * 24l * 60l * 60l * 1000l);
		days = oneDay * days;
		Long tsResult = fecha.getTime() + days;
		fecha.setTime(tsResult);		
		return fecha;
	}
	public static String difTimeStampToTimeStamp(Timestamp fechaInicial, Timestamp fechaFinal) {
		String result = "";

		long oneDay = (24 * 60 * 60 * 1000);
		long oneHour = (60 * 60 * 1000);
		long oneMinute = (60 * 1000);
		if (fechaFinal==null){
			Date date = new Date();
			fechaFinal = new Timestamp(date.getTime());
		}
		Long tsEntrada = fechaInicial.getTime();
		Long tsActual = fechaFinal.getTime();
		
		long tsResult =  tsActual - tsEntrada;
		
		
		Timestamp tiempoDif = new Timestamp(tsResult);			
		
		System.out.println("Resultado " + tiempoDif);
		/*
		 * Obtengo los dias
		 */
		Long dias = tsResult / oneDay;
		/*
		 * Rescato el resto de la divicion de dias para obtener las horas.
		 */
		Long restoDias = tsResult % oneDay;
		/*
		 * Obtengo las horas en base del resto de los dias.
		 */						
		Long horas = restoDias / oneHour;
		/*
		 * Rescato el resto de la divicion de horas para obtener las minutos.
		 */
		Long restoHoras = restoDias % oneHour;
		/*
		 * Obtengo los minutos en base del resto de los Horas.
		 */	
		Long minutos = restoHoras / oneMinute;
		
		/*
		System.out.println("dias " + dias);
		System.out.println("horas " + horas);
		System.out.println("minutos " + minutos);
		*/
		result = dias + "d " + horas + "h " + minutos + "m";
		
		return result;
	}
	
	public static String getMonthName(int monthNumber) {
        String month = null;  
		switch (monthNumber) {
            case 0: month = " Enero ";break;
            case 1: month = " Febrero "; break;
            case 2: month = " Marzo "; break;
            case 3: month = " Abril "; break;
            case 4: month = " Mayo "; break;
            case 5: month = " Junio "; break;
            case 6: month = " Julio "; break;
            case 7: month = " Agosto "; break;
            case 8: month = " Septiembre "; break;
            case 9: month = " Octubre "; break;
            case 10: month = " Noviembre "; break;
            case 11: month = " Diciembre "; break;
        }
        return month;
	}
	
	public static String getFormattedDate(Date date){
		
		int day,month,year;
		Calendar cal = new GregorianCalendar();
		String result = "";
		if(date != null){
			try{
				cal.setTime(date);				
				day = cal.get(Calendar.DATE);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);				
				result = String.valueOf(day) + " de" + getMonthName(month) + "de " + String.valueOf(year);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	public static String getFormattedDateSTD(Date date){
		
		int day,month,year;
		Calendar cal = new GregorianCalendar();
		String result = "";
		if(date != null){
			try{
				cal.setTime(date);				
				day = cal.get(Calendar.DATE);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);				
				result = String.valueOf(day) + " de" + getMonthName(month) + "de " + String.valueOf(year);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Timestamp composeDate(String date){
		Calendar cal=new GregorianCalendar();
		int year = Integer.parseInt(date.substring(date.lastIndexOf("/")+1,date.length()));
		int month = Integer.parseInt(date.substring(date.indexOf("/")+1,date.lastIndexOf("/")))-1;
		int day = Integer.parseInt(date.substring(0, date.indexOf("/")));
		cal.set(year, month, day);
		return new Timestamp(cal.getTime().getTime());
	} 
	public static Timestamp composeDateGuion(String date){
		Calendar cal=new GregorianCalendar();
		int year = Integer.parseInt(date.substring(date.lastIndexOf("-")+1,date.length()));
		int month = Integer.parseInt(date.substring(date.indexOf("-")+1,date.lastIndexOf("-")))-1;
		int day = Integer.parseInt(date.substring(0, date.indexOf("-")));
		cal.set(year, month, day);
		return new Timestamp(cal.getTime().getTime());
	} 
	
	public static String getShortFormattedDate(Date date){
		
		int day,month,year;
		Calendar cal = new GregorianCalendar();
		String result = "--";
		if(date != null){
			cal.setTime(date);
			
			day = cal.get(Calendar.DATE);
			month = cal.get(Calendar.MONTH);
			year = cal.get(Calendar.YEAR);
			
			result = FormatUtil.llenoConCeros(String.valueOf(day),2) + "/" + FormatUtil.llenoConCeros(String.valueOf(month+1),2) + "/" + String.valueOf(year);
		}
		return result;
	}
	
	
	public static String getFormatedSTDDate (Timestamp date){
		if (date != null){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return (date==null)?" - ":format.format(new Date(date.getTime()));
		}else{
			return null;
		}

	}
	
	public static Timestamp getFormatSkDate (Timestamp date){
		return composeCanonicalFech(getFormatedCanonical(date));
	}
	
	public static String getFormatedCanonical (Timestamp date){
		if (date != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			return (date==null)?" - ":format.format(new Date(date.getTime()));
		}else{
			return null;
		}
	}
	
	public static String getFormatedShortDate (Date date){
		if (date != null){
			SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
			return (date==null)?" - ":format.format(new Date(date.getTime()));
		}else{
			return null;
		}

	}
	public static String getCanonicalFech(Date date)  throws Exception{
		String result = "";		
		if (date != null){
			int day,month,year;
			Calendar cal = new GregorianCalendar();

			if(date != null){
				cal.setTime(date);
				
				day = cal.get(Calendar.DATE);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);				
				result = FormatUtil.llenoConCeros(String.valueOf(year),4) + FormatUtil.llenoConCeros(String.valueOf(month +1),2) + FormatUtil.llenoConCeros(String.valueOf(day),2) ;				
			
		}
		}
		return result;
	}
	
	public static String composeCanonicalString(String fecha){
		Timestamp result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		java.util.Date parsedDate = null;
		if(fecha != null && !fecha.equals("")){
			try {
				parsedDate = sdf.parse(fecha);
				result =new java.sql.Timestamp(parsedDate.getTime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
		}		
		if (result != null){
			SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
			return (result==null)?" - ":format.format(new Date(result.getTime()));
		}else{
			return null;
		}		
	}
	
	public static String composeCanonicalAAAAString(String fecha){
		Timestamp result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsedDate = null;
		if(fecha != null && !fecha.equals("")){
			try {
				parsedDate = sdf.parse(fecha);
				result =new java.sql.Timestamp(parsedDate.getTime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
		}		
		if (result != null){
			SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
			return (result==null)?" - ":format.format(new Date(result.getTime()));
		}else{
			return null;
		}		
	}

	public static Timestamp composeCanonicalFech(String fecha){
		Timestamp result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		java.util.Date parsedDate = null;
		if(fecha != null && !fecha.equals("")){
			try {
				parsedDate = sdf.parse(fecha);
				result =new java.sql.Timestamp(parsedDate.getTime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
		}		
		return result; 
	}

	public static Timestamp composeCanonicalFechPresentation(String fecha){
		Timestamp result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsedDate = null;
		if(fecha != null && !fecha.equals("")){
			try {
				parsedDate = sdf.parse(fecha);
				result =new java.sql.Timestamp(parsedDate.getTime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
		}		
		return result; 
	}

	public static Date composeCanonicalFechPresentationDate(String fecha){
		Date result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsedDate = null;
		if(fecha != null && !fecha.equals("")){
			try {
				parsedDate = sdf.parse(fecha);
				result =new Date(parsedDate.getTime());
			} catch (ParseException e) {			
				e.printStackTrace();
			}			
		}		
		return result; 
	}

}
