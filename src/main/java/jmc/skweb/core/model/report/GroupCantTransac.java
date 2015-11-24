package jmc.skweb.core.model.report;

public class GroupCantTransac {

	private Double cant1;
	private Integer colo;
	
	public GroupCantTransac(){
	}
	
	public GroupCantTransac( Double cant1, Integer colo){
		this.cant1 = cant1;
		this.colo = colo;
	}
	
	public Double getCant1() {
		return cant1;
	}
	public void setCant1(Double cant1) {
		this.cant1 = cant1;
	}
	public Integer getColo() {
		return colo;
	}
	public void setColo(Integer colo) {
		this.colo = colo;
	}
	
}
