package jmc.skweb.core.security.filter;




import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.security.filter.validate.ValidacionCache;
import jmc.skweb.core.security.filter.validate.ValidateDataCertificate;


/**
 * @author Juan Manuel Carrascal
 * @version 1.0
 * @created 12-Jan-2010 11:19:57 AM
 */


public final class FilterAccess implements Filter
{
	// parametroos de configuracion
   	private FilterConfig configuracionFiltro = null;
   	private String deploySistem = "";


   public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException
	{	
	   
	   	   
	   HttpServletRequest requestHttp = (HttpServletRequest) request;
	   HttpServletResponse responseHttp = (HttpServletResponse) response;		
	   
	   
	   try{		   
		   	/*
		   	 * Pregunta si el usuario est� logeado	   		   	
		   	 */
		   	Usuario usuario = null;
		   	try{
		   		usuario = (Usuario)requestHttp.getSession().getAttribute("usuario");	
		   	}catch(Exception e){
		   		e.printStackTrace();		   		
				request.setAttribute("usuario", null);				
		   	}
		   	
		   	
		   	/*
			 * Validar el rol del usuario en base a su URL (Implementaci�n fuera del ambiente de Prueba Piloto).
			 */
			
		   	//System.out.println(requestHttp.getRequestURI());				
		   	int idPerfil = 0;
		   	if (usuario != null){
		   		idPerfil = usuario.getRol();		   		
		   	}
		   	
		   	deploySistem = requestHttp.getContextPath();
		   	
			String action = getAction(requestHttp.getRequestURI());
			action = action.replaceAll(".action", "");
			System.out.println(requestHttp.getRequestURI());
			System.out.println("Entro a la accion out");
			Boolean valida = false;
			if(action.equals("out")){
				valida = true;
			}else{
				valida = ValidacionCache.getInstance().validateUrl(action, String.valueOf(idPerfil));	
			}
			
//			//tmp
			
			valida = true;
			if (!valida){
				String finalurl = (this.getPrefix(requestHttp) + deploySistem + "/out");
				System.out.println("URL no valido " + action);
				usuario = null;
				requestHttp.getSession().setAttribute("usuario", null);
				responseHttp.sendRedirect(finalurl);	
			
			}else
			{
			   	
			   	if ( usuario != null){
		   								
					/*
					 * En el caso que el usuario est� logeado pasa y tiene los permisos correspondientes pasa a la URL requerida	   							
					 */
					
					chain.doFilter(request, response);	   			
				}else{			
					chain.doFilter(request, response);			
				}
			}
	   }catch(Exception e){
		    e.printStackTrace();
     		String finalurl = (this.getPrefix(requestHttp) + deploySistem +"/out");
     		request.setAttribute("usuario", null);
			System.out.println("finalUrl " + finalurl);
			responseHttp.sendRedirect(finalurl);
		   
	   }
		
    }

    public void init(FilterConfig pConfiuracionFiltro) throws ServletException
    {
		configuracionFiltro = pConfiuracionFiltro;
    }

    public String toString()
    {

		if (configuracionFiltro == null)
		    return ("FiltroTiempo()");

		StringBuffer sb = new StringBuffer("FiltroTiempo(");
		sb.append(configuracionFiltro);
		sb.append(")");
		return (sb.toString());
    }
    
    public String getPrefix(HttpServletRequest request)
    {
       StringBuffer str = request.getRequestURL();
       String url = str.toString();
       String uri = request.getRequestURI();
       int offset = url.indexOf(uri);
       String prefix = url.substring(0,offset);
       return prefix;
    }
    
    /*
     * Obtengo el URL solicitado, analizo segun los siguientes criterios
     * 	1- En el caso que sea el HTML del formulario de Login por MS pasa el requerimiento (En etapa de prueba piloto van a 
	 *     pasar todos los *.html,*.js, *.css).
	 * 	2- En el caso que sea igual a LOGIN_MS pasa el requerimiento
	 *  3- En el caso que sea igual a LOGIN_P tomo el certificado desde el request y autentico.
	 *  4- Esta fuera de alcance
	 *  
	 *  Esta implementaci�n va a ser modificadas cuando los roles sean persistentes en la base de datos
	 *  FALTA ANALIZAR INVOCACION POR PARAMETROS PARA PODER FILTRAR	
     */
    public Integer getTipoRequest(String url)
    {
    	//Analizo si tengo extenciones
    	
    	String nombre_antecedentes = deploySistem;
    	url = url.replaceAll(nombre_antecedentes, "");
    	String[] listaCarpetas = url.split("/");
    	String urlAnalisis = "";
    	//Pregunto si tiene mas de una subcarpeta
    	if (listaCarpetas != null && listaCarpetas.length > 1){
    		//tomo el ultimo requerimiento
    		urlAnalisis = listaCarpetas[listaCarpetas.length - 1];
    		//pregunto si esta divido por un punto analizando las exteniones
    		//En el caso que tenga un punto analizo la extenci�n
    		String[] listaPalabras = urlAnalisis.split("\\.");
    		
    		String extencion = "";
    		//Si tengo extencion
    		if (listaPalabras != null && listaPalabras.length > 1){
    			extencion = listaPalabras[listaPalabras.length - 1];
    			if( !extencion.equals("do") && !extencion.equals("acion")){
    				return 1;
    			}
    		}
    		//Si no tengo Extencion
    		else{
    			if (urlAnalisis.equals("validateMS"))
    				return 2;
    			if (urlAnalisis.equals("loginProc"))
    				return 3;
    			if (urlAnalisis.equals("login"))
    				return 2;
    			if (urlAnalisis.equals("validateEmpresa"))
    				return 2;
    			
    		}
    	}
    	return 4;
    
    }

    
    /**
     * Obtiene el URL a enviar
     */
    private  String getAction(String url){
    	String nombre_antecedentes = deploySistem + "/";
    	
    	url = url.replaceAll(nombre_antecedentes, "");
 
    	//obtengo el action sacando los parametros
    	String[] parseParams = url.split("\\?");
    	
    	url = parseParams[0];
    	
    	String[] parseTree= url.split("\\/");
    	
    	url = parseTree[0];
    	
    	return url;
    
    }
    

   	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getTipoRequest("/Antecedentes/msAOtip");
		//String action = this.getAction("/Antecedentes/msAotips?param=2");
		//System.out.println(action);
		//System.out.println(ValidacionCache.getInstance().validateUrl(action, "9")); 
	}
    
    public void destroy()
    {
	     configuracionFiltro = null;
    }

}

