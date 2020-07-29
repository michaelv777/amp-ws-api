 
package com.amp.managed.ws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amp.amazon.webservices.rest.client.AmazonClientWorker;
import com.amp.commerce.service.api.CommerceServiceClientBase;
import com.amp.commerce.service.api.CommerceServiceClientFactory;

@RestController
@RequestMapping({"/AmazonService"})
public class AmazonService 
{
	private static final Logger LOG = 
			LoggerFactory.getLogger(AmazonService.class);
	
	//---class variables----------------------
	protected CommerceServiceClientBase cAmazonClientWorker = null;
	
	//---getters/setters----------------------
	public CommerceServiceClientBase getcAmazonClientWorker() {
		return cAmazonClientWorker;
	}


	public void setcAmazonClientWorker(CommerceServiceClientBase cAmazonClientWorker) {
		this.cAmazonClientWorker = cAmazonClientWorker;
	}
	
	//---class methods------------------------
    public AmazonService() {
       
    }
    
    @GetMapping(value =  "/sayHello", 
    		consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
    	    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<String> sayHello()
	{
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<String> responseObject = 
        		new ResponseEntity<String>(
        				String.valueOf("Hello"), headers, HttpStatus.OK);
        
        return responseObject;
	}

	@PostMapping(value =  "/itemSearch", 
			consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
		    produces = {MediaType.APPLICATION_XML_VALUE})
	public String itemSearch(
			@RequestBody MultiValueMap<String, String> mvParams,
  			HttpServletRequest request, 
  			HttpServletResponse response)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	HashMap<String, String> params = new HashMap<String, String>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == mvParams )
	        {
	        	cRes = false;
	        	
	        	LOG.error(cMethodName + "::Error:params is null. Check MultivaluedMap<String, String> params parameter!");
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	if ( null == this.cAmazonClientWorker )
	        	{
	        		this.cAmazonClientWorker = 
	        				CommerceServiceClientFactory.buildCommerceServiceClient(AmazonClientWorker.class);
	        	
	        		if ( null == this.cAmazonClientWorker )
		    		{
	        			LOG.error(cMethodName + "::this.cAmazonClientWorker id null for AmazonClientWorker.class!!");
		    			
		    			cRes = false;
		    		}
	        	}
	        }

	        //--------
	        if ( cRes )
	        {
	        	for( Map.Entry<String, List<String>> mvParam : mvParams.entrySet())
	        	{
	        		String mvValue = "";
	        		String mvKey = mvParam.getKey();
	        		
	        		List<String> mvValues = mvParam.getValue();
	        		
	        		if (mvValues != null && mvValues.size() >= 1)
	        		{
	        			mvValue = mvValues.get(0);
	        			
	        			params.put(mvKey, mvValue);
	        		}
	        	}
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cResXml = this.cAmazonClientWorker.itemSearchXml(params);
	        }
	        
	        //--------
	        LOG.info("------------------");
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage(), e);
    		
    		return ( cResXml = cMethodName + "::Error:" );
    	}
    }
		
	//---
	/**
     * Retrieves representation of an instance of AmazonService
     * @return an instance of String
     */
	@PostMapping(value =  "/itemLookup", 
			consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
		    produces = {MediaType.APPLICATION_XML_VALUE})
	public String itemLookup(MultiValueMap<String, String> mvParams)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	HashMap<String, String> params = new HashMap<String, String>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == mvParams )
	        {
	        	cRes = false;
	        	
	        	LOG.error(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	if ( null == this.cAmazonClientWorker )
	        	{
	        		this.cAmazonClientWorker = 
	        				CommerceServiceClientFactory.buildCommerceServiceClient(AmazonClientWorker.class);
	        	
	        		if ( null == this.cAmazonClientWorker )
		    		{
	        			LOG.error(cMethodName + "::this.cAmazonClientWorker id null for AmazonClientWorker.class!!");
		    			
		    			cRes = false;
		    		}
	        	}
	        }

	        //--------
	        if ( cRes )
	        {
	        	for( Map.Entry<String, List<String>> mvParam : mvParams.entrySet())
	        	{
	        		String mvValue = "";
	        		String mvKey = mvParam.getKey();
	        		
	        		List<String> mvValues = mvParam.getValue();
	        		
	        		if (mvValues != null && mvValues.size() >= 1)
	        		{
	        			mvValue = mvValues.get(0);
	        			
	        			params.put(mvKey, mvValue);
	        		}
	        	}
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cResXml = this.cAmazonClientWorker.itemLookupXml(params);
	        }
	        
	        //--------
	        LOG.info("------------------");
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage(), e);
    		
    		return ( cResXml = cMethodName + "::Error:" );
    	}
    }
	
	//--------------------------------------------------------------------------
	/**
     * Retrieves representation of an instance of AmazonService
     * @return an instance of String
     */
	@PostMapping(value =  "/browseNodeLookup", 
			consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
		    produces = {MediaType.APPLICATION_XML_VALUE})
	public String browseNodeLookup(MultiValueMap<String, String> mvParams)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	String cResXml = "";
    	
    	HashMap<String, String> params = new HashMap<String, String>();
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == mvParams )
	        {
	        	cRes = false;
	        	
	        	LOG.error(cMethodName + "::Error:params is null. Check HashMap<String, String> params parameter!");
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	if ( null == this.cAmazonClientWorker )
	        	{
	        		this.cAmazonClientWorker = 
	        				CommerceServiceClientFactory.buildCommerceServiceClient(AmazonClientWorker.class);
	        	
	        		if ( null == this.cAmazonClientWorker )
		    		{
	        			LOG.error(cMethodName + "::this.cAmazonClientWorker id null for AmazonClientWorker.class!!");
		    			
		    			cRes = false;
		    		}
	        	}
	        }

	        //--------
	        if ( cRes )
	        {
	        	for( Map.Entry<String, List<String>> mvParam : mvParams.entrySet())
	        	{
	        		String mvValue = "";
	        		String mvKey = mvParam.getKey();
	        		
	        		List<String> mvValues = mvParam.getValue();
	        		
	        		if (mvValues != null && mvValues.size() >= 1)
	        		{
	        			mvValue = mvValues.get(0);
	        			
	        			params.put(mvKey, mvValue);
	        		}
	        	}
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cResXml = this.cAmazonClientWorker.browseNodeLookupXml(params);
	        }
	        
	        //--------
	        LOG.info("------------------");
	        
	        return cResXml;
    	}
    	catch( Exception e)
    	{
    		LOG.error(cMethodName + "::Exception:" + e.getMessage(), e);
    		
    		return ( cResXml = cMethodName + "::Error:" );
    	}
    }
}