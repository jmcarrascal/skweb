package jmc.skweb.core.model.report;


import java.util.ArrayList;
import java.util.List;

import jmc.skweb.core.model.Transac;

public class SaldoAcumulado {

	private List<Transac> listTransac = new ArrayList<Transac>();
	private Double incioAcumulado = null;
	private Double finAcumulado = null;
	
	
	public List<Transac> getListTransac() {
		return listTransac;
	}
	public void setListTransac(List<Transac> listTransac) {
		this.listTransac = listTransac;
	}
	public Double getIncioAcumulado() {
		return incioAcumulado;
	}
	public void setIncioAcumulado(Double incioAcumulado) {
		this.incioAcumulado = incioAcumulado;
	}
	public Double getFinAcumulado() {
		return finAcumulado;
	}
	public void setFinAcumulado(Double finAcumulado) {
		this.finAcumulado = finAcumulado;
	}
	
	
	
	
}
