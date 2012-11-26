package com.example;
import java.io.IOException;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class HttpClientTutorial {
	private static String url = "https://172.16.233.122/shibboleth-sp/test.txt";
	
	public static void main(String[] args) {
		// Create a new instance of HttpClient
		HttpClient client = new HttpClient();
		
		// Create a get method
		GetMethod method = new GetMethod(url);
		
		try {
			// Execute the get method we defined above
			int statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed : " + method.getStatusLine());
			}
			
			// read the response body
			byte[] responseBody = method.getResponseBody();
			
			// Let's deal with the response
			System.out.println(new String(responseBody));
			
		} catch (HttpException he) {
			System.err.println("Fatal protocol violation : " + he.getMessage());
			// if debug then print stack
		} catch (IOException ioe) {
			System.err.println("Fatal transport error : " + ioe.getMessage());
			// if debug then print stack
		} finally {
			// release connection
			method.releaseConnection();
		}
	}
}
