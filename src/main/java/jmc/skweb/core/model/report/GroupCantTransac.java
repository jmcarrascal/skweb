package jmc.skweb.core.model.report;

import java.math.BigDecimal;

public class GroupCantTransac {

	private BigDecimal cant1;
	private Integer colo;
	
	public GroupCantTransac(){
	}
	
	public GroupCantTransac( BigDecimal cant1, Integer colo){
		this.cant1 = cant1;
		this.colo = colo;
	}
	
	public BigDecimal getCant1() {
		return cant1;
	}
	public void setCant1(BigDecimal cant1) {
		this.cant1 = cant1;
	}
	public Integer getColo() {
		return colo;
	}
	public void setColo(Integer colo) {
		this.colo = colo;
	}
	
}
