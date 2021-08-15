package com.feederapi.interview.utility;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {

	static Logger log = Logger.getLogger(RestAssuredUtil.class.getName());

	// Sets Base URI
	public static void setBaseURI() throws IOException, URISyntaxException {
		RestAssured.baseURI = TestUtil.getProperty("baseUri");
	}

	// Sets base path
	public static void setBasePath() throws IOException, URISyntaxException{
		RestAssured.basePath = TestUtil.getProperty("basePath");
	}

	public static RequestSpecification getRequestSpec() {
		return RestAssured.given();
	}

	// Sets ContentType. Same common function to set all headers in post call should
	// be made.
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	// Sets query params in request
	public static RequestSpecification setQueryParamsHashMap(RequestSpecification request,
			Map<Object, Object> dataInputMap) {

		Set<Object> keys = dataInputMap.keySet();

		// Set each query param with value from input data sheet
		for (Object key : keys) {
			request.queryParam(key.toString(), dataInputMap.get(key).toString());
		}
		return request;
	}

	// Returns response
	public static Response getResponse(RequestSpecification reqSpec) {
		return reqSpec.get();
	}

	public static JSONObject getJsonResponseBody(Response response) throws JSONException {
		return new JSONObject(response.body().asString());

	}

	/**
	 * Returns the value of current_page field from get call response
	 * 
	 * @param JSONResponseBody
	 * @return
	 * @throws JSONException
	 */
	public static int getCurrentPage(JSONObject JSONResponseBody) throws JSONException {
		return JSONResponseBody.getJSONObject("posts").getInt("current_page");
	}

	/**
	 * Returns the value of limit_value (count per page) field from get call
	 * response
	 * 
	 * @param JSONResponseBody
	 * @return
	 * @throws JSONException
	 */
	public static int getCountLimitPage(JSONObject JSONResponseBody) throws JSONException {
		return JSONResponseBody.getJSONObject("posts").getInt("limit_value");
	}

	/**
	 * Returns the number of posts on the response page
	 * 
	 * @param JSONResponseBody
	 * @return
	 * @throws JSONException
	 */
	public static int getItemsLength(JSONObject JSONResponseBody) throws JSONException {
		return JSONResponseBody.getJSONObject("posts").getJSONArray("items").length();
	}
	
	public static String getSource(JSONObject JSONResponseBody) throws JSONException {
		return JSONResponseBody.getJSONArray("sources").getJSONObject(0).getString("source");
		
	}
}