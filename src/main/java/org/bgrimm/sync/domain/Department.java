package org.bgrimm.sync.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String name;

	private Date createDate;
	private Date modifiedDate;
	// parent department
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Department parent;
	// delete index
	private int delInd;
	
	public Long getId() {
		return id;
	}
	
	public List<Department> getDepartments() {
		return departments;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	private List<Department> departments;
	
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public int getDelInd() {
		return delInd;
	}

	public void setDelInd(int delInd) {
		this.delInd = delInd;
	}

	

}
