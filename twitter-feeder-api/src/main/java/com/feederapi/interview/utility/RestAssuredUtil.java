package com.feederapi.interview.utility;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {
	
	static Logger log = Logger.getLogger(RestAssuredUtil.class.getName());
	
	// Sets Base URI
	public static void setBaseURI() throws IOException {
		RestAssured.baseURI = TestUtil.getProperty("baseUri");
	}

	// Sets base path
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}
	
	public static RequestSpecification getRequestSpec() {
		return RestAssured.given();
	}

	// Sets ContentType
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}
	
	public static void setQueryParams(String param, int value) {
		given().queryParam(param, value);
	}

	// Returns response by given path
	public static Response getResponse(String path) {
		return given().get(path);
	}

	// Returns response
	public static Response getResponse(RequestSpecification reqSpec) {
		return reqSpec.get();
	}

}