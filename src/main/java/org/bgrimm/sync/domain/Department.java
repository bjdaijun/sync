package org.bgrimm.sync.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

//	private Set<Department> departments;
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy="parent_id")
//	public Set<Department> getDepartments() {
//		return departments;
//	}
//
//
//	public void setDepartments(Set<Department> departments) {
//		this.departments = departments;
//	}


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

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "parent_id")
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

	private Date createDate;
	private Date modifiedDate;
	// parent department
	private Department parent;
	// delete index
	private int delInd;

}
