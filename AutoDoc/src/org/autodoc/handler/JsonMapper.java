package org.autodoc.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;

public class JsonMapper {
	Logger logg = Logger.getLogger(this.getClass());
	
	public void WritecInJson(HttpServletResponse res, Object object) {
		try {
			logg.debug("JsonMapper's WritecInJson execution Starts");
			
			MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
			MediaType jsonMimeType = MediaType.APPLICATION_JSON;
			
			if(jsonConverter.canWrite(object.getClass(), jsonMimeType)) {
				try {
					jsonConverter.write(object, jsonMimeType, new ServletServerHttpResponse(res));
				}catch(IOException e) {
					e.printStackTrace();
				}catch(HttpMessageNotWritableException e) {
					e.printStackTrace();
				}catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				logg.debug("JsonMapper's WritecInJson : Reached in else");
			}
			
			logg.debug("JsonMapper's WritecInJson execution Ends");
		} catch(Exception e) {
			logg.error("Error:",e);
		}
	}
}