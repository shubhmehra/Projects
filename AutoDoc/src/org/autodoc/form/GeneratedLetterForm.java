package org.autodoc.form;

public class GeneratedLetterForm
{
	private int questionId;
	private int tokenId;
	private String questionNo;
	private String questionType;
	private String answer;
	private String wordField;
	
	public GeneratedLetterForm()
	{
		
	}
	
	public GeneratedLetterForm(int questionId, int tokenId, String questionNo,
			String questionType, String answer, String wordField)
	{
		super();
		this.questionId = questionId;
		this.tokenId = tokenId;
		this.questionNo = questionNo;
		this.questionType = questionType;
		this.answer = answer;
		this.wordField = wordField;
	}
	
	public int getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}
	public int getTokenId()
	{
		return tokenId;
	}
	public void setTokenId(int tokenId)
	{
		this.tokenId = tokenId;
	}
	public String getQuestionNo()
	{
		return questionNo;
	}
	public void setQuestionNo(String questionNo)
	{
		this.questionNo = questionNo;
	}
	public String getQuestionType()
	{
		return questionType;
	}
	public void setQuestionType(String questionType)
	{
		this.questionType = questionType;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	public String getWordField()
	{
		return wordField;
	}
	public void setWordField(String wordField)
	{
		this.wordField = wordField;
	}
}