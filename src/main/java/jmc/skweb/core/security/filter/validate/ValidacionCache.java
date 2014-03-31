package jmc.skweb.core.security.filter.validate;



import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ValidacionCache {

	private static ValidacionCache validacionCache;
	private HashMap<String,String> validaciones = null;	
	

	private ValidacionCache(){
		//validaciones = new HashMap<String,String>();
	}

	public static ValidacionCache getInstance()
	{
		if (validacionCache == null)
			validacionCache = new ValidacionCache();
		return validacionCache;	
	}



	synchronized public void addCacheValidacion(String url, String rolEnable){
		validaciones.put(url,rolEnable);
	}

	public Boolean validateUrl(String url, String rolSended){
		//System.out.println("URL a validar: '" + url +"' rol a validar '" + rolSended );
		if (validaciones == null){
			validaciones = new HashMap<String,String>();
			loadValidaciones();
		}

		//Si existe esta relacion
		if (validaciones.containsKey(url)){	
			
			//obtengo la lista de roles habilitados
			String rolEnable = validaciones.get(url);
		
			//parseo los roles
			String[] roles = rolEnable.split(",");
			for(String roleFind : roles){
				if (roleFind.trim().equals(rolSended.trim()))
					return true;
			}
			return false;
		}else{	
			// si no existe 
			return false;			
		}	
	}

	
	public void loadValidaciones(){
		  
		try {		  
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse( getClass().getResourceAsStream("ActionValidate.xml"));
			  doc.getDocumentElement().normalize();
			  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			  NodeList nodeLst = doc.getElementsByTagName("entry");
			  
			  String url = "";
			  String roles = "";
			  for (int s = 0; s < nodeLst.getLength(); s++) {

			    Node fstNode = nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
			  
			      Element fstElmnt = (Element) fstNode;
			      
			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("url");
			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			      NodeList fstNm = fstNmElmnt.getChildNodes();
			      //System.out.println("URL : "  + ((Node) fstNm.item(0)).getNodeValue());
			      url = ((Node) fstNm.item(0)).getNodeValue();
			      
			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("rolEnable");
			      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
			      NodeList lstNm = lstNmElmnt.getChildNodes();
			      //System.out.println("rolEnable : " + ((Node) lstNm.item(0)).getNodeValue());
			      roles = ((Node) lstNm.item(0)).getNodeValue();
			      addCacheValidacion(url,roles);
			    }

			  }
			  } catch (Exception e) {
			    e.printStackTrace();
			  }	 
	 
		}
}
