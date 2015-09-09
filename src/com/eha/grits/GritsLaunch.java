package com.eha.grits;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
	Start of project for GATE plugin. 
	This just posts something to a URL with logging
**/
public class GritsLaunch {

	private static final Logger logger = LogManager.getLogger(GritsLaunch.class);

	public static void main(String[] args) throws IOException{

		logger.info("Entering application.");

		if (args.length != 2 ) {
	    	 logger.error("Parameters are: <fully qualified URL>, <payload>");
	    	 System.exit(0);
	    }
	    
		String qualifiedURL = args[0];
   		String payload 		= args[1];
   	
   		String[] schemes = {"http","https"};
   		
	    UrlValidator urlValidator = new UrlValidator(schemes);
	    if (!urlValidator.isValid(qualifiedURL) ) {
	    	 logger.error("Invalid URL specified, existing");
	    	 System.exit(0);
	    }
	    
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(qualifiedURL);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("body", payload));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			System.out.println(response.getStatusLine());    
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        long len = entity.getContentLength();
	            logger.info("Response " + "[" + len + "] bytes:");		            
	            logger.info(EntityUtils.toString(entity));
		    }		    
		} finally {
			response.close();
		}

		logger.info("Exiting application.");
	}

}
