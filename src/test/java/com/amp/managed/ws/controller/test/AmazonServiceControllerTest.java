/**
 * 
 */
package com.amp.managed.ws.controller.test;

import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**	
 * @author MVEKSLER
 *
 */
public class AmazonServiceControllerTest 
{
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(AmazonServiceControllerTest.class);
	
	private static URI getBaseURI() throws URISyntaxException 
	{
		try 
		{
			//URI uri = new URI("http://localhost:8090/amp-ws-api/AmazonService");
			URI uri = new URI("http://localhost:21090/amp-ws-api/AmazonService");
			
			return uri;
		} 
		catch (URISyntaxException e)
		{
			LOGGER.error("::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
			
			throw(e);
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
		}
		catch( Exception e)
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		
	}
	
	@Ignore
	@Test
	public void testSayHello() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        ClientHttpRequestFactory requestFactory = 
					new HttpComponentsClientHttpRequestFactory();
	        
	        RestTemplate restTemplate = new RestTemplate(requestFactory);
		    
		    final String fullUrl = getBaseURI().toString() + "/sayHello";
		    
		    URI uri = new URI(fullUrl);
		    
		    LOGGER.info(cMethodName + "::" + uri.toString());
		    
		    HttpHeaders headers = new HttpHeaders();   
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		    
		    HttpEntity<?> requestEntity = new HttpEntity<Object>(headers);
		    
		    ResponseEntity<String> response = restTemplate.exchange(
		    		fullUrl, HttpMethod.GET, requestEntity, String.class);
		    
		    Assert.assertEquals(200, response.getStatusCodeValue());
		}
		catch( Exception e )
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	
	//---
	@Ignore
	@Test
	public void itemSearch1() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
			
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    params.add("MerchantId", "All");
    		params.add("SearchIndex", "Kitchen");
    		params.add("ResponseGroup", "ItemAttributes");
    		params.add("Brand", "Cuisinart");
    		
    		HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
			
		    ClientHttpRequestFactory requestFactory = 
					new HttpComponentsClientHttpRequestFactory();
	        
	        RestTemplate restTemplate = new RestTemplate(requestFactory);
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	        		   
		    final String endpointURL = getBaseURI().toString() + "/itemSearch";
		    
		    LOGGER.info(cMethodName + "::formattedURL: " + endpointURL);
	        
		    HttpEntity<?> requestEntity = new HttpEntity<>(params, headers);
		   
		    ResponseEntity<String> response = restTemplate.exchange(
		    		endpointURL, HttpMethod.POST, requestEntity, String.class);
	        
    		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

			String responseBody = response.getBody();
    		
			LOGGER.info(cMethodName + "::" + responseBody);
			
			if (response.getStatusCode() != HttpStatus.OK) 
			{
			   throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
			}

			String cResXml = response.getBody();
    		
			LOGGER.info("Response: " + cResXml);
	        
		}
		catch( Exception e )
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	//---
	
	@Test  
	@Ignore
	public void itemSearch2() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	      
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("MerchantId", "All");
    		params.add("SearchIndex", "Grocery");
    		params.add("ResponseGroup", "Large");
    		params.add("BrowseNode", "6967216011");
    		
    		HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
			
		    ClientHttpRequestFactory requestFactory = 
					new HttpComponentsClientHttpRequestFactory();
	        
	        RestTemplate restTemplate = new RestTemplate(requestFactory);
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	        		   
		    final String endpointURL = getBaseURI().toString() + "/itemSearch";
		    
		    LOGGER.info(cMethodName + "::formattedURL: " + endpointURL);
	        
		    HttpEntity<?> requestEntity = new HttpEntity<>(params, headers);
		   
		    ResponseEntity<String> response = restTemplate.exchange(
		    		endpointURL, HttpMethod.POST, requestEntity, String.class);
	        
    		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

			String responseBody = response.getBody();
    		
			LOGGER.info(cMethodName + "::" + responseBody);
			
			if (response.getStatusCode() != HttpStatus.OK) 
			{
			   throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
			}

			String cResXml = response.getBody();
    		
			LOGGER.info("Response: " + cResXml);
		}
		catch( Exception e)
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	//---
	
	@Test  
	@Ignore
	public void itemLookup() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("ItemId", "B0014WYXYW");
	        params.add("ResponseGroup", "Large");
	 
	        HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
			
		    ClientHttpRequestFactory requestFactory = 
					new HttpComponentsClientHttpRequestFactory();
	        
	        RestTemplate restTemplate = new RestTemplate(requestFactory);
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	        		   
		    final String endpointURL = getBaseURI().toString() + "/itemLookup";
		    
		    LOGGER.info(cMethodName + "::formattedURL: " + endpointURL);
	        
		    HttpEntity<?> requestEntity = new HttpEntity<>(params, headers);
		   
		    ResponseEntity<String> response = restTemplate.exchange(
		    		endpointURL, HttpMethod.POST, requestEntity, String.class);
	        
    		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

			String responseBody = response.getBody();
    		
			LOGGER.info(cMethodName + "::" + responseBody);
			
			if (response.getStatusCode() != HttpStatus.OK) 
			{
			   throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
			}

			String cResXml = response.getBody();
    		
			LOGGER.info("Response: " + cResXml);
		}
		catch( Exception e)
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	
	//---
	
	@Ignore
	@Test
	public void browseNodeLookup() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("BrowseNodeId", "6386372011");
	        params.add("ResponseGroup", "BrowseNodeInfo");
    		
    		HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
			
		    ClientHttpRequestFactory requestFactory = 
					new HttpComponentsClientHttpRequestFactory();
	        
	        RestTemplate restTemplate = new RestTemplate(requestFactory);
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	        		   
		    final String endpointURL = getBaseURI().toString() + "/browseNodeLookup";
		    
		    LOGGER.info(cMethodName + "::formattedURL: " + endpointURL);
	        
		    HttpEntity<?> requestEntity = new HttpEntity<>(params, headers);
		   
		    ResponseEntity<String> response = restTemplate.exchange(
		    		endpointURL, HttpMethod.POST, requestEntity, String.class);
	        
    		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

			String responseBody = response.getBody();
    		
			LOGGER.info(cMethodName + "::" + responseBody);
			
			if (response.getStatusCode() != HttpStatus.OK) 
			{
			   throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCode());
			}

			String cResXml = response.getBody();
    		
			LOGGER.info("Response: " + cResXml);
		}
		catch( Exception e)
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	
	//---
}
