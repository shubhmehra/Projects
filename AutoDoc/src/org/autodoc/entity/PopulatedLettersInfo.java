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
@Table(name = "populated_letters")
@SequenceGenerator(name = "sequence", sequenceName = "populated_letters_id_seq", allocationSize=0)
public class PopulatedLettersInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name="pop_letter_id")
	private int popLetterId;					// primary_key
	@Column(name="token_id")
	private int tokenNo;						// Not Null
	@Column(name="file_path")
	private String filePath;					// 100
	@Column(name="file_name")
	private String fileName;					// 200
	
	@Column(name="created_date", updatable = false)
	private Date createdDate;					//TIMESTAMP - Not Null
	@Column(name="created_by", updatable = false)
	private String createdBy;					//100 - Not Null
	@Column(name="updated_date")
	private Date updatedDate;					//TIMESTAMP
	@Column(name="updated_by")
	private String updatedBy;					//100
	
	public PopulatedLettersInfo()
	{
		
	}
	
	public PopulatedLettersInfo(int popLetterId, int tokenNo, String filePath,
			String fileName, Date createdDate, String createdBy,
			Date updatedDate, String updatedBy)
	{
		this.popLetterId = popLetterId;
		this.tokenNo = tokenNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}
	
	public int getPopLetterId()
	{
		return popLetterId;
	}
	public void setPopLetterId(int popLetterId)
	{
		this.popLetterId = popLetterId;
	}
	public int getTokenNo()
	{
		return tokenNo;
	}
	public void setTokenNo(int tokenNo)
	{
		this.tokenNo = tokenNo;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}
	public String getFileName()
	{
		return fileName;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}
	public String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate()
	{
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
}