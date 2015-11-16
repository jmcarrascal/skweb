package jmc.skweb.ui.struts.actions;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.service.ParametrizacionManager;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.CallUrl;
import jmc.skweb.util.Constants;
import jmc.skweb.util.PreferenciasUtil;
import jmc.skweb.core.model.Preferencias;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;





public class LoginAction extends ActionSupport  {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String password;
	private ParametrizacionManager parametrizacionManager;
	private UsuarioManager usuarioManager;
	
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}


	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}


	public ParametrizacionManager getParametrizacionManager() {
		return parametrizacionManager;
	}


	public void setParametrizacionManager(
			ParametrizacionManager parametrizacionManager) {
		this.parametrizacionManager = parametrizacionManager;
	}


	public String execute() throws Exception {
    
    return "success";
	}
  
	/**
	 * Redirige la APP a la pagina de Login 
	 * @return
	 * @throws Exception
	 */
	public String login(){	        
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String userAgent = (String)request.getHeader("user-agent");
		String valoresStr  = parametrizacionManager.getParametrizacionByPrimaryKey(Constants.ID_PARAM_BROWSER_MOBILE).getValor();
		String[] valores = valoresStr.split("\\|");
		boolean usoMobile = false; 
		for(String valor : valores){
			if (userAgent.indexOf(valor) != -1){
				usoMobile = true;
			}
		}				
		if (usoMobile){
			return "mobile";	
		}else{
			return "success";
		}
		
	}
				
	/**
	 * Cierra la session de la APP y redirige al Login
	 * @return
	 * @throws Exception
	 */
	public String out() throws Exception {	        		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		
		request.getSession().setAttribute("usuario", null);
		request.setAttribute("usuario", null);
		request.getSession().invalidate();
		return "success";
	}

	
	public String close() throws Exception {	        		
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);				
		request.getSession().setAttribute("usuario", null);
		request.setAttribute("usuario", null);
		request.getSession().invalidate();
		return null;
	}

	private static void openURL(String url){
		URL method = null; 
		try {
			method = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			method.openStream();
		} catch (IOException e) {
			
			e.printStackTrace();
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

	
	/*
	 * Metodo que toma los valores usuario y password
	 * y valida contra el WS a validez del mismo contra el Login unico
	 * Se invoca solo para usuarios del Ministerio de Justicia
	 */
	public String validateMS() {	    
		Usuario usuarioPrevio = getUsuarioSesion();
		if (usuarioPrevio != null){			
			return "ok";
		}
		Usuario user = null;
		Usuario userA = null;
		try {
			user = usuarioManager.getUsuarioByUsernamePassword(usuario, password);
			userA = user;
		} catch (Exception e1) {			
			e1.printStackTrace();
			return "exit";
		}
		if (user == null){
			return "exit";
		}else{
			HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
			List<Preferencias> listPreferencias = usuarioManager.getListPreferencias();
			userA.setListPreferencias(listPreferencias);
			userA.setTipoCambio(PreferenciasUtil.comparePreferencia(userA.getListPreferencias(), Constants.PREF_ID_VALOR_DOLAR));
			request.getSession().setAttribute("usuario", userA);						
			return "ok";
		}				
	}
	

	/*
	 * Metodo que toma los valores usuario y password
	 * y valida contra el WS a validez del mismo contra el Login unico
	 * Se invoca solo para usuarios del Ministerio de Justicia
	 */
	public String validateEmpresa(){	    
		Usuario user = null;
		try {
			user = usuarioManager.getUsuarioByUsernamePassword(usuario, password);			
		} catch (Exception e1) {			
			e1.printStackTrace();
			return "exit";
		}
		if (user == null){
			return "exit";
		}else{
			HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

			HttpServletResponse response  =(HttpServletResponse)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
			
			List<Preferencias> listPreferencias = usuarioManager.getListPreferencias();

			user.setListPreferencias(listPreferencias);
			
			user.setTipoCambio(PreferenciasUtil.comparePreferencia(user.getListPreferencias(), Constants.PREF_ID_VALOR_DOLAR));
			

			request.getSession().setAttribute("usuario", user);
						
			usuarioManager.setLastLogin(user);
			
			request.setAttribute("usuarioWeb", usuario);
			
			request.setAttribute("passwordWeb", password);
			
			return "empresa" + user.getIdEmpresa();
		}				
	}
	
	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}