package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUtil {
    
	//Verify the http response status returned. Check Status Code is 200?
    public void checkStatusIs200(Response res) {
        Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
    }
    
    public void getTotalPostsCount() {
    	
    }
    
    //Get Clients
    public <T> ArrayList<T> getClients(JsonPath jp) {
        return jp.get();
    }
    
    public String getProperty(String propertyName) throws IOException {
    	InputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
    	Properties prop = new Properties();

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        return prop.getProperty(propertyName);
    }
}
