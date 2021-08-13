package com.feederapi.interview;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseTest;
import utility.RestAssuredUtil;

public class TestGetMethod extends BaseTest {
	ValidateResponse vr = new ValidateResponse();
	int totalPostCount;
	
	@BeforeMethod
	public void getTotalPostsCount() {
		RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("api/feeds/punitgarg1988"); //Setup Base Path
        RequestSpecification request = RestAssuredUtil.getRequestSpec();
		Response res = RestAssuredUtil.getResponse(request);
		HashMap<String, Object> postsMap = RestAssuredUtil.getJsonPath(res).get("posts");
		List<Object> itemList = (List<Object>) postsMap.get("items");
		totalPostCount = itemList.size();
	}

	@Test(priority = 1, enabled = true)
	public void testTweetCount() {
		
		request.queryParam("per", 1);
		Response res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isPostCountExpected(res, 1, totalPostCount);
		
		request.queryParam("per", 0);
		res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isPostCountExpected(res, 0, totalPostCount);
		
		request.queryParam("per", 120);	
		res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isPostCountExpected(res, 120, totalPostCount);
		
		
		request.queryParam("per", -1);	
		res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isPostCountExpected(res, -1, totalPostCount);
		
	}
	
	@Test(priority = 1, enabled=false)
	public void testTweetCountPerPage() {
		int totalPost = totalPostCount;
		
		request.queryParam("page", 1);
		Response res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isCurrentPageExpected(res, 1, 0, totalPostCount);
		
		request.queryParam("per", 1);
		request.queryParam("page", ++totalPost);
		res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isCurrentPageExpected(res, totalPost, 1, totalPostCount);
		
//		request.queryParam("per", 0);
//		res = RestAssuredUtil.getResponse(request);
//		ValidateResponse.isPostCountExpected(res, 0, totalPostCount);
//		
//		request.queryParam("per", 120);	
//		res = RestAssuredUtil.getResponse(request);
//		ValidateResponse.isPostCountExpected(res, 120, totalPostCount);
//		
//		
//		request.queryParam("per", -1);	
//		res = RestAssuredUtil.getResponse(request);
//		ValidateResponse.isPostCountExpected(res, -1, totalPostCount);
		
	}

}
