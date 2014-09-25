package org.bgrimm.sync.domain.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274443374584802879L;
	private long id;
	private String name;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	private List<Orders> orders;

	public void setId(long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "user")
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
