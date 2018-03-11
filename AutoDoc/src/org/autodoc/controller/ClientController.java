package org.autodoc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.autodoc.controller.CommonController;
import org.autodoc.dao.ClientDao;
import org.autodoc.entity.ClientInfo;
import org.autodoc.handler.JsonMapper;
import org.autodoc.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class ClientController extends CommonController {
	Logger logg = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/getCountryList",method=RequestMethod.POST)
	public void getCountryListData(HttpServletRequest req, HttpServletResponse res) {
		try {
			logg.debug("getCountryList execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			ClientService service = (ClientService) context.getBean("ClientServiceImpl");
			ClientDao dao = (ClientDao) context.getBean("ClientDaoImpl");
			
			List<Object[]> list = service.getCountryListData(dao);
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, list);
			
			logg.debug("getCountryList execution Ends");
		} catch (Exception e) {
			e.printStackTrace();
			logg.error("Error in getCountryListData : ",e);
		}
	}
	
	@RequestMapping(value = "/addEditClientInformation", method = RequestMethod.POST)
	public void addEditClientData(HttpServletRequest req, HttpServletResponse res, ClientInfo clientInfo) {
		try {
			logg.debug("addEditClientData execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			ClientService service = (ClientService) context.getBean("ClientServiceImpl");
			ClientDao dao = (ClientDao) context.getBean("ClientDaoImpl");
			
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			int clientIdForEdit = 0;
			if (!isNullOrEmpty(req.getParameter("clientIdForEdit"))) {
				clientIdForEdit = Integer.parseInt(req.getParameter("clientIdForEdit"));
				logg.debug("Editing client Information->clientId=>"+clientIdForEdit);
			}
			
			String message = service.addEditClientData(dao, clientInfo, userName, clientIdForEdit);
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, message);
			
			logg.debug("addEditClientData execution Ends");
		} catch (Exception e) {
			e.printStackTrace();
			logg.error("Error in addEditClientData : ", e);
		}
	}
	
	@RequestMapping(value = "/getClientList", method = RequestMethod.POST)
	public void getClientListData(HttpServletRequest req, HttpServletResponse res) {
		try {
			logg.debug("getClientListData execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			ClientService service = (ClientService) context.getBean("ClientServiceImpl");
			ClientDao dao = (ClientDao) context.getBean("ClientDaoImpl");
			
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			String enabled = req.getParameter("enabled");
			int page = Integer.valueOf(req.getParameter("page"));
			int limit = Integer.valueOf(req.getParameter("rows")); 
			String sidx = req.getParameter("sidx"); 
			String sord = req.getParameter("sord");
			
			Map<String,Object> map = service.getClientListData(dao, userName, enabled, page, limit, sidx, sord);
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, map);
			
			logg.debug("getClientListData execution Ends");
		} catch (Exception e) {
			e.printStackTrace();
			logg.error("Error in getClientListData : ", e);
		}
	}
	
	@RequestMapping(value = "/enableDisableClient", method = RequestMethod.POST)
	public void enableDisableClientStatus(HttpServletRequest req, HttpServletResponse res) {
		try {
			logg.debug("enableDisableClientStatus execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			ClientService service = (ClientService) context.getBean("ClientServiceImpl");
			ClientDao dao = (ClientDao) context.getBean("ClientDaoImpl");
			
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			int selectedClientId = Integer.parseInt(req.getParameter("selectedClientId"));
			int changeStatus = Integer.parseInt(req.getParameter("changeStatus"));
			
			String status = service.enableDisableClientStatus(dao, selectedClientId, changeStatus, userName);
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, status);
			
			logg.debug("enableDisableClientData execution Ends");
		} catch (Exception e) {
			e.printStackTrace();
			logg.error("Error in enableDisableClientData : ", e);
		}
	}
	
	// From Here TAXABILITY related coding
	@RequestMapping(value = "/getClientNames", method = RequestMethod.POST)
	public void getClientNamesList(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getClientNamesList execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			ClientService service = (ClientService) context.getBean("ClientServiceImpl");
			ClientDao dao = (ClientDao) context.getBean("ClientDaoImpl");
			
			String clientStatus = req.getParameter("clientStatus");
			List<Object[]> list = service.getClientNamesList(dao, clientStatus);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, list);

			logg.debug("getClientNamesList execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getClientNamesList : ", e);
		}
	}
}