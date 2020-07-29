/**
 * 
 */
package com.amp.managed.ws.controller.test;

import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amp.amazon.webservices.rest.client.SignedRequestsHelper;

/**
 * @author MVEKSLER
 *
 */
public class AmazonServiceAPITest 
{
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(AmazonServiceAPITest.class);
	
	/*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAIQU2FHPYCZDYXCIA";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "k9TUoikDHilxh11Ukrxl9PN1h4KxgQIpQ2HozC7w";

    /*
     * Use the end-point according to the region you are interested in.
     */
    private static final String ENDPOINT = "webservices.amazon.ca";
	
	@SuppressWarnings("unused")
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
	
	public void testAmazonWebservicesAPI() 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();

	        SignedRequestsHelper helper;

	        try {
	            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return;
	        }

	        String requestUrl = null;

	        Map<String, String> params = new HashMap<String, String>();

	        params.put("Service", "AWSECommerceService");
	        params.put("Operation", "ItemSearch");
	        params.put("AWSAccessKeyId", "AKIAIQU2FHPYCZDYXCIA");
	        params.put("AssociateTag", "michaelv777-20");
	        params.put("SearchIndex", "All");
	        params.put("Keywords", "Lighting Equipment ");
	        params.put("ResponseGroup", "Images,ItemAttributes,Offers");

	        requestUrl = helper.sign(params);

	        LOGGER.info("Signed URL: \"" + requestUrl + "\"");
	        
		}
		catch( Exception e )
		{
			LOGGER.error(cMethodName + "::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			//new AmazonServiceAPITest().testAmazonWebservicesAPI();
		}
		catch( Exception e )
		{
			LOGGER.error("::Exception:" + e.getMessage(), e);
			
			fail(e.getMessage());
		}
	}
}
