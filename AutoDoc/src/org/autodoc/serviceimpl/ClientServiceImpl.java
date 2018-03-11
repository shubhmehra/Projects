package org.autodoc.serviceimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.autodoc.controller.CommonController;
import org.autodoc.dao.ClientDao;
import org.autodoc.entity.ClientInfo;
import org.autodoc.form.ClientInfoForm;
import org.autodoc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends CommonController implements ClientService {
	@Autowired
	ClientDao clientDao;
	
	@Override
	public List<Object[]> getCountryListData(ClientDao dao) throws Exception {
		return dao.getCountryListData();
	}
	
	@Override
	public String addEditClientData(ClientDao dao, ClientInfo clientInfo, String userName, int clientIdForEdit) throws Exception {
		String status = "added";
		if (clientIdForEdit != 0) {
			clientInfo.setClientId(clientIdForEdit);
			clientInfo.setUpdatedDate(new Date());
			clientInfo.setUpdatedBy(userName);
			status = "updated";
		} else {
			clientInfo.setCreatedDate(new Date());
			clientInfo.setCreatedBy(userName);
		}
		clientInfo.setStatus(1);
		
		return dao.addEditClientData(clientInfo, status);
	}
	
	/*Start from here*/
	@Override
	public Map<String, Object> getClientListData(ClientDao dao, String userName, String enabled,
			int page, int limit, String sidx, String sord) throws Exception {
		StringBuffer query = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		
		buildQueryForClientList(enabled, query, paraList, dao);
		
		BigInteger c = dao.countClientListData(userName, query, paraList);
		
		Double count = c.doubleValue();
		double total_pages=0;
		
		if(count>0)
			total_pages=(int) Math.ceil(count/limit);
		else
			total_pages=0;
		
		if(page>total_pages)
			page=(int)total_pages;
		
		int start=(limit*page)-limit;
		
		List<ClientInfo> list = dao.getClientListData(userName, start, limit, query, paraList, false, sidx, sord);
		
		Map<String, Object> clientListMap = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> clientList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> clientListSubMap = null;
		List<String> cell = null;
		
		//This is done to set SR No in the grid
		if (start<0) {
			start += 11;
		} else {
			start += 1;
		}
		
		int i = 1;
		for (ClientInfo header : list) {
			clientListSubMap = new LinkedHashMap<String, Object>();
			clientListSubMap.put("id",header.getClientId());
			cell = new ArrayList<String>();
			
			cell.add(String.valueOf(start));							//Sr.No
			cell.add(String.valueOf(header.getClientId()));				//clientId
			cell.add(String.valueOf(header.getClientName()));			//clientName
			cell.add(String.valueOf(header.getGroupNames()));			//groupNames
			cell.add(String.valueOf(header.getFileNo()));				//fileNo
			cell.add(String.valueOf(header.getIncorporationDate()));	//incorporationDate
			cell.add(String.valueOf(header.getYearEnd()));				//yearEnd
			cell.add(String.valueOf(header.getContactName()));			//contactName
			if (isNullOrEmpty(header.getPartnerName()))
				cell.add("");											//partnerName
			else
				cell.add(String.valueOf(header.getPartnerName()));		//partnerName
			cell.add(String.valueOf(header.getStatus()));				//status
			cell.add(String.valueOf(header.getTelephoneNo()));			//telephoneNo
			cell.add(String.valueOf(header.getEmailId()));				//emailId
			cell.add(String.valueOf(header.getAddressLine1()));			//addressLine1
			cell.add(String.valueOf(header.getAddressLine2()));			//addressLine2
			cell.add(String.valueOf(header.getCity()));					//city
			cell.add(String.valueOf(header.getCountryCode()));			//country
			cell.add(String.valueOf(header.getPostCode()));				//postCode
			
			clientListSubMap.put("cell", cell);
			clientList.add(clientListSubMap);
			i++;
			start++;
		}
		clientListMap.put("rows", clientList);
		clientListMap.put("page", page);
		clientListMap.put("total", total_pages);
		clientListMap.put("records", count);
		
		return clientListMap;
	}
	
	public void buildQueryForClientList(String enabled, StringBuffer query, List<String> paraList, ClientDao dao) throws Exception {
		query.append(" WHERE status = ? ");
		paraList.add(enabled);
	}
	/*Till here*/

	@Override
	public String enableDisableClientStatus(ClientDao dao,
			int selectedClientId, int changeStatus, String userName) throws Exception {
		return dao.enableDisableClientStatus(selectedClientId, changeStatus, userName);
	}

	@Override
	public List<Object[]> getClientNamesList(ClientDao dao, String clientStatus)
			throws Exception
	{
		return dao.getClientNamesList(clientStatus);
	}

	@Override
	public ClientInfoForm getClientinformation(int clientId) throws Exception
	{
		return clientDao.getClientinformation(clientId);
	}
}