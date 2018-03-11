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
@Table(name = "question_answer_templates")
@SequenceGenerator(name = "sequence", sequenceName = "question_answer_templates_question_id_seq", allocationSize=0)
public class QATemplates implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
	@Column(name="question_id")
	private int questionId;							//primary_key - integer NOT NULL
	
	@Column(name="letter_id")
	private int letterId;							//integer - NOT NULL
	
	@Column(name="question_no")
	private String questionNo;						//character varying(20) - NOT NULL
	
	@Column(name="question")
	private String question;						//character varying(1500) - NOT NULL
	
	@Column(name="question_type")
	private String questionType;					//character varying(20) - NOT NULL
	
	@Column(name="default_value")
	private String defaultValue;					//character varying(500) - NOT NULL
	
	
	@Column(name="yes_true_value")
	private String yesTrueValue;					//character varying(1500)
	
	@Column(name="no_false_value")
	private String noFalseValue;					//character varying(1500)
	
	
	@Column(name="positive_ans_child_id", updatable = false)
	private int positiveAnsChildId;					//integer NOT NULL DEFAULT 0
	
	@Column(name="negative_ans_child_id", updatable = false)
	private int negativeAnsChildId;					//integer NOT NULL DEFAULT 0
	
	
	@Column(name="created_date", updatable = false)
	private Date createdDate;						//TIMESTAMP - NOT NULL
	
	@Column(name="created_by", updatable = false)
	private String createdBy;						//character varying(100) - NOT NULL
	
	@Column(name="updated_date")
	private Date updatedDate;						//TIMESTAMP
	
	@Column(name="updated_by")
	private String updatedBy;						//character varying(100)

	/* Getters and Setters */
	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}

	public int getLetterId()
	{
		return letterId;
	}

	public void setLetterId(int letterId)
	{
		this.letterId = letterId;
	}

	public String getQuestionNo()
	{
		return questionNo;
	}

	public void setQuestionNo(String questionNo)
	{
		this.questionNo = questionNo;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getQuestionType()
	{
		return questionType;
	}

	public void setQuestionType(String questionType)
	{
		this.questionType = questionType;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public String getYesTrueValue()
	{
		return yesTrueValue;
	}

	public void setYesTrueValue(String yesTrueValue)
	{
		this.yesTrueValue = yesTrueValue;
	}

	public String getNoFalseValue()
	{
		return noFalseValue;
	}

	public void setNoFalseValue(String noFalseValue)
	{
		this.noFalseValue = noFalseValue;
	}

	public int getPositiveAnsChildId()
	{
		return positiveAnsChildId;
	}

	public void setPositiveAnsChildId(int positiveAnsChildId)
	{
		this.positiveAnsChildId = positiveAnsChildId;
	}

	public int getNegativeAnsChildId()
	{
		return negativeAnsChildId;
	}

	public void setNegativeAnsChildId(int negativeAnsChildId)
	{
		this.negativeAnsChildId = negativeAnsChildId;
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