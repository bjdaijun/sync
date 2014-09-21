package org.bgrimm.sync.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Asset  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5321903738728367316L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private double x;
	
	private double y;
	
	private double z;
	
	private Long tagid;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Long getTagid() {
		return tagid;
	}

	public void setTagid(Long tagid) {
		this.tagid = tagid;
	}


	
	
	
	
	
	
}
