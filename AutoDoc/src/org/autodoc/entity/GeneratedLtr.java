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
@Table(name = "generated_letter_info")
@SequenceGenerator(name = "sequence", sequenceName = "generated_letter_info_generated_letter_id_seq", allocationSize=0)
public class GeneratedLtr implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name="generated_letter_id")
	private int genLtrId;							// primary_key
	
	@Column(name="client_id")
	private int clientId;							// integer - Not Null
	@Column(name="letter_id")
	private int letterId;							// integer - Not Null
	@Column(name="question_id")
	private int questionId;							// integer - Not Null
	@Column(name="answer")
	private String answer;							// 500
	@Column(name="token_id")
	private int tokenNo;							// Not Null
	@Column(name="created_date", updatable = false)
	private Date createdDate;						// TIMESTAMP - Not Null
	@Column(name="created_by", updatable = false)
	private String createdBy;						// 100 - Not Null
	@Column(name="updated_date")
	private Date updatedDate;						// TIMESTAMP
	@Column(name="updated_by")
	private String updatedBy;						// 100
	
	public int getGenLtrId()
	{
		return genLtrId;
	}
	public void setGenLtrId(int genLtrId)
	{
		this.genLtrId = genLtrId;
	}
	
	public int getClientId()
	{
		return clientId;
	}
	public void setClientId(int clientId)
	{
		this.clientId = clientId;
	}
	
	public int getLetterId()
	{
		return letterId;
	}
	public void setLetterId(int letterId)
	{
		this.letterId = letterId;
	}
	
	public int getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}
	
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
	public int getTokenNo()
	{
		return tokenNo;
	}
	public void setTokenNo(int tokenNo)
	{
		this.tokenNo = tokenNo;
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