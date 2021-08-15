package com.feederapi.interview.test.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;

import com.feederapi.interview.utility.RestAssuredUtil;
import com.feederapi.interview.utility.TestUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
	protected int totalPostCount;
	protected int valueMoreThanTotalCount;

	@BeforeClass
	protected void setup() throws IOException, JSONException {
		// Test Setup
		RestAssuredUtil.setBaseURI(); // Setup Base URI
		RestAssuredUtil.setBasePath(TestUtil.getProperty("basePath")); // Setup Base Path
		request = RestAssuredUtil.getRequestSpec();
		log.info("Request Base Path: " + SpecificationQuerier.query(request).getURI());
		RestAssuredUtil.setContentType(ContentType.JSON); // Setup Content Type
		Response res = RestAssuredUtil.getResponse(request);
		
		//Validate the default functionality without any query param
		ValidateResponse.validateResponse(res, 100, 1);
		totalPostCount = ValidateResponse.getPostCount(res);
		valueMoreThanTotalCount = totalPostCount + 50;

	}
}
