package org.autodoc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="role_id")
	private char roleId;					//2 - primary_key
	@Column(name="role_name")
    private String roleName;				//45 - Not Null
	@Column(name="description")
	private String description;				//150
	@Column(name="created_date", updatable = false)
    private String createdDate;				//TIMESTAMP - Not Null
	@Column(name="created_by", updatable = false)
	private String createdBy;				//100 - Not Null
	@Column(name="updated_date")
    private String updatedDate;				//TIMESTAMP
	@Column(name="updated_by")
	private String updatedBy;				//100
	
	public char getRoleId() {
		return roleId;
	}
	public void setRoleId(char roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}