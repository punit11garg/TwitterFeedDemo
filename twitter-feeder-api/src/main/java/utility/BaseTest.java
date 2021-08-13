package utility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    public Response res = null; //Response
    public JsonPath jp  = null; //JsonPath

    //Instantiate a Helper Test Methods (testUtils) Object
    protected TestUtil testUtil = new TestUtil();
    protected RequestSpecification request;

    @BeforeClass
    public void setup() {
        //Test Setup
    	RestAssuredUtil.setBaseURI(); //Setup Base URI
        RestAssuredUtil.setBasePath("api/feeds/punitgarg1988"); //Setup Base Path
        request  = RestAssuredUtil.getRequestSpec();
        RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssuredUtil.resetBaseURI();
        RestAssuredUtil.resetBasePath();
    }
}
