package org.autodoc.service;

import java.util.List;
import java.util.Map;

import org.autodoc.dao.ClientDao;
import org.autodoc.entity.ClientInfo;
import org.autodoc.form.ClientInfoForm;

public interface ClientService {
	public List<Object[]> getCountryListData(ClientDao dao) throws Exception ;
	
	public String addEditClientData(ClientDao dao, ClientInfo clientInfo, String userName, int clientIdForEdit) throws Exception;

	public Map<String, Object> getClientListData(ClientDao dao, String userName,
			String enabled, int page, int limit, String sidx, String sord) throws Exception;

	public String enableDisableClientStatus(ClientDao dao,
			int selectedClientId, int changeStatus, String userName) throws Exception;

	public List<Object[]> getClientNamesList(ClientDao dao, String clientStatus) throws Exception;

	public ClientInfoForm getClientinformation(int clientId) throws Exception;
}