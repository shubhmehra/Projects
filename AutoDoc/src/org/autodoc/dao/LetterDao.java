package org.autodoc.dao;

import java.math.BigInteger;
import java.util.List;

import org.autodoc.entity.GeneratedLtr;
import org.autodoc.entity.LettersInformation;
import org.autodoc.entity.PopulatedLettersInfo;
import org.autodoc.entity.QATemplates;
import org.autodoc.form.GeneratedLetterForm;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.autodoc.form.SysParaForm;

public interface LetterDao
{
	String addEditLetterInformation(LettersInformation lettersInformation, String status) throws Exception;
	
	BigInteger countLetterListInformation(String userName, StringBuffer query, List<String> paraList) throws Exception;
	
	List<LettersInformation> getLetterListInformation(String userName, int start, int limit,
			StringBuffer query, List<String> paraList, boolean isForAllData, String sidx, String sord) throws Exception;

	String deleteLetter(LettersInformation lettersInformation) throws Exception;

//From Here TAXABILITY related coding
	List<Object[]> getLetterNamesList(String typeOfData) throws Exception;
	List<ListItem> getLetterNameList() throws Exception;

	String addEditQATemplateInformation(QATemplates qaTemplates, String status) throws Exception;

	BigInteger countTaxabilityListInformation(StringBuffer query, List<String> paraList) throws Exception;

	List<Object[]> getTaxabilityListInformation(StringBuffer query, List<String> paraList, boolean isForAllData,
			int start, int limit, String sidx, String sord) throws Exception;

	String removeTaxabilityQuestion(QATemplates qaTemplates) throws Exception;

	List<QuestionFlowSetting> getQuesListForLetteFlow(int letterId) throws Exception;

	String saveLetteFlow(QuestionFlowSetting questionFlowSet) throws Exception;
	
	// Generate Letter
	long getTokenNo() throws Exception;
	
	// Generate Letter
	String saveGeneratedLetterInformation(GeneratedLtr generatedLtr) throws Exception;

	// Generate Letter
	List<String> getDataToGenerateLetter(int letterId, int questionId) throws Exception;

	List<GeneratedLetterForm> getGeneratedLtrData(int tokenNo) throws Exception;

	Object[] getLetterAndOrgNameUsingLetterId(int letterId) throws Exception;

	String checkQuestionNoExists(String questionNo, int letterId) throws Exception;

	PopulatedLettersInfo getPopulatedLettersInfoUsingTokenId(int tokenNo) throws Exception;

	void savePopulatedLettersInfo(PopulatedLettersInfo populatedLettersInfo) throws Exception;

	Object[] getLetterClientIdsUsingTokenId(int tokenNo) throws Exception;

	// dndTREE related
	/*String saveLetterFlow(Map<String, HashMap<String, Object>> mapData) throws Exception;*/
	
	/*List<Object[]> getQuestionListData(int letterId) throws Exception;*/
}