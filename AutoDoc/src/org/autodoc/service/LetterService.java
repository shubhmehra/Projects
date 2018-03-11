package org.autodoc.service;

import java.util.List;
import java.util.Map;

import org.autodoc.dao.LetterDao;
import org.autodoc.entity.GeneratedLtr;
import org.autodoc.entity.LettersInformation;
import org.autodoc.entity.PopulatedLettersInfo;
import org.autodoc.form.GeneratedLetterForm;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.autodoc.form.SysParaForm;

public interface LetterService
{
	String addEditLetterInformation(LetterDao dao, LettersInformation lettersInformation, String userName,
			int letterIdForEdit) throws Exception;
	
	Map<String, Object> getLetterListInformation(LetterDao dao, String userName, String letterStatus,
			int page, int limit, String sidx, String sord) throws Exception;

	String deleteLetter(LetterDao dao, int selectedLetterId) throws Exception;
	
//From Here TAXABILITY related coding
	List<Object[]> getLetterNamesList(LetterDao dao, String typeOfData) throws Exception;
	List<ListItem> getLetterNameList(LetterDao dao) throws Exception;

	String addEditQATemplateInformation(LetterDao dao, String userName, int letterId, String questionNo,
			String question, String questionType, String defaultValue, int idForEdit, String yesTrueValue, String noFalseValue) throws Exception;
	
	Map<String, Object> getTaxabilityListInformation(LetterDao dao, int page,
			int limit, String sidx, String sord) throws Exception;

	String removeTaxabilityQuestion(LetterDao dao, int selectedQuestionId) throws Exception;

	List<QuestionFlowSetting> getQuesListForLetteFlow(LetterDao dao,
			int letterId) throws Exception;

	String saveLetteFlow(LetterDao dao, QuestionFlowSetting questionFlowSet) throws Exception;

	// Generate Letter
	long getTokenNo(LetterDao dao) throws Exception;
	
	// Generate Letter
	String saveGeneratedLetterInformation(LetterDao dao, int clientId, int letterId,
			int questionId, String answer, String userName, int tokenNo) throws Exception;
	
	// Generate Letter
	List<String> getDataToGenerateLetter(LetterDao dao, int letterId, int questionId) throws Exception;

	// Generate Letter
	List<GeneratedLetterForm> getGeneratedLtrData(int tokenNo, LetterDao dao) throws Exception;

	Object[] getLetterAndOrgNameUsingLetterId(int letterId, LetterDao dao) throws Exception;

	String checkQuestionNoExists(LetterDao dao, String questionNo, int letterId) throws Exception;

	PopulatedLettersInfo getPopulatedLettersInfoUsingTokenId(int tokenNo, LetterDao dao) throws Exception;

	void savePopulatedLettersInfo(PopulatedLettersInfo populatedLettersInfo, LetterDao dao) throws Exception;

	Object[] getLetterClientIdsUsingTokenId(int tokenNo, LetterDao dao) throws Exception;

	// dndTREE related
	/*String saveLetterFlow(Map<String, HashMap<String, Object>> mapData, LetterDao dao) throws Exception;*/
	
	/*String getQuestionListData(int letterId, LetterDao dao) throws Exception;*/
}