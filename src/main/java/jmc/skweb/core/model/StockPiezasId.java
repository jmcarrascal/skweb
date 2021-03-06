   package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * ExiArtId generated by hbm2java
 */
@Embeddable
public class StockPiezasId implements java.io.Serializable {

	private Colores colores;
	private Stock stock;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color")
	public Colores getColores() {
		return colores;
	}

	public void setColores(Colores colores) {
		this.colores = colores;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codArt")
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public StockPiezasId() {
	}

	public StockPiezasId(Colores colores, Stock stock) {
		this.colores = colores;
		this.stock = stock;
	}

		
	
	

}
