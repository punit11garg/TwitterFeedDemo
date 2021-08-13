package com.feederapi.interview;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.BaseTest;
import utility.RestAssuredUtil;

public class TestGetMethod extends BaseTest {
	ValidateResponse vr = new ValidateResponse();
	int totalPostCount;
	int totalPost;
	
	@BeforeMethod
	public void getTotalPostsCount() {
		RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("api/feeds/punitgarg1988"); //Setup Base Path
        RequestSpecification request = RestAssuredUtil.getRequestSpec();
		Response res = RestAssuredUtil.getResponse(request);
		HashMap<String, Object> postsMap = RestAssuredUtil.getJsonPath(res).get("posts");
		List<Object> itemList = (List<Object>) postsMap.get("items");
		totalPostCount = itemList.size();
		totalPost = totalPostCount + 50;
	}

	@Test(priority = 1, enabled = true, dataProvider = "test-data")
	public void testTweetCount(String per, int count) {
		
		request.queryParam(per, count);
		Response res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isPostCountExpected(res, count, totalPostCount);
		
	}
	
	@DataProvider(name = "test-data")
	public Object[][] dataProvFunc(){
		return new Object[][] {
			{"per", 1}, {"per", 0}, {"per", -1}, {"per", totalPost}
		};
	}
	
	@Test(priority = 1, dataProvider = "test-data1")
	public void testTweetCountPerPage(String s1, int c1, String s2, int c2) {
		request.queryParam(s1, c1);
		request.queryParam(s2, c2);
		Response res = RestAssuredUtil.getResponse(request);
		ValidateResponse.isCurrentPageExpected(res, c2, c1, totalPostCount);

	}
	
	
	@DataProvider(name = "test-data1")
	public Object[][] dataProvFunc1(){
		int totalPost = totalPostCount + 50;
		return new Object[][] {
			{"per", 1, "page", 1}, {"per", 50, "page", 2}, {"per", 50, "page", 10000},  {"per", 0, "page", 20},
			{"per", 0, "page", 0}, {"per", -1, "page", -1}
		};
	}

}
