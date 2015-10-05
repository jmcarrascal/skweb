package jmc.skweb.core.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jmc.skweb.core.model.Usuario;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;

@Component 
@Path("/vendedor")
public class VendedorRestService {
		
	
	/**
	 * Este metodo devuelve el usuario de la session actual
	 * @return Usuario actual
	 */
	private Usuario getUsuarioSesion() {
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().getActionInvocation().getInvocationContext().get(ServletActionContext.HTTP_REQUEST);		
		return (Usuario)request.getSession().getAttribute("usuario");
	}

	@GET
	@Path("/soloimpagoallclientes")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario() {	
		return getUsuarioSesion();
		
	}
	
	
	

}
