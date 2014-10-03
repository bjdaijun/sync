package org.bgrimm.sync.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="class")
	private String tagclass;
	private String networkID;
	private String batterystatus;
	
	public String getTagclass() {
		return tagclass;
	}
	public void setTagclass(String tagclass) {
		this.tagclass = tagclass;
	}
	public String getNetworkID() {
		return networkID;
	}
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}

	@ManyToOne
	private Asset asset;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public String getBatterystatus() {
		return batterystatus;
	}
	public void setBatterystatus(String batterystatus) {
		this.batterystatus = batterystatus;
	}
	
}
