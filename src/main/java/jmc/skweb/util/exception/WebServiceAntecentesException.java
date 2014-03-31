/*

 * Copyright 2008 Dirección Provincial de Informática de la Provincia de Buenos Aires.  All Rights Reserved

 * Dirección Provincial de Informática de la Provincia de Buenos Aires Proprietary and Confidential.

 *

 * You agree to use Your best efforts to protect the software and documentation

 * from unauthorized copy or use. The software source code represents and embodies

 * trade secrets of Dirección Provincial de Informática de la Provincia de Buenos Aires and/or its licensors.

 * The source code and embodied trade secrets are not licensed to you and any modification,

 * addition or deletion is strictly prohibited. You agree not to disassemble, decompile,

 * or otherwise reverse engineer the software in order to discover the source code and/or

 * the trade secrets contained in the source code.

 *

 *

 * Derecho de autor 2008 Dirección Provincial de Informática de la Provincia de Buenos Aires.  Todos los derechos reservados.

 * Propiedad de Dirección Provincial de Informática de la Provincia de Buenos Aires y Confidencial.

 *

 * Por la presente acuerdo hacer mi mayor esfuerzo para proteger el software y la documentación

 * de la copia o el uso no autorizados. El código fuente del software representa e incluye

 * secretos comerciales de Dirección Provincial de Informática de la Provincia de Buenos Aires y/o sus licenciantes. 

 * No se le otorga licencia del código fuente ni los secretos comerciales incluidos;

 * y cualquier modificación, agregado o eliminación se encuentra estrictamente prohibida.

 * Asimismo, por la presente me comprometo a no desarmar, descompilar, o de alguna forma utilizar

 * técnicas de ingeniería inversa sobre el software para descubrir su fuente y/o los

 * secretos comerciales contenidos en el código fuente.

 *

 */

package jmc.skweb.util.exception;

/**
 * @author clarisa
 *
 */
public class WebServiceAntecentesException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WebServiceAntecentesException() {
		super();		
	}

	public WebServiceAntecentesException(String message, Throwable cause) {
		super(message, cause);		
	}

	public WebServiceAntecentesException(String message) {
		super(message);		
	}

	public WebServiceAntecentesException(Throwable cause) {
		super(cause);		
	}
	

}
