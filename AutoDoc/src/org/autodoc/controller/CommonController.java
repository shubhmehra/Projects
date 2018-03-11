package org.autodoc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class CommonController {
	Logger logg = Logger.getLogger(this.getClass());
	
	protected boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().length() == 0;
	}
	
	public static String getDateString(Date dDate, String szDateFormate)
	{
		String szDate = "";
		String szDateFmt = szDateFormate;
		SimpleDateFormat fmt = new SimpleDateFormat(szDateFmt, Locale.UK);
		szDate = fmt.format(dDate);
		return szDate;
	}
	
	public static Date getDateFromString(String szDate, String szDateFormat)
	{
		SimpleDateFormat fmt = new SimpleDateFormat(szDateFormat, Locale.UK);
		Date dDate = new Date();
		try
		{
			dDate = fmt.parse(szDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return dDate;
	}
}