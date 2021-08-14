
package com.feederapi.interview.test.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class ValidateResponse {

	public static void validateResponse(Response response, int countPerPage, int pageCount) throws JSONException {
		validateResponseCode(response);		
		
		//Max limit per page is 100. SO, if value passed more than 100 then that should be limited to 100.
		countPerPage = (countPerPage > 100) ? 100 : Math.abs(countPerPage);
		
		//Current Page can't be 0. So, if 0 is passed then set to 1
		pageCount = (pageCount == 0) ? 1 : Math.abs(pageCount);
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		
		//Assert on the current page, count per page and posts length
		Assert.assertEquals(JSONResponseBody.getJSONObject("posts").getInt("current_page"), pageCount, "Expected page:"
				+ pageCount + ", actual page: " + JSONResponseBody.getJSONObject("posts").getInt("current_page"));
		Assert.assertEquals(JSONResponseBody.getJSONObject("posts").getInt("limit_value"), countPerPage, "Expected limit:" 
				+ countPerPage +", actual limit:" + JSONResponseBody.getJSONObject("posts").getInt("limit_value"));
		Assert.assertEquals((JSONResponseBody.getJSONObject("posts").getJSONArray("items").length() <= countPerPage),
				true, JSONResponseBody.getJSONObject("posts").getJSONArray("items").length() + " should be less or equal to:"+ countPerPage);
	}

	public static int getPostCount(Response response) throws JSONException {
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		return JSONResponseBody.getJSONObject("posts").getJSONArray("items").length();
	}

	/**
	 * A common function should be created for validation of any param. However, since limited access was granted, so could only make for these.
	 * This function will validate response on the basis of per, page and Filter params
	 * @author PUNIT GARG
	 * @param response
	 * @param countPerPage
	 * @param pageCount
	 * @param filterName
	 * @throws JSONException
	 */
	
	public static void validateFilteredData(Response response, int countPerPage, int pageCount, String filterName)
			throws JSONException {
		validateResponseCode(response);
		
		countPerPage = (countPerPage > 100) ? 100 : Math.abs(countPerPage);
		pageCount = (pageCount == 0) ? 1 : Math.abs(pageCount);
		JSONObject JSONResponseBody = new JSONObject(response.body().asString());
		if (!filterName.toLowerCase().contains("twitter") && !filterName.toLowerCase().equalsIgnoreCase("")) {
			Assert.assertEquals((JSONResponseBody.getJSONObject("posts").getJSONArray("items").length() == 0),
					true, "Number of posts for any feed other than twitter is 0");
		}
		Assert.assertEquals(JSONResponseBody.getJSONObject("posts").getInt("current_page"), pageCount, "Expected page:"
				+ pageCount + ", actual page: " + JSONResponseBody.getJSONObject("posts").getInt("current_page"));
		Assert.assertEquals(JSONResponseBody.getJSONObject("posts").getInt("limit_value"), countPerPage, "Expected limit:" 
				+ countPerPage +", actual limit:" + JSONResponseBody.getJSONObject("posts").getInt("limit_value"));

	}
	
	public static void validateResponseCode(Response response) {
		response.then().statusCode(200);
		
	}
}
