   package jmc.skweb.core.model;

// Generated 23/09/2011 12:11:03 by Hibernate Tools 3.2.0.b9

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * ExiArtId generated by hbm2java
 */
@Embeddable
public class ItemsId implements java.io.Serializable {

	private Transac transac;
	private Integer itemNr;

	public ItemsId() {
	}

	public ItemsId(Transac transac, Integer itemNr) {
		this.itemNr = itemNr;
		this.transac = transac;
	}

		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transacNr", nullable = false)	
	public Transac getTransac() {
		return transac;
	}

	public void setTransac(Transac transac) {
		this.transac = transac;
	}

	@Column(name = "ItemNr", nullable = false)
	public Integer getItemNr() {
		return itemNr;
	}

	public void setItemNr(Integer itemNr) {
		this.itemNr = itemNr;
	}


	
	
	

}
