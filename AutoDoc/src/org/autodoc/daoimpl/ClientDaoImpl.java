package org.autodoc.daoimpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.autodoc.dao.ClientDao;
import org.autodoc.entity.ClientInfo;
import org.autodoc.form.ClientInfoForm;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao {
	ResourceBundle rb = ResourceBundle.getBundle("resources/ClientDao");
	IdDaoImpl idDaoImpl = new IdDaoImpl();
	
	@Autowired
	private HibernateTemplate template;
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCountryListData() throws Exception {
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getCountryListData"));
			List<Object[]> list = query.list();
			
			return list;
		} finally {
			idDaoImpl.closeSession(session, "getCountryListData");
		}
	}
	
	@Override
	public String addEditClientData(ClientInfo clientInfo, String status) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(clientInfo);
			
			tx.commit();
			tx = null;
			return "Client information "+status+" Successfully.";
		} catch (Exception e) {
			if(tx != null)
				tx.rollback();
			throw e;
		} finally {
			idDaoImpl.closeSession(session, "addEditClientData");
		}
	}

	/*Start from here*/
	@SuppressWarnings("unchecked")
	public BigInteger countClientListData(String userName, StringBuffer query, List<String> paraList) throws Exception {
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			Query countQuery = session.createSQLQuery(rb.getString("countClientListData")+query);
				if (paraList.size()>0) {
					for(int i = 0; i < paraList.size(); i++) {
						countQuery.setInteger(i, Integer.parseInt(paraList.get(i)));
					}
				}
			List<BigInteger> list = (List<BigInteger>)countQuery.list();
			return list.get(0);
		} finally {
			idDaoImpl.closeSession(session, "countClientListData");
		}
	}

	@SuppressWarnings("unchecked")
	public List<ClientInfo> getClientListData(String userName, int start, int limit,
			StringBuffer query, List<String> paraList, boolean isForAllData, String sidx,
			String sord) throws Exception {
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			Query mainQry = session.createQuery(rb.getString("getClientListData")+query+" ORDER BY "+sidx+" "+sord);
				if (paraList.size()>0) {
					for(int i = 0; i < paraList.size(); i++) {
						mainQry.setInteger(i, Integer.parseInt(paraList.get(i)));
					}
				}
				if (!isForAllData) {
					mainQry.setFirstResult(start);
					mainQry.setMaxResults(limit);
				}
			List<ClientInfo> clientList = mainQry.list();
			
		    return clientList;
		} finally {
			idDaoImpl.closeSession(session, "getClientListData");
		}
	}
	/*Till here*/

	@Override
	public String enableDisableClientStatus(int selectedClientId, int changeStatus, String userName) throws Exception {
		Session session = null;
		Transaction tx = null;
		try {
			session = template.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			Query mainQry = session.createSQLQuery(rb.getString("enableDisableClientStatus"));
				mainQry.setInteger(0, changeStatus);
				mainQry.setInteger(1, selectedClientId);
			mainQry.executeUpdate();
			
			String statusChanged = "enabled";
			if (changeStatus == 0) {
				statusChanged = "disabled";
			}
			
			tx.commit();
			tx = null;
			return "Selected Client "+statusChanged+" Successfully.!";
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			idDaoImpl.closeSession(session, "enableDisableClientStatus");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClientNamesList(String clientStatus)
			throws Exception
	{
		Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			
			int status = 0;
			if (clientStatus.equalsIgnoreCase("active"))
				status = 1;
			
			Query query = session.createSQLQuery(rb.getString("getClientNamesList"));
				query.setInteger("status", status);
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
	public ClientInfoForm getClientinformation(int clientId) throws Exception
	{
		/*Session session = null;
		try
		{
			session = template.getSessionFactory().openSession();
			
			ClientInfo clientInfo = (ClientInfo) session.get(ClientInfo.class, clientId);
			
			ClientInfoForm clientInfoForm = new ClientInfoForm();

			BeanUtils.copyProperties(clientInfo, clientInfoForm);
			
			return clientInfoForm;
		}
		finally
		{
			idDaoImpl.closeSession(session, "getClientinformation");
		}*/
		
		Session session = null;
		try {
			session = template.getSessionFactory().openSession();
			
			Query query = session.createSQLQuery(rb.getString("getClientinformation"));
				query.setInteger("clientId", clientId);
			List<Object[]> oRecs = query.list();
			
			Object objs[];
			ClientInfoForm clientInfoForm = null;
			if (oRecs.size() != 0)
			{
				clientInfoForm = new ClientInfoForm();
				objs = (Object[]) oRecs.get(0);
				
				clientInfoForm.setClientId((Integer) objs[0]);
				clientInfoForm.setFileNo((Integer) objs[1]);
				clientInfoForm.setClientName((String) objs[2]);
				clientInfoForm.setGroupNames((String) objs[3]);
				clientInfoForm.setContactName((String) objs[4]);
				clientInfoForm.setPartnerName((String) objs[5]);
				clientInfoForm.setIncorporationDate((Date) objs[6]);
				
				clientInfoForm.setYearEnd((Integer) objs[7]);
				clientInfoForm.setAddressLine1((String) objs[8]);
				clientInfoForm.setAddressLine2((String) objs[9]);
				clientInfoForm.setCity((String) objs[10]);
				clientInfoForm.setCountryCode((Integer) objs[11]);
				clientInfoForm.setPostCode((String) objs[12]);
				
				clientInfoForm.setTelephoneNo((String) objs[13]);
				clientInfoForm.setEmailId((String) objs[14]);
				clientInfoForm.setCountryName((String) objs[20]);
			}
			return clientInfoForm;
		} finally {
			idDaoImpl.closeSession(session, "getClientinformation");
		}
	}
}