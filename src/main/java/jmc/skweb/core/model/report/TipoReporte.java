package jmc.skweb.core.model.report;

public class TipoReporte {
	private Integer nr;
	private String descripcion;
	
	public TipoReporte(Integer nr, String descripcion){
		this.nr = nr;
		this.descripcion = descripcion;
	}
	
	public Integer getNr() {
		return nr;
	}
	public void setNr(Integer nr) {
		this.nr = nr;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
