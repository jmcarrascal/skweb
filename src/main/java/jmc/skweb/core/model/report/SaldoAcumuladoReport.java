package jmc.skweb.core.model.report;


import java.util.ArrayList;
import java.util.List;

import jmc.skweb.core.model.Transac;

public class SaldoAcumuladoReport {

	private List<TransacReport> listTransac = new ArrayList<TransacReport>();
	private Double incioAcumulado = null;
	private Double finAcumulado = null;
	private String gente;
	
	
	
	public String getGente() {
		return gente;
	}
	public void setGente(String gente) {
		this.gente = gente;
	}
	public List<TransacReport> getListTransac() {
		return listTransac;
	}
	public void setListTransac(List<TransacReport> listTransac) {
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
