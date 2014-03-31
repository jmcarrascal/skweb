package jmc.skweb.util.exception;

import org.springframework.dao.DataAccessException;


public class DataAccessDPIv1Exception extends DataAccessException{


	public final static String SAVE_ERROR="SAVE_ERROR";
	public final static String UPDATE_ERROR="UPDATE_ERROR";
	public final static String DELETE_ERROR="DELETE_ERROR";
	public final static String GET_ERROR="GET_ERROR";
	
	private static final long serialVersionUID = 1L;

	public DataAccessDPIv1Exception(String msg) {
		super(msg);		
	}

	public DataAccessDPIv1Exception(String msg, Throwable cause) {
		super(msg, cause);		
	}

	
	
}
