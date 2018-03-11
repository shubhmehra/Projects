package org.autodoc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "letters")
@SequenceGenerator(name = "sequence", sequenceName = "letter_information_letter_id_seq", allocationSize=0)
public class LettersInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name="letter_id")
	private int letterId;						//primary_key
	@Column(name="letter_name")
	private String letterName;					//500 - Not Null
	@Column(name="created_date", updatable = false)
	private Date createdDate;					//TIMESTAMP - Not Null
	@Column(name="created_by", updatable = false)
	private String createdBy;					//100 - Not Null
	@Column(name="updated_date")
	private Date updatedDate;					//TIMESTAMP
	@Column(name="updated_by")
	private String updatedBy;					//100
	@Column(name="status")
	private String status;						//20
	
	public int getLetterId() {
		return letterId;
	}
	public void setLetterId(int letterId) {
		this.letterId = letterId;
	}
	public String getLetterName() {
		return letterName;
	}
	public void setLetterName(String letterName) {
		this.letterName = letterName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}