package org.autodoc.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.autodoc.dao.LetterDao;
import org.autodoc.form.ListItem;
import org.autodoc.form.QuestionFlowSetting;
import org.autodoc.service.LetterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
@Controller
public class PageShowController {
	Logger logg = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/404")
	public String page404() {
		try {
			logg.debug("page404 execution starts");
			return "404";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/errorPage")
	public String errorPage() {
		try {
			logg.debug("errorPage execution starts");
			return "errorPage";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/loginpage")
	public String login() {
		try {
			logg.debug("Login execution starts");
			return "login";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/loginFailed")
	public String loginFailed() {
		try {
			logg.debug("loginFailed execution starts");
			return "login";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	@RequestMapping(value = "/welcomePage")
	public String welcomePage(HttpServletRequest req, Principal principal, Model model) {
		try {
			logg.debug("welcomePage execution starts");
			
			HttpSession session = req.getSession(false);
			String userName = principal.getName();
			session.setAttribute("userName", userName);
			
			if(req.isUserInRole("ADMIN")) {
				return "clients";
			} else if(req.isUserInRole("SUPER")) {
				return "errorPage";
			} else {
				return "users";
			} 
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/clients")
	public String clients() {
		try {
			logg.debug("clients execution starts");
			return "clients";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/letters")
	public String letters() {
		try {
			logg.debug("letters execution starts");
			return "letters";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/taxability")
	public String taxability() {
		try {
			logg.debug("taxability execution starts");
			return "taxability";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/letterFlow")
	public String letterFlow(Map<String, Object> map, Model model, HttpServletRequest req) {
		try {
			logg.debug("letterFlow execution starts");
			
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
			LetterService service = (LetterService) context.getBean("LetterServiceImpl");
			LetterDao dao = (LetterDao) context.getBean("LetterDaoImpl");
			
			List<ListItem> letterNamesList = service.getLetterNameList(dao);
			model.addAttribute("letterNamesList", letterNamesList);
			
			QuestionFlowSetting questionFlowSetting = new QuestionFlowSetting();
			questionFlowSetting.setLetterId(String.valueOf(0));
			questionFlowSetting.setQuestionFlowSettingList(null);
			model.addAttribute("questionFlowSet", questionFlowSetting);
			
			return "letterFlow";
			
			// dndTREE related
			/*String dataInString = "{'id':'','name':''}";
			String isDataThere = "NoData";
			map.put("dataForSeelectedLetter", isDataThere);
			map.put("dataInString", dataInString);*/
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
	
	@RequestMapping(value = "/genLetter")
	public String genLetter() {
		try {
			logg.debug("genLetter execution starts");
			return "genLetter";
		} catch (Exception e) {
			logg.error("Error:",e);
			return "errorPage";
		}
	}
}