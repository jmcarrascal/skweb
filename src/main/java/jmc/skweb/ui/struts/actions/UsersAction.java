package jmc.skweb.ui.struts.actions;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmc.skweb.core.model.ArtMadre;
import jmc.skweb.core.model.EmpresaWeb;
import jmc.skweb.core.model.ExiArt;
import jmc.skweb.core.model.Fam;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Operadores;
import jmc.skweb.core.model.Rol;
import jmc.skweb.core.model.SubFam;
import jmc.skweb.core.model.Transac;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.Stock;
import jmc.skweb.core.model.UsuarioWeb;
import jmc.skweb.core.model.Vendedor;
import jmc.skweb.core.model.report.SaldoAcumulado;
import jmc.skweb.core.service.ArticuloManager;
import jmc.skweb.core.service.CuentaCorrienteManager;
import jmc.skweb.core.service.ParametrizacionManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.Constants;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;





/**
 * @author vaio
 *
 */
public class UsersAction extends ActionSupport  {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private ParametrizacionManager parametrizacionManager;
	private UsuarioManager usuarioManager;
	private String adminUsuario;
	private UsuarioWeb usuarioWeb = new UsuarioWeb();
	private EmpresaWeb empresaWeb = new EmpresaWeb();
	
	
	
	
	
	public EmpresaWeb getEmpresaWeb() {
		return empresaWeb;
	}


	public void setEmpresaWeb(EmpresaWeb empresaWeb) {
		this.empresaWeb = empresaWeb;
	}


	public UsuarioWeb getUsuarioWeb() {
		return usuarioWeb;
	}


	public void setUsuarioWeb(UsuarioWeb usuarioWeb) {
		this.usuarioWeb = usuarioWeb;
	}


	public String getAdminUsuario() {
		return adminUsuario;
	}


	public void setAdminUsuario(String adminUsuario) {
		this.adminUsuario = adminUsuario;
	}


	public ParametrizacionManager getParametrizacionManager() {
		return parametrizacionManager;
	}


	public void setParametrizacionManager(
			ParametrizacionManager parametrizacionManager) {
		this.parametrizacionManager = parametrizacionManager;
	}


	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}


	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


	/**
	 * Muestra Todos los usuarios de la empresa en que esta logeado el usuario  
	 * 
	 */
	public String getUsuarios() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
						
		Usuario usuario = getUsuarioSesion();
		
		List<UsuarioWeb> usuarioList = usuarioManager.getUsuarios(); 
		
		request.setAttribute("usuarioList", usuarioList);
				
		return "success";
	}
	
	/**
	 * Prepara la edicion de un usuario  
	 * 
	 */
	public String preparedEditUsuario() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
						
		usuarioWeb = usuarioManager.getUsuarioByPK(usuarioWeb.getIdUsuarioWeb());
						
		Usuario usuario = getUsuarioSesion();
		
		List<Rol> rolList = usuarioManager.getAllRol();
		
		List<Vendedor> vendedorList = usuarioManager.getAllVendedor(); 
		
		List<EmpresaWeb> empresaWebList = usuarioManager.getAllEmpresa();
		
		List<UsuarioWeb> usuarioWebList = usuarioManager.getUsuarios();

		
		// Lista de Empresas
		ActionContext.getContext().getSession().put("empresaWebList", empresaWebList);
		
		// Lista de Roles
		ActionContext.getContext().getSession().put("rolList", rolList);
		
		// Lista de Vendedores
		ActionContext.getContext().getSession().put("vendedorList", vendedorList);
		
		// Lista de Vendedores
		ActionContext.getContext().getSession().put("usuarioWebList", usuarioWebList);

		//Si es Operador cargo el Nombre de Operador de SK
		
		
		adminUsuario = "edit";			
		
		return "success";
	}
	
	
	/**
	 * Prepara el alta de un usuario  
	 * 
	 */
	public String preparedNewUsuario() throws Exception {	        						
		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
						
		Usuario usuario = getUsuarioSesion();
		
		List<Rol> rolList = usuarioManager.getAllRol(); 
		
		List<Vendedor> vendedorList = usuarioManager.getAllVendedor(); 
		
		List<EmpresaWeb> empresaWebList = usuarioManager.getAllEmpresa(); 
		
		List<UsuarioWeb> usuarioWebList = usuarioManager.getUsuarios();
		
		// Lista de Empresas
		ActionContext.getContext().getSession().put("empresaWebList", empresaWebList);

		// Lista de Roles
		ActionContext.getContext().getSession().put("rolList", rolList);
		
		// 	 Lista de VEndedor
		ActionContext.getContext().getSession().put("vendedorList", vendedorList);
		
		// 	 Lista de VEndedor
		ActionContext.getContext().getSession().put("usuarioWebList", usuarioWebList);		
		
		adminUsuario = "new";
		
		return "success";
	}
	
	/**
	 * Prepara la busqueda de un Operador por Nombre.
	 * 
	 */
	public String getOperadorPorNombre() throws Exception {	        								
		
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

		String filter = (String) request.getParameter("q");
		
		List<Operadores> operadoresList = usuarioManager.getOperadorPorNombre(filter);						
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder salida = new StringBuilder();        				
		salida.append(getAutoCompleteDataJqueryOperadores(operadoresList));
      salida.insert(0, "[");
      salida.append("]");

		//		salida.append("[	{ name: \"Peter Pan\", to: \"peter@pan.de\" },	{ name: \"Molly\", to: \"molly@yahoo.com\" },	{ name: \"Forneria Marconi\", to: \"live@japan.jp\" },	{ name: \"Master <em>Sync</em>\", to: \"205bw@samsung.com\" },	{ name: \"Dr. <strong>Tech</strong> de Log\", to: \"g15@logitech.com\" },	{ name: \"Don Corleone\", to: \"don@vegas.com\" },	{ name: \"Mc Chick\", to: \"info@donalds.org\" },	{ name: \"Donnie Darko\", to: \"dd@timeshift.info\" },	{ name: \"Quake The Net\", to: \"webmaster@quakenet.org\" },	{ name: \"Dr. Write\", to: \"write@writable.com\" }]");
		response.setContentType("text/html; charset=iso-8859-1");
        //Imprime el resultado
        try {
			sos.print(salida.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
			
	}	

	/**
	 * Metodo que devuelve un array de String con las suggestions en el vector 0
	 * y  data en el vector 1
	 */
	private String getAutoCompleteDataJqueryOperadores(List<Operadores> operadoresList){			
		String data = "";
		int i = 0;
		int total = operadoresList.size();
		for(Operadores operadores: operadoresList){
			i++;
			String descrip = operadores.getOperNombre().replaceAll("\"", "'").replaceAll(",", " ");			
			String tmp = "{ \"clave\": " + "\""+  operadores.getOperNr() + "\", \"valor\": " + "\"" + descrip + "\" }" ;
			if (total == i){				
			}else{
				tmp = tmp + ", ";
			}
			data = data + tmp;
		}		
		data = data + "";
		return data;
	}
	
	/**
	 * Guarda el Usuario save or update depende cada caso
	 * @return String
	 */
	public String saveUsuario(){		
		
		//Obtengo el usuario de Sesion
		Usuario usuario = getUsuarioSesion();
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);		
		ServletOutputStream sos = null;
		
		try {
			sos = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String leyendaSaveUsuario = "";
		if (adminUsuario.equals("new")) {							
			
			try {
	        	//Verifico que el nombre de usuario no este dado de alta
				if (usuarioManager.existeUsuario(usuarioWeb.getUsuario())){
					leyendaSaveUsuario = "El usuario " + usuarioWeb.getUsuario() + " ya existe en la base de datos.";
					sos.print(leyendaSaveUsuario);
					return null;
				}
				usuarioManager.saveUsuarioWeb(usuarioWeb, usuarioWeb.getEmpresaWeb().getIdEmpresaWeb());
				leyendaSaveUsuario = "OK";
				sos.print(leyendaSaveUsuario);
				return null;
			        	        
			} catch (Exception e) {
				e.printStackTrace();
				leyendaSaveUsuario = "No se pudo grabar el usuario por favor comuniquese con el administrador";
				try {
					sos.print(leyendaSaveUsuario);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return null;
	
			
			}
		} else {
			//Edito el UsuarioWeb

			try {
				usuarioManager.updateUsuario(usuarioWeb, usuarioWeb.getEmpresaWeb().getIdEmpresaWeb());
				leyendaSaveUsuario = "OK";
				sos.print(leyendaSaveUsuario);
				return null;
			        	        
			} catch (Exception e) {
				e.printStackTrace();
				leyendaSaveUsuario = "No se pudo editar el usuario por favor comuniquese con el administrador";
				try {
					sos.print(leyendaSaveUsuario);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return null;
			}
		}
	
	}

	/**
	 * Este metodo devuelve el usuario de la session actual
	 * @return Usuario actual
	 */
	private Usuario getUsuarioSesion() {
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		return (Usuario)request.getSession().getAttribute("usuario");
	}
	

	/**
	 * Elimina un usuairo .
	 * 
	 */
	public String removeUsuario() throws Exception {	        						
				
		usuarioManager.removeUsuario(usuarioWeb.getIdUsuarioWeb());
		
		return "success";
	}	

}