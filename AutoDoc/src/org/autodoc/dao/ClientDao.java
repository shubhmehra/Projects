package org.autodoc.dao;

import java.math.BigInteger;
import java.util.List;

import org.autodoc.entity.ClientInfo;
import org.autodoc.form.ClientInfoForm;

public interface ClientDao {
	List<Object[]> getCountryListData() throws Exception ;
	
	String addEditClientData(ClientInfo clientInfo, String status) throws Exception ;

	BigInteger countClientListData(String userName, StringBuffer query, List<String> paraList) throws Exception;

	List<ClientInfo> getClientListData(String userName, int start, int limit, StringBuffer query,
			List<String> paraList, boolean isForAllData, String sidx, String sord) throws Exception;

	String enableDisableClientStatus(int selectedClientId, int changeStatus, String userName) throws Exception;

	List<Object[]> getClientNamesList(String clientStatus) throws Exception;

	ClientInfoForm getClientinformation(int clientId) throws Exception;
}