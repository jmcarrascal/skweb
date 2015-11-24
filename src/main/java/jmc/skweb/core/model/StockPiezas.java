package jmc.skweb.core.model;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Gente generated by hbm2java
 */
/**
 * @author vaio
 *
 */
@Entity
@Table(name = "StockPiezas", schema = " ComunSql.dbo ")
public class StockPiezas implements java.io.Serializable {

	private StockPiezasId id;
	private Double cant1;
	private String imagenSemaforo;
	private Double comprasmenosventas;
	
	
	public StockPiezas(){
		
	}
	public StockPiezas(StockPiezasId id, Double cant1) {
		this.id = id;
		this.cant1 = cant1;
	}
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "CodArt", column = @Column(name = "CodArt", nullable = false, length = 13)),
			@AttributeOverride(name = "color", column = @Column(name = "color", nullable = false)) })
	public StockPiezasId getId() {
		return id;
	}
	public void setId(StockPiezasId id) {
		this.id = id;
	}
	public Double getCant1() {
		return cant1;
	}
	public void setCant1(Double cant1) {
		this.cant1 = cant1;
	}
	
	@Transient
	public String getImagenSemaforo() {
		return imagenSemaforo;
	}
	public void setImagenSemaforo(String imagenSemaforo) {
		this.imagenSemaforo = imagenSemaforo;
	}
	
	
	@Transient
	public Double getComprasmenosventas() {
		return comprasmenosventas;
	}
	public void setComprasmenosventas(Double comprasmenosventas) {
		this.comprasmenosventas = comprasmenosventas;
	}

	
		
	
}

