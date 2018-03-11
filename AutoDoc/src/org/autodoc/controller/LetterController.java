package org.autodoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.velocity.tools.generic.DateTool;
import org.autodoc.dao.LetterDao;
import org.autodoc.entity.LettersInformation;
import org.autodoc.entity.PopulatedLettersInfo;
import org.autodoc.form.ClientInfoForm;
import org.autodoc.form.GeneratedLetterForm;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.autodoc.handler.JsonMapper;
import org.autodoc.service.ClientService;
import org.autodoc.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.opensagres.xdocreport.core.document.SyntaxKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

@Controller
public class LetterController extends CommonController
{
	@Autowired
	ClientService clientService;
	
	public static final String USERNAME = "USERNAME";
	
	Logger logg = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/addEditLetters", method = RequestMethod.POST)
	public void addEditLetterInformation(HttpServletRequest req, HttpServletResponse res, LettersInformation lettersInformation)
	{
		try
		{
			logg.debug("addEditLetterInformation execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			int letterIdForEdit = 0;
			if (!isNullOrEmpty(req.getParameter("letterIdForEdit")))
			{
				letterIdForEdit = Integer.parseInt(req.getParameter("letterIdForEdit"));
				logg.debug("Editing Letter Information->LetterId=>" + letterIdForEdit);
			}

			String message = service.addEditLetterInformation(dao, lettersInformation, userName, letterIdForEdit);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, message);

			logg.debug("addEditLetterInformation execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in addEditLetterInformation : ", e);
		}
	}

	@RequestMapping(value = "/getLettersList", method = RequestMethod.POST)
	public void getLetterListInformation(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getLetterListInformation execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");

			String letterStatus = req.getParameter("letterStatus");
			int page = Integer.valueOf(req.getParameter("page"));
			int limit = Integer.valueOf(req.getParameter("rows"));
			String sidx = req.getParameter("sidx");
			String sord = req.getParameter("sord");

			Map<String, Object> map = service.getLetterListInformation(dao, userName, letterStatus,
																			page, limit, sidx, sord);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, map);

			logg.debug("getLetterListInformation execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getLetterListInformation : ", e);
		}
	}

	@RequestMapping(value = "/deleteLetterData", method = RequestMethod.POST)
	public void deleteLetter(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("deleteLetter execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			int selectedLetterId = Integer.parseInt(req.getParameter("selectedLetterId"));
			
			String status = service.deleteLetter(dao, selectedLetterId);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, status);

			logg.debug("deleteLetter execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in deleteLetter : ", e);
		}
	}

	// From Here TAXABILITY related coding
	@RequestMapping(value = "/getLetterNames", method = RequestMethod.POST)
	public void getLetterNamesList(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getLetterNamesList execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			String typeOfData = req.getParameter("typeOfData");
			List<Object[]> list = service.getLetterNamesList(dao, typeOfData);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, list);

			logg.debug("getLetterNamesList execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getLetterNamesList : ", e);
		}
	}

	@RequestMapping(value = "/addEditQuestionAnswerTemplate", method = RequestMethod.POST)
	public void addEditQATemplateInformation(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("addEditQATemplateInformation execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			HttpSession session = req.getSession();
			String message = "OK";
			String userName = (String) session.getAttribute("userName");

			int letterId = Integer.parseInt(req.getParameter("letterId").trim());
			String questionNo = req.getParameter("questionNo").trim();
			
			int idForEdit = 0;
			if (!isNullOrEmpty(req.getParameter("idForEdit").trim()))
			{
				idForEdit = Integer.parseInt(req.getParameter("idForEdit").trim());
				logg.debug("Editing QATemplates->idForEdit=>" + idForEdit);
			}
			
			if (idForEdit == 0)
				message = service.checkQuestionNoExists(dao, questionNo, letterId);
			
			if (message.equalsIgnoreCase("OK"))
			{
				String question = req.getParameter("question").trim();
				String questionType = req.getParameter("questionType").trim();
				String defaultValue = req.getParameter("defaultValue").trim();
				
				String yesTrueValue = "";
				if (!isNullOrEmpty(req.getParameter("yesTrueValue").trim()))
					yesTrueValue = req.getParameter("yesTrueValue").trim();
				
				String noFalseValue = "";
				if (!isNullOrEmpty(req.getParameter("noFalseValue").trim()))
					noFalseValue  = req.getParameter("noFalseValue").trim();
				
				message = service.addEditQATemplateInformation(dao, userName, letterId, questionNo, question,
																			questionType, defaultValue, idForEdit, yesTrueValue, noFalseValue);
			}
			
			List<Object> list = new ArrayList<Object>();
				list.add(message);
				list.add(letterId);
				list.add(questionNo);
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, list);

			logg.debug("addEditQATemplateInformation execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in addEditQATemplateInformation : ", e);
		}
	}

	@RequestMapping(value = "/getTaxabilityList", method = RequestMethod.POST)
	public void getTaxabilityListInformation(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getTaxabilityListInformation execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			int page = Integer.valueOf(req.getParameter("page"));
			int limit = Integer.valueOf(req.getParameter("rows"));
			String sidx = req.getParameter("sidx");
			String sord = req.getParameter("sord");

			Map<String, Object> map = service.getTaxabilityListInformation(dao, page, limit, sidx, sord);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, map);

			logg.debug("getTaxabilityListInformation execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getTaxabilityListInformation : ", e);
		}
	}
	
	@RequestMapping(value = "/deleteTaxabilityData", method = RequestMethod.POST)
	public void removeTaxabilityQuestion(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("removeTaxabilityQuestion execution Starts");

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");

			int selectedQuestionId = Integer.parseInt(req.getParameter("selectedQuestionId"));
			
			String status = service.removeTaxabilityQuestion(dao, selectedQuestionId);

			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, status);

			logg.debug("removeTaxabilityQuestion execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in removeTaxabilityQuestion : ", e);
		}
	}
	
	// From Here LETTER FLOW SETTING related coding
	@RequestMapping(value = "/getQuesListForLetteFlow", method = RequestMethod.POST)
	public String getQuesListForLetteFlow(@ModelAttribute("questionFlowSet") QuestionFlowSetting questionFlowSet,
			Model model, @RequestParam("letterId") int letterId,
			HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getQuesListForLetteFlow execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			String saveBtnStatus = req.getParameter("saveBtn");
			if (!isNullOrEmpty(saveBtnStatus) && saveBtnStatus.equalsIgnoreCase("save"))
				service.saveLetteFlow(dao, questionFlowSet);
			
			List<QuestionFlowSetting> questionFlowSetList = null;
			if (letterId != 0)
				questionFlowSetList = service.getQuesListForLetteFlow(dao, letterId);
			
			QuestionFlowSetting questionFlowSetting = new QuestionFlowSetting();
			questionFlowSetting.setLetterId(String.valueOf(letterId));
			if (questionFlowSetList != null)
				questionFlowSetting.setQuestionFlowSettingList(questionFlowSetList);
			model.addAttribute("questionFlowSet", questionFlowSetting);
			
			List<ListItem> letterNamesList = service.getLetterNameList(dao);
			model.addAttribute("letterNamesList", letterNamesList);
			
			model.addAttribute("letterId", letterId);
			
			logg.debug("getQuesListForLetteFlow execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getQuesListForLetteFlow : ", e);
		}
		return "letterFlow";
	}
	// Generate Letter
	@RequestMapping(value = "/getTokenNumber", method = RequestMethod.POST)
	public void getTokenNo(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getTokenNo execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			long tokenNo = service.getTokenNo(dao);
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			mapper.WritecInJson(res, tokenNo);
			
			logg.debug("getTokenNo execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getTokenNo : ", e);
		}
	}

	// Generate Letter
	@RequestMapping(value = "/getGenerateLetterData", method = RequestMethod.POST)
	public void getDataToGenerateLetter(@RequestParam("letterId") int letterId, @RequestParam("questionId") int questionId,
			HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("getDataToGenerateLetter execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			
			String toDoMessage = req.getParameter("toDoMessage");
			String savedMessage = "";
			if (!isNullOrEmpty(toDoMessage) && (toDoMessage.equalsIgnoreCase("next") || toDoMessage.equalsIgnoreCase("save")))
			{
				String answer = req.getParameter("answer");
				int clientId = Integer.parseInt(req.getParameter("clientId"));
				int currentQuestionId = Integer.parseInt(req.getParameter("currentQuestionId"));
				HttpSession session = req.getSession();
				String userName = (String) session.getAttribute("userName");
				int tokenNo = Integer.parseInt(req.getParameter("tokenNumber"));
				
				savedMessage = service.saveGeneratedLetterInformation(dao, clientId, letterId, currentQuestionId, answer, userName, tokenNo);
				
				if(toDoMessage.equalsIgnoreCase("save"))
				{
					String returnMsg = generateWordLetter(clientId, tokenNo, service, dao, letterId, res, req);
					mapper.WritecInJson(res, returnMsg);
				}
			}
			
			List<String> questionFlowSetting = service.getDataToGenerateLetter(dao, letterId, questionId);
			if (!toDoMessage.equalsIgnoreCase("save"))
				mapper.WritecInJson(res, questionFlowSetting);
			
			logg.debug("getDataToGenerateLetter execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getDataToGenerateLetter : ", e);
		}
	}
	
	// Generate Letter
	private String generateWordLetter(int clientId, int tokenNo, LetterService service, LetterDao dao, int letterId, HttpServletResponse res, HttpServletRequest req)
	{
		try
		{
			logg.debug("generateWordLetter execution Starts");
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("userName");
			
			Object[] letterInfo = service.getLetterAndOrgNameUsingLetterId(letterId, dao);
			String letterPath = (String) letterInfo[0];
			String letterName = (String) letterInfo[1];
			String orgName = (String) letterInfo[2];
			String orgRefNo = (String) letterInfo[3];
			
			ClientInfoForm clientInfo = clientService.getClientinformation(clientId);
			
			// 1) Load Docx file by filling Velocity template engine and cache it to the registry
			File inputFile = new File(letterPath + "Template/" + letterName + ".docx");
			if(!inputFile.exists())
			{
				System.out.println("inputFile=>" + inputFile);
				System.out.println("File not exists.!");
				logg.debug("generateWordLetter execution Ends. In if");
				
				
				/* This code is used to save the information about the generated letter for future use. */
				String date1 = new SimpleDateFormat("dd-MMMM-yyyy").format(new Date());
				String filePath = letterPath + "Result/";
		        String fileName = tokenNo + "_" + letterName + "_" + clientInfo.getClientName() + "_" + date1 + ".docx";
		        
				saveLetterInfoIntoPopulatedLetterInfo(service, dao, tokenNo, filePath, fileName, userName);
		        /* End */
				
				return "Information saved. But File not exists.! Please generate letter from Generate Letter menu using your Token id.";
			}
			else
			{
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
		        String currDate = sdf.format(new Date());
		        
				FileInputStream fIn = new FileInputStream(inputFile);
		        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(fIn, TemplateEngineKind.Velocity);
		        
		        // 2) Create fields metadata to manage lazy loop (#forech velocity) for table row.
		        FieldsMetadata metadata = new FieldsMetadata();
		        /*metadata.addFieldAsTextStyling("clientInfo", SyntaxKind.Html);
		        metadata.addFieldAsTextStyling("ourRef", SyntaxKind.Html);
		        metadata.addFieldAsTextStyling("currentDate", SyntaxKind.Html);
		        metadata.addFieldAsTextStyling("clientNameAndAddress", SyntaxKind.Html);*/
		        metadata.addFieldAsTextStyling("groupNames", SyntaxKind.Html);
		        metadata.addFieldAsTextStyling("groupNameToAudit", SyntaxKind.Html);
		        
		        report.setFieldsMetadata(metadata);
		        
		        // 3) Create context Java model
		        IContext info = report.createContext();
		        
		        long differenceBetweenDates = daysBetween(clientInfo.getIncorporationDate(), new Date());
		        if (differenceBetweenDates > 365)
		        	info.put("companyIncorporatedOverYearAgo", "YES");
		        else
		        	info.put("companyIncorporatedOverYearAgo", "NO");	
		        
		        String tempDate = getDateString(clientInfo.getIncorporationDate(), "dd/MM/yyyy");
				clientInfo.setIncorporationDate(getDateFromString(tempDate, "dd/MM/yyyy"));
				
				info.put("engagementLetterDate", new Date());
				info.put("orgName", orgName);
				info.put("orgRefNo", orgRefNo);
				info.put("currDate", currDate);
				//info.put("ourRef", String.valueOf(ctlientInfo.getFileNo()));
				info.put("dateTool", new DateTool());
		        info.put("clientInfo", clientInfo);
		        
		        String[] groupNamesArray = clientInfo.getGroupNames().split(";");
		        String groupNames = "<b>";
		        for (int i = 0; i < groupNamesArray.length; i++)
				{
		        	String temp = groupNamesArray[i];
		        	groupNames += temp.toUpperCase() + "<br/>";
				}
		        groupNames += "</b>";
		        
		        String groupNameToAudit = "";
		        for (int i = 0; i < groupNamesArray.length; i++)
				{
		        	if (i != (groupNamesArray.length -2))
		        		groupNameToAudit += groupNamesArray[i] + ", ";
		        	else
		        		groupNameToAudit += groupNamesArray[i] + " and ";
				}
		        groupNameToAudit += "";
		        
		        info.put("groupNames", groupNames);
		        info.put("groupNameToAudit", groupNameToAudit);
		        
		        List<GeneratedLetterForm> generatedLtrs = service.getGeneratedLtrData(tokenNo, dao);
				
				for (int i = 0; i < generatedLtrs.size(); i++)
				{
					if (generatedLtrs.get(i).getAnswer().equalsIgnoreCase("YES")
							|| generatedLtrs.get(i).getAnswer().equalsIgnoreCase("NO")
							|| generatedLtrs.get(i).getAnswer().equalsIgnoreCase("TRUE")
							|| generatedLtrs.get(i).getAnswer().equalsIgnoreCase("FALSE"))
					{
						info.put(generatedLtrs.get(i).getWordField(), generatedLtrs.get(i).getAnswer().toUpperCase());
					}
					else if(generatedLtrs.get(i).getQuestionType().equalsIgnoreCase("date"))
					{
						Date tempAnswerDate = getDateFromString(generatedLtrs.get(i).getAnswer(), "dd/MM/yyyy");
						info.put(generatedLtrs.get(i).getWordField(), tempAnswerDate);
						
						if (generatedLtrs.get(i).getWordField().equalsIgnoreCase("firstPayrollPeriodEndDate"))
						{
							Date date1970 = getDateFromString("01/01/1970", "dd/MM/yyyy");
							if (date1970.compareTo(tempAnswerDate) == 0)
								info.put("isDate1970", "YES");
							else
								info.put("isDate1970", "NO");
						}
					}
					else
					{
						info.put(generatedLtrs.get(i).getWordField(), generatedLtrs.get(i).getAnswer());
					}
				}
		        
		        // 4) Generate report by merging Java model with the Docx
		        File outputFile = new File(letterPath + "Result/");
		        if (!outputFile.isDirectory())
		        	outputFile.mkdir();
		        String date1 = new SimpleDateFormat("dd-MMMM-yyyy").format(new Date());
		        String filePath = letterPath + "Result/";
		        String fileName = tokenNo + "_" + letterName + "_" + clientInfo.getClientName() + "_" + date1 + ".docx";
		        OutputStream out = new FileOutputStream(new File(filePath + fileName));
		        report.process( info, out );
		        
		        /* This code is used to save the information about the generated letter for future use. */
				saveLetterInfoIntoPopulatedLetterInfo(service, dao, tokenNo, filePath, fileName, userName);
		        /* End */
		        
		        System.out.println("Letter Generated successfully.!");
		        logg.debug("generateWordLetter execution Ends. In else");
		        return "Letter Generated successfully.!downloadWordFile.do?filePath="+filePath+"&fileName="+fileName;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in generateWordLetter : ", e);
			return "Something went wrong.!";
		}
		
	}
	
	private static long daysBetween(Date incorporationDate, Date currDate)
	{
        long difference =  (incorporationDate.getTime()-currDate.getTime())/86400000;
        return Math.abs(difference);
    }

	private void saveLetterInfoIntoPopulatedLetterInfo(LetterService service, LetterDao dao, int tokenNo, String filePath, String fileName, String userName) throws Exception
	{
		PopulatedLettersInfo populatedLettersInfo = new PopulatedLettersInfo();
        populatedLettersInfo.setTokenNo(tokenNo);
        populatedLettersInfo.setFilePath(filePath);
        populatedLettersInfo.setFileName(fileName);
        populatedLettersInfo.setCreatedDate(new Date());
        populatedLettersInfo.setCreatedBy(userName);
        
        service.savePopulatedLettersInfo(populatedLettersInfo, dao);
	}
	
	@RequestMapping(value = "/downloadWordFile", method = RequestMethod.GET)
	public @ResponseBody void downloadWordFile(HttpServletRequest req, HttpServletResponse res)
	{
		String szFilePath = req.getParameter("filePath");
		String szFileName = req.getParameter("fileName");
		
		res.setContentType("application/vnd.ms-word");
		res.setHeader("Content-disposition", "attachment; fileName=\"" + szFileName + "\"" );
		try
		{
			byte[] fileInByteArray = getBytesFromFile(new File(szFilePath + szFileName));
			OutputStream out = res.getOutputStream();
			out.write(fileInByteArray);
			out.flush();
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException
	{
		InputStream objInputStream = new FileInputStream(file);
		long length = file.length();
		byte bytes[] = new byte[(int) length];
		int offset = 0;
		for (int numRead = 0; offset < bytes.length
				&& (numRead = objInputStream.read(bytes, offset, bytes.length
						- offset)) >= 0; offset += numRead)
		{
		}
		if (offset < bytes.length)
		{
			throw new IOException((new StringBuilder("Could not completely read file ")).append(file.getName()).toString());
		}
		else
		{
			objInputStream.close();
			return bytes;
		}
	}
	
	// Generate Letter
	@RequestMapping(value = "/generateLetter", method = RequestMethod.POST)
	public @ResponseBody void generateLetter(HttpServletRequest req, HttpServletResponse res)
	{
		try
		{
			logg.debug("generateLetter execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			JsonMapper mapper = (JsonMapper) context.getBean("JsonMapper");
			
			int tokenNo = Integer.parseInt(req.getParameter("tokenNumber"));
			
			PopulatedLettersInfo populatedLettersInfo = service.getPopulatedLettersInfoUsingTokenId(tokenNo, dao);
			if (populatedLettersInfo != null)
			{
				String szFilePath = populatedLettersInfo.getFilePath();
				String szFileName = populatedLettersInfo.getFileName();
				
				File file = new File(szFilePath + szFileName);
				if(!file.exists())
				{
					Object[] letterInfo = service.getLetterClientIdsUsingTokenId(tokenNo, dao);
					int clientId = (Integer) letterInfo[0];
					int letterId = (Integer) letterInfo[1];
					
					String returnMsg = generateWordLetter(clientId, tokenNo, service, dao, letterId, res, req);
					mapper.WritecInJson(res, returnMsg);
				}
				else
				{
					mapper.WritecInJson(res, "downloadWordFile.do?filePath="+szFilePath+"&fileName="+szFileName);
				}
			}
			else
			{
				mapper.WritecInJson(res, "Invalid token number entered. Please enter appropriate token number.!");
			}
			
			//int letterId = (Integer) info[1];
			
			//String returnMsg = generateWordLetter(clientId, tokenNo, service, dao, letterId, res, req);
			//mapper.WritecInJson(res, returnMsg);
			
			logg.debug("generateLetter execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in generateLetter : ", e);
		}
	}
	
	// Generate Letter
	/*private void generateWordLetter()
	{
		JasperReportBuilder report = DynamicReports.report();//a new report
	}*/
	
	// dndTREE related
	/*@RequestMapping(value="/saveLetterFlow",method=RequestMethod.POST)
	public String saveLetterFlow(HttpServletRequest req, Map<String, Object> map)
	{
		String returnMessage = "";
		try
		{
			logg.debug("saveLetterFlow execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			String letterFlowData = req.getParameter("letterFlowData");
			
			//System.out.println(letterFlowData);
			
			String replace = letterFlowData.replace("children\":[{","@%@");
			//System.out.println(replace);
			String replace1 = replace.replace("[]}","@@");
			[]}]},{
			//System.out.println(replace1);
			List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split("@@")));
			//System.out.println(myList.toString());
			
			List<String> listData = new ArrayList<String>();
			listData.add(letterFlowData);
			
			Map<String, HashMap<String, Object>> mapData = new ObjectMapper().readValue(letterFlowData, HashMap.class);
			
			//System.out.println("result=>"+mapData.get("id"));
			
			returnMessage = service.saveLetterFlow(mapData, dao);
			
			Map<String,String> map1 = new HashMap<String,String>();
		    ObjectMapper mapper = new ObjectMapper();

		    map1 = mapper.readValue(letterFlowData, new TypeReference<HashMap>(){});

		    System.out.println(map1);
			
			Gson gson = new Gson();
			LinkedTreeMap result = gson.fromJson(nestedJSON , LinkedTreeMap.class);
			
			JSONObject jObj = new JSONObject(req.getParameter("letterFlowData")); 
			Iterator it = jObj.keys(); //gets all the keys

			while(it.hasNext())
			{
			    String key = (String) it.next(); // get key
			    Object o = jObj.get(key); // get value
			    System.out.println("==1==>"+ key + " : " +  o); // print the key and value
			}
			
			//returnMessage = service.saveLetterFlow(letterFlowData, dao);
			
			logg.debug("saveLetterFlow execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			returnMessage = "Something Went wrong.!";
			logg.error("Error in saveLetterFlow : ",e);
		}
		return returnMessage;
	}*/
	
	/*@RequestMapping(value="/getQuestionList",method=RequestMethod.POST)
	public String getQuestionListData(HttpServletRequest req, Map<String, Object> map)
	{
		try
		{
			logg.debug("getQuestionListData execution Starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			int letterId = Integer.parseInt(req.getParameter("letterId"));
			
			String dataInString = service.getQuestionListData(letterId, dao);
			
			String isDataThere = "Yes";
			if (dataInString.equalsIgnoreCase("noDataForTheSelectedLetter"))
			{
				isDataThere = "NoData";
				dataInString = "{'id':'','name':''}";
			}
			
			map.put("letterId", letterId);
			map.put("dataForSeelectedLetter", isDataThere);
			map.put("dataInString", dataInString);
			logg.debug("getQuestionListData execution Ends");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			logg.error("Error in getQuestionListData : ",e);
		}
		return "letterFlow";
	}*/
}