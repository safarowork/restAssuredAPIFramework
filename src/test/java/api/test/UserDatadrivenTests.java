package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.utilities.User_DataProviders;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserDatadrivenTests {
	
	User payload;
	
	@Test(priority=1, dataProvider = "Data", dataProviderClass = api.utilities.User_DataProviders.class)
	void testCreateUser(String id, String username, String firstname, String lastname, 
						String email, String password, String phone)
	{
		payload = new User();
		
		payload.setId(Integer.parseInt(id));  //ID is in int type
		payload.setUsername(username);
		payload.setFirstName(firstname);
		payload.setLastName(lastname);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response res = Userendpoints.createUser(payload);
		
		res.then().statusCode(200);
	}
	
	
	@Test(priority=3, dataProvider = "userNames", dataProviderClass = api.utilities.User_DataProviders.class)
	void testDeleteUser(String username)
	{
		Response res = Userendpoints.deleteUser(username);
		res.then().statusCode(200);
	}
	
	
}
