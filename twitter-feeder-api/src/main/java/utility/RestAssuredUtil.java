package utility;


import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {
	// Sets Base URI
	public static void setBaseURI() {
		RestAssured.baseURI = "https://www.juicer.io/";
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

	// Returns JsonPath object
	public static JsonPath getJsonPath(Response res) {
		String json = res.asString();
		return new JsonPath(json);
	}
}