package org.autodoc.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.autodoc.dao.LetterDao;
import org.autodoc.entity.GeneratedLtr;
import org.autodoc.entity.LettersInformation;
import org.autodoc.entity.PopulatedLettersInfo;
import org.autodoc.entity.QATemplates;
import org.autodoc.form.GeneratedLetterForm;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LetterDaoImpl implements LetterDao
{
	ResourceBundle rb = ResourceBundle.getBundle("resources/LetterDao");
	IdDaoImpl idDaoImpl = new IdDaoImpl();

	@Autowired
	private HibernateTemplate template;

	@Override
	public String addEditLetterInformation(LettersInformation lettersInformation, String status) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(lettersInformation);

			tx.commit();
			tx = null;
			return "Letter information " + status + " Successfully.";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditLetterInformation");
		}
	}

	/* Start from here */
	@SuppressWarnings("unchecked")
	public BigInteger countLetterListInformation(String userName, StringBuffer query, List<String> paraList) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			Query countQuery = session.createSQLQuery(rb.getString("countLetterListInformation") + query);
			//countQuery.setString(0, userName);
			/*if (paraList.size() > 0)
			{
				for (int i = 0; i < paraList.size(); i++)
				{
					countQuery.setString(i + 1, paraList.get(i));
				}
			}*/
			List<BigInteger> list = (List<BigInteger>) countQuery.list();
			return list.get(0);
		}
		finally
		{
			idDaoImpl.closeSession(session, "countLetterListInformation");
		}
	}

	@SuppressWarnings("unchecked")
	public List<LettersInformation> getLetterListInformation(String userName, int start, int limit,
			StringBuffer query, List<String> paraList, boolean isForAllData, String sidx, String sord) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();

			Query mainQry = session.createQuery(rb.getString("getLetterListInformation") + query
																	+ " ORDER BY " + sidx + " " + sord);
			//mainQry.setString(0, userName);
			/*if (paraList.size() > 0)
			{
				for (int i = 0; i < paraList.size(); i++)
				{
					mainQry.setString(i + 1, paraList.get(i));
				}
			}*/
			if (!isForAllData)
			{
				mainQry.setFirstResult(start);
				mainQry.setMaxResults(limit);
			}
			List<LettersInformation> letterList = mainQry.list();

			return letterList;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterListInformation");
		}
	}
	/* Till here */

	@Override
	public String deleteLetter(LettersInformation lettersInformation) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.delete(lettersInformation);

			tx.commit();
			tx = null;
			return "Success";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "deleteLetter");
		}
	}

	// From Here TAXABILITY related coding
	@SuppressWarnings("unchecked")
	public List<Object[]> getLetterNamesList(String typeOfData) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			
			String queryName = "getLetterNamesList";
			if (typeOfData.equalsIgnoreCase("unique"))
				queryName = "getLetterNameList";
			
			Query query = session.createSQLQuery(rb.getString(queryName));
			List<Object[]> list = query.list();

			return list;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterNamesList");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ListItem> getLetterNameList()
			throws Exception
	{
		Session session = null;
		try
		{
			List<ListItem> listItems = new ArrayList<ListItem>();
			session = template.getSessionFactory().openSession();

			Query query = session.createSQLQuery(rb.getString("getLetterNameList"));
			List<Object[]> oRec = query.list();
			
			ListItem listItem;
			Object objs[];
			for (int i = 0; i < oRec.size(); i++)
			{
				listItem = new ListItem();
				objs = (Object[]) oRec.get(i);
				
				listItem.setId(Integer.parseInt(objs[0].toString()));
				listItem.setValue(objs[1].toString());
				
				listItems.add(listItem);
			}

			return listItems;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterNameList");
		}
	}

	/*@Override
	public String addEditQATemplateInformation(QATemplates qaTemplates, String status) throws Exception
	{
		try
		{
			if (status.equalsIgnoreCase("added"))
			{
				if(qaTemplates.getQuestionType().equalsIgnoreCase("trueFalse"))
				{
					for (int i = 0; i < 2; i++)
					{
						if (i == 1)
						{
							String nextValue;
							if (qaTemplates.getDefaultValue().equalsIgnoreCase("True"))
								nextValue = "False";
							else
								nextValue = "True";
							qaTemplates.setQuestion("-");
							qaTemplates.setQuestionType("-");
							qaTemplates.setQuestionNo("-");
							qaTemplates.setDefaultValue(nextValue);
						}
						
						saveUpdateQATemplate(qaTemplates, "save");
					}
				}
				else if (qaTemplates.getQuestionType().equalsIgnoreCase("yesNo"))
				{
					for (int i = 0; i < 2; i++)
					{
						if (i == 1)
						{
							String nextValue;
							if (qaTemplates.getDefaultValue().equalsIgnoreCase("Yes"))
								nextValue = "No";
							else
								nextValue = "Yes";
							qaTemplates.setQuestion("-");
							qaTemplates.setQuestionType("-");
							qaTemplates.setQuestionNo("-");
							qaTemplates.setDefaultValue(nextValue);
						}
						
						saveUpdateQATemplate(qaTemplates, "save");
					}
				}
				
			}
			else
			{
				saveUpdateQATemplate(qaTemplates, "udpate");
			}
			return "Question Answer Template " + status + " Successfully.";
		}
		finally
		{
			System.out.println("All done");
		}
	}
	
	public void saveUpdateQATemplate(QATemplates qaTemplates, String whatToDo) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			if (whatToDo.equalsIgnoreCase("save"))
				session.save(qaTemplates);
			else
				session.update(qaTemplates);
			
			
			tx.commit();
			tx = null;
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditQATemplateData");
		}
	}*/
	
	@Override
	public String addEditQATemplateInformation(QATemplates qaTemplates, String status) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(qaTemplates);
			
			tx.commit();
			tx = null;
			return "Question Answer Template " + status + " Successfully.";
		}
		/*catch (EntityExistsException eee)
		{
			throw new QuestionIdAlreadyExists();  
		}
		catch (ConstraintViolationException e)
		{
			throw new QuestionIdAlreadyExists();
		}*/  
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditQATemplateData");
		}
	}
	
	/* Start from here */
	@SuppressWarnings("unchecked")
	public BigInteger countTaxabilityListInformation(StringBuffer query, List<String> paraList) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			Query countQuery = session.createSQLQuery(rb.getString("countTaxabilityListInformation") + query);
			// countQuery.setString(0, userName);
			if (paraList.size() > 0)
			{
				for (int i = 0; i < paraList.size(); i++)
				{
					// countQuery.setString(i+1, paraList.get(i));
					countQuery.setString(i, paraList.get(i));
				}
			}
			List<BigInteger> list = (List<BigInteger>) countQuery.list();
			return list.get(0);
		}
		finally
		{
			idDaoImpl.closeSession(session, "countTaxabilityListInformation");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTaxabilityListInformation(StringBuffer query, List<String> paraList,
			boolean isForAllData, int start, int limit, String sidx, String sord) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			Query mainQry = session.createSQLQuery(rb.getString("getTaxabilityListInformation") + query
																	+ " ORDER BY " + sidx + " " + sord);
			// mainQry.setString(0, userName);
			if (paraList.size() > 0)
			{
				for (int i = 0; i < paraList.size(); i++)
				{
					// mainQry.setString(i+1, paraList.get(i));
					mainQry.setString(i, paraList.get(i));
				}
			}
			if (!isForAllData)
			{
				mainQry.setFirstResult(start);
				mainQry.setMaxResults(limit);
			}
			List<Object[]> taxabilityList = (List<Object[]>) mainQry.list();

			return taxabilityList;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getTaxabilityListInformation");
		}
	}
	/* Till here */
	
	@Override
	public String removeTaxabilityQuestion(QATemplates qaTemplates)
			throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.delete(qaTemplates);

			tx.commit();
			tx = null;
			return "Success";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "removeTaxabilityQuestion");
		}
	}

	// Letter Flow setting
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionFlowSetting> getQuesListForLetteFlow(int letterId)
			throws Exception
	{
		Session session = null;
		try {
			List<QuestionFlowSetting> listQuestionFlowSetting = new ArrayList<QuestionFlowSetting>();
			
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getQuesListForLetteFlow"));
				query.setInteger(0, letterId);
			List<Object[]> oRecs = query.list();
			
			Object objs[];
			QuestionFlowSetting questionFlowSetting = null;
			if (oRecs.size() != 0)
			{
				for (int nRec = 0; nRec < oRecs.size(); nRec++)
				{
					questionFlowSetting = new QuestionFlowSetting();
					objs = (Object[]) oRecs.get(nRec);
					
					questionFlowSetting.setQuestionId(objs[0].toString());				// question_id
					questionFlowSetting.setQuestionNo(objs[1].toString());				// question_no
					questionFlowSetting.setQuestion(objs[2].toString());				// question
					questionFlowSetting.setQuestionType(objs[3].toString());			// question_type
					questionFlowSetting.setDefaultValue(objs[4].toString());			// default_value
					
					questionFlowSetting.setPositiveAnsChildId(objs[5].toString());			// positive_ans_child_id
					questionFlowSetting.setNegativeAnsChildId(objs[6].toString());			// negative_ans_child_id
					
					questionFlowSetting.setLetterId(objs[6].toString());					// letter_id
					
					if (objs[0] != null)
					{
						Query childQuestionIdsListQuery = session.createSQLQuery(rb.getString("getChildQuestionIds"));
							childQuestionIdsListQuery.setInteger(0, letterId);
							childQuestionIdsListQuery.setInteger(1, Integer.parseInt(objs[0].toString()));
						List<Object[]> oRec = childQuestionIdsListQuery.list();
						
						List<ListItem> childQuestionIdsListQueryORecs = new ArrayList<ListItem>();
						
						ListItem listItem;
						Object objs1[];
						for (int i = 0; i < oRec.size(); i++)
						{
							listItem = new ListItem();
							objs1 = (Object[]) oRec.get(i);
							
							listItem.setId(Integer.parseInt(objs1[0].toString()));
							listItem.setValue(objs1[1].toString());
							
							childQuestionIdsListQueryORecs.add(listItem);
						}
						
						questionFlowSetting.setPositiveAnsChildIds(childQuestionIdsListQueryORecs);
						questionFlowSetting.setNegativeAnsChildIds(childQuestionIdsListQueryORecs);
					}
					
					//List<ListItem> childQuestionIdsList = 
					listQuestionFlowSetting.add(questionFlowSetting);
				}
			}
			
			return listQuestionFlowSetting;
		} finally {
			idDaoImpl.closeSession(session, "getQuesListForLetteFlow");
		}
	}
	
	@Override
	public String saveLetteFlow(QuestionFlowSetting questionFlowSet)
			throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			String[] questionIds = questionFlowSet.getQuestionId().split(",");
			String[] positiveAnsChildIds = questionFlowSet.getPositiveAnsChildId().split(",");
			String[] negativeAnsChildIds = questionFlowSet.getNegativeAnsChildId().split(",");
			
			for (int i = 0; i < questionIds.length; i++)
			{
				Query queryToSave = session.createQuery("FROM QATemplates WHERE questionId = :questionId ");
					queryToSave.setInteger("questionId", Integer.parseInt(questionIds[i]));
				QATemplates qaTemplate = (QATemplates) queryToSave.list().get(0);
				
				qaTemplate.setPositiveAnsChildId(Integer.parseInt(positiveAnsChildIds[i]));
				qaTemplate.setNegativeAnsChildId(Integer.parseInt(negativeAnsChildIds[i]));
				
				session.update(qaTemplate);
			}
			
			tx.commit();
			tx = null;
			return "Letter flow saved Successfully.";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditLetterInformation");
		}
	}
	
	// Generate Letter
	public long getTokenNo() throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getTokenNo"));
		    long key = ((BigInteger) query.uniqueResult()).longValue();
		    return key;
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditLetterInformation");
		}
	}
	
	// Generate Letter
	@Override
	public String saveGeneratedLetterInformation(GeneratedLtr generatedLtr)
			throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.save(generatedLtr);
			
			tx.commit();
			tx = null;
			return "Letter Generated Successfully.";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "addEditLetterInformation");
		}
	}
	
	// Generate Letter
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDataToGenerateLetter(int letterId, int questionId) throws Exception
	{
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getDataToGenerateLetter"));
				query.setInteger("letterId", letterId);
				query.setInteger("questionId", questionId);
			List<Object[]> oRecs = query.list();
			
			Object objs[];
			List<String> questionFlowSetting = null;
			if (oRecs.size() != 0)
			{
				questionFlowSetting = new ArrayList<String>();
				objs = (Object[]) oRecs.get(0);
				
				questionFlowSetting.add(objs[0].toString());				// question_id
				questionFlowSetting.add(objs[1].toString());				// question_no
				questionFlowSetting.add(objs[2].toString());				// question
				questionFlowSetting.add(objs[3].toString());				// question_type
				questionFlowSetting.add(objs[4].toString());				// default_value
				questionFlowSetting.add(String.valueOf(objs[5]));			// positive_ans_child_id
				questionFlowSetting.add(String.valueOf(objs[6]));			// negative_ans_child_id
				questionFlowSetting.add(objs[7].toString());				// letter_id
			}
			
			return questionFlowSetting;
		} finally {
			idDaoImpl.closeSession(session, "getQuesListForLetteFlow");
		}
	}
	
	// Generate Letter
	@SuppressWarnings("unchecked")
	@Override
	public List<GeneratedLetterForm> getGeneratedLtrData(int tokenNo) throws Exception
	{
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			List<GeneratedLetterForm> genLtrList = new ArrayList<GeneratedLetterForm>();
			
			Query query = session.createSQLQuery(rb.getString("getGeneratedLtrData"));
				query.setInteger("tokenNo", tokenNo);
			List<Object[]> oRecs = query.list();
			
			Object objs[];
			GeneratedLetterForm genLtr = null;
			if (oRecs.size() != 0)
			{
				for (int i = 0; i < oRecs.size(); i++)
				{
					genLtr = new GeneratedLetterForm();
					objs = (Object[]) oRecs.get(i);
					
					genLtr.setQuestionId((Integer) objs[0]);					// question_id
					genLtr.setTokenId((Integer) objs[1]);						// tokenId
					genLtr.setQuestionNo((String) objs[2]);						// question_no
					genLtr.setQuestionType((String) objs[3]);					// question_type
					genLtr.setAnswer((String) objs[4]);							// answer
					genLtr.setWordField((String) objs[5]);						// word_field
					
					genLtrList.add(genLtr);
				}
			}
			return genLtrList;
		} finally {
			idDaoImpl.closeSession(session, "getGeneratedLtrData");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getLetterAndOrgNameUsingLetterId(int letterId) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();

			Query mainQry = session.createSQLQuery(rb.getString("getLetterAndOrgNameUsingLetterId"));
			mainQry.setInteger("letterId", letterId);
			
			List oRecs = mainQry.list();
			
			Object objs[] = null;
			if (oRecs.size() != 0)
			{
				objs = (Object[]) oRecs.get(0);
			}
			
			return objs;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterNameUsingLetterId");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String checkQuestionNoExists(String questionNo, int letterId) throws Exception
	{
		Session session = null;
		String status = "OK";
		try
		{
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("checkQuestionNoExists"));
				query.setString("questionNo", questionNo);
				query.setInteger("letterId", letterId);
			List list = query.list();
			
			if (list.size() > 0)
				status = "Question no should not be same.!";
			
		    return status;
		}
		finally
		{
			idDaoImpl.closeSession(session, "checkQuestionNoExists");
		}
	}
	
	@Override
	public PopulatedLettersInfo getPopulatedLettersInfoUsingTokenId(int tokenNo) throws Exception
	{
		Session session = null;
		try
		{
			PopulatedLettersInfo populatedLettersInfo = null;
			session = template.getSessionFactory().openSession();

			Query query = session.createQuery("FROM PopulatedLettersInfo WHERE tokenNo = :tokenNo ORDER BY createdDate DESC");
			query.setInteger("tokenNo", tokenNo);
			if (query.list().size() > 0)
				populatedLettersInfo = (PopulatedLettersInfo) query.list().get(0);
			
			return populatedLettersInfo;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterNameUsingLetterId");
		}
	}

	@Override
	public void savePopulatedLettersInfo(PopulatedLettersInfo populatedLettersInfo) throws Exception
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.save(populatedLettersInfo);
			
			tx.commit();
			tx = null;
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "savePopulatedLettersInfo");
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getLetterClientIdsUsingTokenId(int tokenNo) throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();

			Query mainQry = session.createSQLQuery(rb.getString("getLetterClientIdsUsingTokenId"));
			mainQry.setInteger("tokenNo", tokenNo);
			
			List oRecs = mainQry.list();
			
			Object objs[] = null;
			if (oRecs.size() != 0)
				objs = (Object[]) oRecs.get(0);
			
			return objs;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getLetterClientIdsUsingTokenId");
		}
	}
	
	// dndTREE related
	/*@Override
	public String saveLetterFlow(Map<String, HashMap<String, Object>> mapData) throws Exception
	{
		
		Session session = null;
		Transaction tx = null;
		try
		{
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			//iterate(mapData);
			//System.out.println("name=>"+mapData.get("id"));
			//System.out.println("vavnboln=>"+mapData.get("name"));
			
			
			// get all the set of keys
	        Set<String> keys = mapData.keySet();
	 
	        // iterate through the key set and display key and values
	        for (String key : keys) {
	            System.out.println("Key = " + key);
	            System.out.println("Values = " + mapData.get(key) + "\n");
	            
	            if(key == "children")
	            {
	            	Set<String> keys1 = mapData.get(key).keySet();
	            	for (String key1 : keys1) {
	            		System.out.println("Key = " + key1);
	    	            System.out.println("Values = " + mapData.get(key).get(key1) + "\n");
	            	}
	            }
	        }
	        
			
			for (int i = 0; i < mapData.size(); i++)
			{
				//System.out.println("id=>"+mapData.values().toArray());
				System.out.println("name=>"+mapData.get("id"));
				System.out.println("vavnboln=>"+mapData.values());
			}
			
			Object[] values = mapData.values().toArray(new Object[mapData.size()]);
			


			for (Entry<String, Object> entry : mapData.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
			
			//session.saveOrUpdate(qaTemplates);

			//tx.commit();
			tx = null;
			return "Letter Flow set Successfully.";
		}
		catch (Exception e)
		{
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally
		{
			idDaoImpl.closeSession(session, "saveLetterFlow");
		}
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getQuestionListData(int letterId) throws Exception
	{
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getQuestionListData"));
				query.setInteger(0, letterId);
			List<Object[]> list = query.list();
			
			return list;
		} finally {
			idDaoImpl.closeSession(session, "getQuestionListData");
		}
	}*/
	
	/*public void iterate(Map<String, Object> mapData) {
		for (Map.Entry<String, Object> entry : mapData.entrySet()) {
	        //System.out.println("Key is: " + entry.getKey());
	        if (entry.getValue() instanceof Map) {
	            //System.out.println("Map found, digging further");
	            iterate((Map<String, Object>) entry.getValue());
	        } else {
	            //System.out.println("Leaf found, value is: " + entry.getValue());
	        }
	    }
	}*/
}