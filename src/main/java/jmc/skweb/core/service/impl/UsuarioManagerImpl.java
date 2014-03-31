package jmc.skweb.core.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.transaction.annotation.Transactional;


import jmc.skweb.core.dao.GenericDAO;
import jmc.skweb.core.dao.OperadoresDAO;
import jmc.skweb.core.dao.VendedorDAO;
import jmc.skweb.core.model.EmpresaWeb;
import jmc.skweb.core.model.Gente;
import jmc.skweb.core.model.Operadores;
import jmc.skweb.core.model.Parametrizacion;
import jmc.skweb.core.model.Preferencias;
import jmc.skweb.core.model.Rol;
import jmc.skweb.core.model.Usuario;
import jmc.skweb.core.model.UsuarioWeb;
import jmc.skweb.core.model.Vendedor;
import jmc.skweb.core.service.UsuarioManager;
import jmc.skweb.util.Constants;
import jmc.skweb.util.email.Email;
import jmc.skweb.util.email.SendEmailThread;






/**
 * @author Juan Manuel Carrascal
 *
 */
public class UsuarioManagerImpl implements UsuarioManager{

	private GenericDAO<Gente> genteDAO;
	private GenericDAO<UsuarioWeb> usuarioWebDAO;
	private GenericDAO<Preferencias> preferenciasDAO;
	private GenericDAO<EmpresaWeb> empresaWebDAO;
	private GenericDAO<Rol> rolDAO;
	private VendedorDAO extendedVendedorDAO;
	private OperadoresDAO extendedOperadoresDAO;
	private GenericDAO<Parametrizacion> parametrizacionDAO;
	

	public GenericDAO<Parametrizacion> getParametrizacionDAO() {
		return parametrizacionDAO;
	}

	public void setParametrizacionDAO(GenericDAO<Parametrizacion> parametrizacionDAO) {
		this.parametrizacionDAO = parametrizacionDAO;
	}

	public OperadoresDAO getExtendedOperadoresDAO() {
		return extendedOperadoresDAO;
	}

	public void setExtendedOperadoresDAO(OperadoresDAO extendedOperadoresDAO) {
		this.extendedOperadoresDAO = extendedOperadoresDAO;
	}

	public VendedorDAO getExtendedVendedorDAO() {
		return extendedVendedorDAO;
	}

	public void setExtendedVendedorDAO(VendedorDAO extendedVendedorDAO) {
		this.extendedVendedorDAO = extendedVendedorDAO;
	}

	public GenericDAO<Rol> getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(GenericDAO<Rol> rolDAO) {
		this.rolDAO = rolDAO;
	}

	public GenericDAO<EmpresaWeb> getEmpresaWebDAO() {
		return empresaWebDAO;
	}

	public void setEmpresaWebDAO(GenericDAO<EmpresaWeb> empresaWebDAO) {
		this.empresaWebDAO = empresaWebDAO;
	}

	public GenericDAO<UsuarioWeb> getUsuarioWebDAO() {
		return usuarioWebDAO;
	}

	public void setUsuarioWebDAO(GenericDAO<UsuarioWeb> usuarioWebDAO) {
		this.usuarioWebDAO = usuarioWebDAO;
	}

	public GenericDAO<Gente> getGenteDAO() {
		return genteDAO;
	}

	public void setGenteDAO(GenericDAO<Gente> genteDAO) {
		this.genteDAO = genteDAO;
	}

	public void sendMail(Email email) {
		Properties props = getPropertiesEmail();		
		SendEmailThread emailManager = new SendEmailThread(props,email);		
		emailManager.start();
	}

	
	public Usuario getUsuarioByUsernamePassword(String username, String password){
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("usuario", username.trim());
		res.put("password", password.trim());
		List<UsuarioWeb> usuarioWebList = usuarioWebDAO.findByObjectCriteria(res);
		//List<Usuario> usuarios = usuarioDAO.getAll();
		if (usuarioWebList.size()>0){
			//Obtengo el Rol y la empresa
			//Creo el objeto Usuario de Sesion				
			Usuario usuario = usuarioWebToUsuario(usuarioWebList.get(0));
			return usuario;				
		}else{
			return null;				
		}
	}
	
	private Usuario usuarioWebToUsuario(UsuarioWeb usuarioWeb){
		//Hacer Casteo de Usuario. 
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioWeb.getIdUsuarioWeb());
		usuario.setNombre(usuarioWeb.getNombre());
		usuario.setApellido(usuarioWeb.getApellido());				
		usuario.setRol(usuarioWeb.getRol().getIdRol());
		try{
			usuario.setIdEmpresa(usuarioWeb.getEmpresaWeb().getIdEmpresaWeb());
		}catch(Exception e){
			e.printStackTrace();
		}
		usuario.setEmpresaNrSk(usuarioWeb.getEmpresaWeb().getEmpresaNrSk());
		usuario.setListPreferencias(getListPreferencias());
		usuario.setVendedorNr(usuarioWeb.getVendedorNr());
		usuario.setGenteNr(usuarioWeb.getGenteNr());
		usuario.setOperadorNr(usuarioWeb.getOperadorNr());
		usuario.setPermisoTransac(usuarioWeb.getPermisoTransac()); 
		return usuario;
	}

	public GenericDAO<Preferencias> getPreferenciasDAO() {
		return preferenciasDAO;
	}

	public void setPreferenciasDAO(GenericDAO<Preferencias> preferenciasDAO) {
		this.preferenciasDAO = preferenciasDAO;
	}

	
	public List<Preferencias> getListPreferencias() {		
		return preferenciasDAO.getAll();
	}

	
	public List<UsuarioWeb> getUsuariosPorEmpresa(int idEmpresa) {
		HashMap<String, Object> res = new HashMap<String, Object>();
		EmpresaWeb empresaWeb = empresaWebDAO.getByPrimaryKey(idEmpresa);
		res.put("empresaWeb", empresaWeb);
		List<UsuarioWeb> usuarioWebList = usuarioWebDAO.findByObjectCriteria(res);
		return usuarioWebList;
	}

	public List<UsuarioWeb> getUsuarios() {
		List<UsuarioWeb> usuarioWebList = usuarioWebDAO.getAll();
		return usuarioWebList;
	}

	public List<Rol> getAllRol() {
		List<Rol> rolList = rolDAO.getAll();
		List<Rol> rolListOrig = new ArrayList<Rol>();
		Rol rol = new Rol();
		rol.setDescrip("Seleccione un Rol");		
		rolListOrig.add(rol);
		rolListOrig.addAll(rolList);
		return rolListOrig;
	}

	
	public List<Vendedor> getAllVendedor() {	
		return extendedVendedorDAO.getVendedorEnable();
	}
	
	public boolean existeUsuario(String usuario)throws Exception {
		HashMap<String, Object> res = new HashMap<String, Object>();		
		res.put("usuario", usuario);
		List<UsuarioWeb> usuarioWebList = usuarioWebDAO.findByObjectCriteria(res);
		if (usuarioWebList.size() > 0)
			return true;
		else
			return false;
	}

	
	public void saveUsuarioWeb(UsuarioWeb usuarioWeb, int idEmpresa) throws Exception{
		EmpresaWeb empresaWeb = empresaWebDAO.getByPrimaryKey(idEmpresa);
		
		usuarioWeb.setEmpresaWeb(empresaWeb);
		usuarioWebDAO.save(usuarioWeb);		
	}

	
	public void setLastLogin(Usuario user) {
		UsuarioWeb userWeb = usuarioWebDAO.getByPrimaryKey(user.getIdUsuario());
		userWeb.setLastLogin(new Timestamp(System.currentTimeMillis()));
		usuarioWebDAO.update(userWeb);		
	}

	
	public UsuarioWeb getUsuarioByPK(Long idUsuarioWeb) {
		UsuarioWeb usuarioWeb = usuarioWebDAO.getByPrimaryKey(idUsuarioWeb);
		Operadores operadores = null;
		Gente gente = null;
		switch (usuarioWeb.getRol().getIdRol()) {		
		case Constants.ID_USR_OPERADOR:
			 operadores = extendedOperadoresDAO.getByPrimaryKey(usuarioWeb.getOperadorNr());
			if (operadores != null){
				usuarioWeb.setOperadorDescrip(operadores.getDescripC());
			}
			break;
		case Constants.ID_USR_GERENTE:
			operadores = extendedOperadoresDAO.getByPrimaryKey(usuarioWeb.getOperadorNr());
			if (operadores != null){
				usuarioWeb.setOperadorDescrip(operadores.getDescripC());
			}
			break;
		case Constants.ID_USR_CLIENTE:
			gente = genteDAO.getByPrimaryKey(usuarioWeb.getGenteNr());
			if (gente != null){
				usuarioWeb.setOperadorDescrip(gente.getDescripC());
			}
			break;
		case Constants.ID_USR_PROVEEDOR:
			gente = genteDAO.getByPrimaryKey(usuarioWeb.getGenteNr());
			if (gente != null){
				usuarioWeb.setOperadorDescrip(gente.getDescripC());
			}
			break;
		default:
			break;
		}
		
		return usuarioWeb; 
	}

	
	public void updateUsuario(UsuarioWeb usuarioWeb,int idEmpresa) throws Exception{
		EmpresaWeb empresaWeb = empresaWebDAO.getByPrimaryKey(idEmpresa);
		
		usuarioWeb.setEmpresaWeb(empresaWeb);
		usuarioWebDAO.update(usuarioWeb);
		
	}


	public void removeUsuario(Long idUsuarioWeb) {
		UsuarioWeb usuarioWeb = usuarioWebDAO.getByPrimaryKey(idUsuarioWeb);
		usuarioWebDAO.remove(usuarioWeb);		
	}

	public List<EmpresaWeb> getAllEmpresa() {		
		return empresaWebDAO.getAll();
	}

	@Override
	public List<Operadores> getOperadorPorNombre(String filter) {
		List<Operadores> operadoresList = new ArrayList<Operadores>();
		try {
			operadoresList = extendedOperadoresDAO.getOperadorPorNombre(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operadoresList;
	}

	public Properties getPropertiesEmail(){
		Properties props = new Properties();
		long i = 50l;
		while(i < 66){
			Parametrizacion parametrizacion = parametrizacionDAO.getByPrimaryKey(i);			
			if (parametrizacion!=null){
				props.put(parametrizacion.getDescrip().trim(), parametrizacion.getValor().trim());				
			}			
			i++;
		}			
		return props;
	}

	
	public UsuarioWeb getUsuarioByVendedor(Integer genteNr) {
		Rol rol = rolDAO.getByPrimaryKey(Constants.ID_USR_VENDEDOR);
		Gente gente = genteDAO.getByPrimaryKey(genteNr);		
		HashMap<String, Object> res = new HashMap<String, Object>();
		res.put("vendedorNr", gente.getVendedorNr());
		res.put("rol", rol);
		List<UsuarioWeb> usuarioWebList = usuarioWebDAO.findByObjectCriteria(res);
		if (usuarioWebList.size()>0){
			return  usuarioWebList.get(0);			
		}else{
			return null;				
		}
	}	

}
