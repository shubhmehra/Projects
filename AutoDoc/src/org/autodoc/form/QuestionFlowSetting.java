package org.autodoc.form;

import java.util.ArrayList;
import java.util.List;

public class QuestionFlowSetting
{
	private String questionId;
	private String questionNo;
	private String question;
	private String questionType;
	private String defaultValue;
	//private String childQueId;
	private String positiveAnsChildId;
	private String negativeAnsChildId;
	
	private String letterId;
	
	List<ListItem> positiveAnsChildIds = new ArrayList<ListItem>();
	List<ListItem> negativeAnsChildIds = new ArrayList<ListItem>();
	
	List<QuestionFlowSetting> questionFlowSettingList = new ArrayList<QuestionFlowSetting>();
	
	public String getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(String questionId)
	{
		this.questionId = questionId;
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
	
	public String getPositiveAnsChildId()
	{
		return positiveAnsChildId;
	}
	public void setPositiveAnsChildId(String positiveAnsChildId)
	{
		this.positiveAnsChildId = positiveAnsChildId;
	}
	
	public String getNegativeAnsChildId()
	{
		return negativeAnsChildId;
	}
	public void setNegativeAnsChildId(String negativeAnsChildId)
	{
		this.negativeAnsChildId = negativeAnsChildId;
	}
	
	public String getLetterId()
	{
		return letterId;
	}
	public void setLetterId(String letterId)
	{
		this.letterId = letterId;
	}
	
	public List<QuestionFlowSetting> getQuestionFlowSettingList()
	{
		return questionFlowSettingList;
	}
	public void setQuestionFlowSettingList(
			List<QuestionFlowSetting> questionFlowSettingList)
	{
		this.questionFlowSettingList = questionFlowSettingList;
	}
	
	public List<ListItem> getPositiveAnsChildIds()
	{
		return positiveAnsChildIds;
	}
	public void setPositiveAnsChildIds(List<ListItem> positiveAnsChildIds)
	{
		this.positiveAnsChildIds = positiveAnsChildIds;
	}
	
	public List<ListItem> getNegativeAnsChildIds()
	{
		return negativeAnsChildIds;
	}
	public void setNegativeAnsChildIds(List<ListItem> negativeAnsChildIds)
	{
		this.negativeAnsChildIds = negativeAnsChildIds;
	}
}