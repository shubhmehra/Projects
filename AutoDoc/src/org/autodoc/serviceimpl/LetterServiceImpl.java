/**
 * 
 */
package org.autodoc.serviceimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.autodoc.dao.LetterDao;
import org.autodoc.entity.GeneratedLtr;
import org.autodoc.entity.LettersInformation;
import org.autodoc.entity.PopulatedLettersInfo;
import org.autodoc.entity.QATemplates;
import org.autodoc.form.GeneratedLetterForm;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.autodoc.service.LetterService;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService
{
	@Override
	public String addEditLetterInformation(LetterDao dao, LettersInformation lettersInformation,
										   String userName, int letterIdForEdit) throws Exception
	{
		String status = "added";
		if (letterIdForEdit != 0)
		{
			lettersInformation.setLetterId(letterIdForEdit);
			lettersInformation.setUpdatedDate(new Date());
			lettersInformation.setUpdatedBy(userName);
			status = "updated";
			lettersInformation.setStatus("UPDATED");
		}
		else
		{
			lettersInformation.setCreatedDate(new Date());
			lettersInformation.setCreatedBy(userName);
			lettersInformation.setStatus("NEW");
		}

		return dao.addEditLetterInformation(lettersInformation, status);
	}

	/* Start from here */
	@Override
	public Map<String, Object> getLetterListInformation(LetterDao dao, String userName, String letterStatus,
											int page, int limit, String sidx, String sord) throws Exception
	{
		StringBuffer query = new StringBuffer();
		List<String> paraList = new ArrayList<String>();

		// buildQueryForLetterList(letterStatus, query, paraList, dao);

		BigInteger c = dao.countLetterListInformation(userName, query, paraList);

		Double count = c.doubleValue();
		double total_pages = 0;

		if (count > 0)
			total_pages = (int) Math.ceil(count / limit);
		else
			total_pages = 0;

		if (page > total_pages)
			page = (int) total_pages;

		int start = (limit * page) - limit;
		
		List<LettersInformation> list = dao.getLetterListInformation(userName,
				start, limit, query, paraList, false, sidx, sord);

		Map<String, Object> letterListMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> letterList = new ArrayList<Map<String, Object>>();

		Map<String, Object> letterListSubMap = null;
		List<String> cell = null;

		// This is done to set SR No in the grid
		if (start < 0)
			start += 11;
		else
			start += 1;

		int i = 1;
		for (LettersInformation header : list)
		{
			letterListSubMap = new LinkedHashMap<String, Object>();
			letterListSubMap.put("id", header.getLetterId());
			cell = new ArrayList<String>();
			
			cell.add(String.valueOf(start));							//Sr.No
			cell.add(String.valueOf(header.getLetterId()));				//letterId
			cell.add(String.valueOf(header.getLetterName()));			//letterName
			cell.add(String.valueOf(header.getStatus()));				//status
			cell.add(String.valueOf(header.getCreatedDate()));			//createdDate
			cell.add(String.valueOf(header.getCreatedBy()));			//createdBy
			cell.add(String.valueOf(header.getUpdatedDate()));			//updatedDate
			cell.add(String.valueOf(header.getUpdatedBy()));			//updatedBy
			
			letterListSubMap.put("cell", cell);
			letterList.add(letterListSubMap);
			i++;
			start++;
		}
		letterListMap.put("rows", letterList);
		letterListMap.put("page", page);
		letterListMap.put("total", total_pages);
		letterListMap.put("records", count);

		return letterListMap;
	}

	/*public void buildQueryForLetterList(String letterStatus, StringBuffer query, List<String> paraList, LetterDao dao) throws Exception {
		query.append(" AND status = ? ");
		paraList.add(letterStatus);
	}*/
	/*Till here*/

	@Override
	public String deleteLetter(LetterDao dao, int selectedLetterId) throws Exception
	{
		LettersInformation lettersInformation = new LettersInformation();
		lettersInformation.setLetterId(selectedLetterId);
		return dao.deleteLetter(lettersInformation);
	}

//From Here TAXABILITY related coding
	@Override
	public List<Object[]> getLetterNamesList(LetterDao dao, String typeOfData) throws Exception
	{
		return dao.getLetterNamesList(typeOfData);
	}
	
	@Override
	public List<ListItem> getLetterNameList(LetterDao dao) throws Exception
	{
		return dao.getLetterNameList();
	}

	@Override
	public String addEditQATemplateInformation(LetterDao dao, String userName, int letterId, String questionNo,
			String question, String questionType, String defaultValue, int idForEdit, String yesTrueValue, String noFalseValue) throws Exception
	{
		QATemplates qaTemplates = new QATemplates();

		String status = "added";
		if (idForEdit != 0)
		{
			qaTemplates.setQuestionId(idForEdit);
			qaTemplates.setUpdatedDate(new Date());
			qaTemplates.setUpdatedBy(userName);
			status = "updated";
		}
		else
		{
			qaTemplates.setCreatedDate(new Date());
			qaTemplates.setCreatedBy(userName);
		}

		qaTemplates.setLetterId(letterId);
		qaTemplates.setQuestionNo(questionNo);
		qaTemplates.setQuestion(question);
		qaTemplates.setQuestionType(questionType);
		qaTemplates.setDefaultValue(defaultValue);
		qaTemplates.setYesTrueValue(yesTrueValue);
		qaTemplates.setNoFalseValue(noFalseValue);
		
		return dao.addEditQATemplateInformation(qaTemplates, status);
	}

	@Override
	public Map<String, Object> getTaxabilityListInformation(LetterDao dao, int page, int limit,
															String sidx, String sord) throws Exception
	{
		StringBuffer query = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		// buildQueryForLetterList(letterStatus, query, paraList, dao);
		BigInteger c = dao.countTaxabilityListInformation(query, paraList);

		Double count = c.doubleValue();
		double total_pages = 0;

		if (count > 0)
			total_pages = (int) Math.ceil(count / limit);
		else
			total_pages = 0;

		if (page > total_pages)
			page = (int) total_pages;

		int start = (limit * page) - limit;

		List<Object[]> list = dao.getTaxabilityListInformation(query, paraList, false, start, limit, sidx, sord);

		Map<String, Object> taxabilityMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> taxabilityList = new ArrayList<Map<String, Object>>();

		Map<String, Object> taxabilityListSubMap = null;
		List<String> cell = null;

		// This is done to set SR No in the grid
		if (start < 0)
			start += 11;
		else
			start += 1;

		int i = 1;
		for (Object[] header : list)
		{
			taxabilityListSubMap = new LinkedHashMap<String, Object>();
			taxabilityListSubMap.put("id", header[0]);
			cell = new ArrayList<String>();

			cell.add(String.valueOf(start));							// Sr.No
			cell.add(String.valueOf(header[0]));						// Question Id
			cell.add(String.valueOf(header[1]));						// Letter Id
			cell.add(String.valueOf(header[2]));						// Letter Name
			cell.add(String.valueOf(header[3]));						// Question No
			cell.add(String.valueOf(header[4]));						// Question
			cell.add(String.valueOf(header[5]));						// Question Type
			cell.add(String.valueOf(header[6]));						// Default Value
			cell.add(String.valueOf(header[7]));						// yes_true_value
			cell.add(String.valueOf(header[8]));						// no_false_value
			cell.add(String.valueOf(header[9]));						// Created By

			taxabilityListSubMap.put("cell", cell);
			taxabilityList.add(taxabilityListSubMap);
			i++;
			start++;
		}
		taxabilityMap.put("rows", taxabilityList);
		taxabilityMap.put("page", page);
		taxabilityMap.put("total", total_pages);
		taxabilityMap.put("records", count);

		return taxabilityMap;
	}

	@Override
	public String removeTaxabilityQuestion(LetterDao dao, int selectedQuestionId) throws Exception
	{
		QATemplates qaTemplates = new QATemplates();
			qaTemplates.setQuestionId(selectedQuestionId);
		return dao.removeTaxabilityQuestion(qaTemplates);
	}

	

	@Override
	public List<QuestionFlowSetting> getQuesListForLetteFlow(LetterDao dao,
			int letterId) throws Exception
	{
		return dao.getQuesListForLetteFlow(letterId);
	}

	@Override
	public String saveLetteFlow(LetterDao dao,
			QuestionFlowSetting questionFlowSet) throws Exception
	{
		return dao.saveLetteFlow(questionFlowSet);
	}
	
	// Generate Letter
	@Override
	public long getTokenNo(LetterDao dao) throws Exception
	{
		return dao.getTokenNo();
	}
	
	// Generate Letter
	@Override
	public String saveGeneratedLetterInformation(LetterDao dao, int clientId, int letterId,
			int questionId, String answer, String userName, int tokenNo) throws Exception
	{
		GeneratedLtr generatedLtr = new GeneratedLtr();
			generatedLtr.setClientId(clientId);
			generatedLtr.setLetterId(letterId);
			generatedLtr.setQuestionId(questionId);
			generatedLtr.setAnswer(answer);
			generatedLtr.setTokenNo(tokenNo);
			generatedLtr.setCreatedBy(userName);
			generatedLtr.setCreatedDate(new Date());
		return dao.saveGeneratedLetterInformation(generatedLtr);
	}
	
	// Generate Letter
	@Override
	public List<String> getDataToGenerateLetter(LetterDao dao, int letterId, int questionId) throws Exception
	{
		return dao.getDataToGenerateLetter(letterId, questionId);
	}

	// Generate Letter
	@Override
	public List<GeneratedLetterForm> getGeneratedLtrData(int tokenNo, LetterDao dao) throws Exception
	{
		return dao.getGeneratedLtrData(tokenNo);
	}

	@Override
	public Object[] getLetterAndOrgNameUsingLetterId(int letterId, LetterDao dao) throws Exception
	{
		return dao.getLetterAndOrgNameUsingLetterId(letterId);
	}

	@Override
	public String checkQuestionNoExists(LetterDao dao, String questionNo, int letterId)
			throws Exception
	{
		return dao.checkQuestionNoExists(questionNo, letterId);
	}
	
	@Override
	public PopulatedLettersInfo getPopulatedLettersInfoUsingTokenId(int tokenNo, LetterDao dao) throws Exception
	{
		return dao.getPopulatedLettersInfoUsingTokenId(tokenNo);
	}
	
	@Override
	public void savePopulatedLettersInfo(PopulatedLettersInfo populatedLettersInfo, LetterDao dao) throws Exception
	{
		dao.savePopulatedLettersInfo(populatedLettersInfo);
	}

	@Override
	public Object[] getLetterClientIdsUsingTokenId(int tokenNo, LetterDao dao)
			throws Exception
	{
		return dao.getLetterClientIdsUsingTokenId(tokenNo);
	}
	
	// dndTREE related
	/*@Override
	public String saveLetterFlow(Map<String, HashMap<String, Object>> mapData, LetterDao dao)
			throws Exception
	{
		return dao.saveLetterFlow(mapData);
	}*/
	
	/*@Override
	public String getQuestionListData(int letterId, LetterDao dao) throws Exception
	{
		// Getting the list of question with its answer and all in list format
		List<Object[]> list = dao.getQuestionListData(letterId);
		
		String dataInString = null;
		if (list.size() == 0)
		{
			dataInString = "noDataForTheSelectedLetter";
		}
		else
		{
			// Converting the data into the JSON format to send it the the client side to show in the family tree format
			Object objs[];
			int counter = 0;
			for (int nRec = 0; nRec < list.size(); nRec++)
			{
				objs = (Object[]) list.get(nRec);
				
				if (objs[2] == null || objs[2].toString() == null || objs[2].toString() == "")
					counter++;
			}
			
			dataInString = "{";
			if (counter == list.size())
			{
				for (int nRec = 0; nRec < list.size(); nRec++)
				{
					objs = (Object[]) list.get(nRec);
					
					if (nRec == 0)
					{
						dataInString	+=	"'id':'"+objs[0].toString()+"',"
										+	"'name':'"+objs[1].toString()+"'";
						if (nRec != list.size()-1)
							dataInString	+=	",";
						else
							dataInString	+=	"}";
					}
					else
					{
						if (nRec == 1)
							dataInString	+=	"'children':[";
						
						dataInString	+=	"{"
											+	"'id':'"+objs[0].toString()+"',"
											+	"'name':'"+objs[1].toString()+"',"
											+	"'size':'"+nRec+956+"'";
										
						if (nRec != list.size()-1)
							dataInString	+=	"},";
						else
							dataInString	+=	"}]}";
					}
				}
			}
		}
		
		//System.out.println("dataInString==>"+dataInString);
		return dataInString;
		
		StringBuffer tempQuery = new StringBuffer();
		List<Integer> paraList = new ArrayList<Integer>();
		
		tempQuery.append(" AND question_id NOT IN(");
		for (int i = 0; i < usedQuestionIds.length; i++)
		{
			if (i != (usedQuestionIds.length-1))
				tempQuery.append("?,");
			else
				tempQuery.append("?");
			
			paraList.add(Integer.parseInt(usedQuestionIds[i]));
		}
		
		tempQuery.append(");");
		return dao.getQuestionListData(letterId, tempQuery, paraList);
		
	}*/
}