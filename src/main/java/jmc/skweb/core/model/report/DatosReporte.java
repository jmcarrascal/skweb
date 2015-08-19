package jmc.skweb.core.model.report;

/**
 * @author Juan Manuel
 *
 */
public class DatosReporte {

	private String fechaDesde;
	private String fechaHasta;
	private Integer agendadoDesde;
	private Integer agendadoHasta;
	private Integer tipoReporte;
	private Integer origen;
	private Integer reporte;
	private String formatoReporte;
	private String pedidosPendientes;
	private String artDesde;
	private String artHasta;
	private String todosClientes;
	private String clientes;
	
	
	
	
	public String getArtDesde() {
		return artDesde;
	}
	public void setArtDesde(String artDesde) {
		this.artDesde = artDesde;
	}
	public String getArtHasta() {
		return artHasta;
	}
	public void setArtHasta(String artHasta) {
		this.artHasta = artHasta;
	}
	public String getPedidosPendientes() {
		return pedidosPendientes;
	}
	public void setPedidosPendientes(String pedidosPendientes) {
		this.pedidosPendientes = pedidosPendientes;
	}
	public Integer getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(Integer tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	public Integer getOrigen() {
		return origen;
	}
	public void setOrigen(Integer origen) {
		this.origen = origen;
	}
	public Integer getReporte() {
		return reporte;
	}
	public void setReporte(Integer reporte) {
		this.reporte = reporte;
	}
	public String getFormatoReporte() {
		return formatoReporte;
	}
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Integer getAgendadoDesde() {
		return agendadoDesde;
	}
	public void setAgendadoDesde(Integer agendadoDesde) {
		this.agendadoDesde = agendadoDesde;
	}
	public Integer getAgendadoHasta() {
		return agendadoHasta;
	}
	public void setAgendadoHasta(Integer agendadoHasta) {
		this.agendadoHasta = agendadoHasta;
	}
	public String getTodosClientes() {
		return todosClientes;
	}
	public void setTodosClientes(String todosClientes) {
		this.todosClientes = todosClientes;
	}
	public String getClientes() {
		return clientes;
	}
	public void setClientes(String clientes) {
		this.clientes = clientes;
	}
	
	
	
}
