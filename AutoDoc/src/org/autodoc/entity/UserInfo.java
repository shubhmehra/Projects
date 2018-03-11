package org.autodoc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	private String userId;				//100 - primary_key
	@Column(name="password")
    private String password;			//45 - Not Null
	@Column(name="role_id")
    private int roleId;					//smallInt - 1 - Not Null
	@Column(name="created_date", updatable = false)
    private String createdDate;			//TIMESTAMP - Not Null
	@Column(name="created_by", updatable = false)
	private String createdBy;			//100 - Not Null
	@Column(name="updated_date")
    private String updatedDate;			//TIMESTAMP
	@Column(name="updated_by")
	private String updatedBy;			//100
	@Column(name="enabled")
	private String enabled;				//10 - Not Null
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}