import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestNGListener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;


/**
 * @author Avinash Patel
 *
 */
public class API_Tests implements ITestNGListener {

	static Logger log = Logger.getLogger(API_Tests.class);
	String BASE_URL="https://api.chucknorris.io/";
	ArrayList<String> categories = new ArrayList<String>();

	@BeforeMethod
	public void beforeTest(Method method){
		log.info("------------------------------------------------");
		log.info("Starting executing :" +method.getName());
	}

	@AfterMethod
	public void afterTest(ITestResult result){
		log.info("Finished executing testcase :" +result.getMethod().getMethodName());
		log.info("------------------------------------------------");
	}

	@Test(priority=1, description="To check whether the no. of categories match")
	public void testcase_1() throws Exception {
		Response response = given().contentType("application/json").when().get(BASE_URL+"jokes/categories");
		log.info("Executed the GET request with URL: "+BASE_URL+"jokes/categories");
		if(response.getStatusCode()==200){
			JSONArray ja = new JSONArray(response.getBody().asString());
			Assert.assertEquals(ja.length(),16, "No. of categories differ in the response");
			 for(int i=0; i< ja.length();i++){
				categories.add(ja.get(i).toString());
			}
		}
	}

	@Test(priority=2, description="To check category name is same in the response")
	public void testcase_2() throws Exception {
		for (String category: categories){
			Response response = given().contentType("application/json").when().get(BASE_URL+"jokes/random?category="+category);
			log.info("Executed the GET request with URL: "+BASE_URL+"jokes/random?category="+category);
			JSONObject jo = new JSONObject(response.getBody().asString());
			JSONArray ja = jo.getJSONArray("categories");
			Assert.assertEquals(ja.get(0),category,"Category mismatch.");
		}
	}

	@Test(priority=3, description="To check if response contains particular text, last modification and valid image url")
	public void testcase_3() throws Exception {
		Response response = given().contentType("application/json").when().get(BASE_URL+"jokes/1BYqNs0MSzmtl9ivZikisA");
		log.info("Executed the GET request with URL: "+BASE_URL+"jokes/1BYqNs0MSzmtl9ivZikisA");
		JSONObject jo = new JSONObject(response.getBody().asString());
		Assert.assertTrue(jo.get("value").toString().contains("entire song"),"Entire song text is not present in the value attribute");
		String updateAt_given="2016-01";
		String updateAt_response= jo.get("updated_at").toString();
		SimpleDateFormat date1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat date2= new SimpleDateFormat("yyyy-MM");
		Date updaDate1= date1.parse(updateAt_response);
		Date updaDate2= date2.parse(updateAt_given);
		Assert.assertTrue(updaDate1.after(updaDate2),"");
		String icon_url= jo.get("icon_url").toString();
		Assert.assertEquals(given().contentType("application/json").when().get(icon_url).getStatusCode(),200, "Icon url is not valid");
	}

	@Test(priority=4, description="To check invalid url returns 404 error code and not a joke")
	public void testcase_4() throws Exception {
		Response response = given().contentType("application/json").when().get(BASE_URL+" jokes/1BYqNs0MSzmtl9ivZikisAxxxxxx");
		log.info("Executed the GET request with URL: "+BASE_URL+" jokes/1BYqNs0MSzmtl9ivZikisAxxxxxx");
		Assert.assertEquals(response.getStatusCode(),404, "Status code is different");
	}
}
