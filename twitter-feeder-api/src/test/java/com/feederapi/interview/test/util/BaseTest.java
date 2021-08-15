package com.feederapi.interview.test.util;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;

import com.feederapi.interview.utility.RestAssuredUtil;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
 * Base class which will do set up before executing every test class
 * 
 * @author PUNIT GARG
 *
 */
public class BaseTest {
	
	static Logger log = Logger.getLogger(BaseTest.class.getName());

	// Instantiate a Helper Test Methods (testUtils) Object
	protected RequestSpecification request;

	@BeforeClass
	protected void setup() throws IOException, JSONException, URISyntaxException {
		// Test Setup
		RestAssuredUtil.setBaseURI(); // Setup Base URI
		RestAssuredUtil.setBasePath(); // Setup Base Path
		RestAssuredUtil.setContentType(ContentType.JSON); // Setup Content Type
		request = RestAssuredUtil.getRequestSpec();
		log.info("Request Base Path: " + SpecificationQuerier.query(request).getURI());
		ValidateResponse.validateResponseStatusCode(RestAssuredUtil.getResponse(request));
	}
}
