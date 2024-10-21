package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserTests {
	
	Faker fake;
	User userPayload;
	public Logger logger;
	
	@BeforeClass()
	void setUp()
	{
		fake = new Faker();
		userPayload = new User();
		
		userPayload.setId(fake.number().hashCode());
		userPayload.setUsername(fake.name().username());
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		userPayload.setPassword(fake.internet().password(5, 10));
		userPayload.setPhone(fake.phoneNumber().cellPhone());
		
		//Logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("debugging.....");
	}
	
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("*************Creating User***************");
		Response res = Userendpoints.createUser(userPayload);
		
	//	System.out.println(res.asPrettyString());
		res.then().statusCode(200);
		logger.info("*************User is Created***************");
	}
	
	@Test(priority=2, dependsOnMethods = "testCreateUser")
	public void testgetUserByName()
	{
		logger.info("*************Get User By Name***************");
		
		Response res = Userendpoints.getUser(this.userPayload.getUsername());
		
		//Validation
		
		System.out.println(res.asPrettyString());
		
		Assert.assertEquals(res.statusCode(), 200); 
		res.then().body("username", equalTo(this.userPayload.getUsername()));
		res.then().body("firstName", equalTo(this.userPayload.getFirstName()));
		res.then().body("lastName", equalTo(this.userPayload.getLastName()));
		res.then().body("email", equalTo(this.userPayload.getEmail()));
		res.then().body("phone", equalTo(this.userPayload.getPhone()));	

		logger.info("*************User found***************");
	}
	
	@Test(priority=3, dependsOnMethods = "testCreateUser")
	public void testUpdateUser()
	{
		
		//Update data using the payload
		userPayload.setFirstName(fake.name().firstName());
		userPayload.setLastName(fake.name().lastName());
		userPayload.setEmail(fake.internet().safeEmailAddress());
		
		logger.info("*************Update User Data***************");
		
		Response res = Userendpoints.updateUser(this.userPayload.getUsername(), userPayload);
		
		//Validation
	//	System.out.println(res.asPrettyString());
		Assert.assertEquals(res.statusCode(), 200);
		
		logger.info("*************User Data Updated***************");
		
		//checking data ater update:
		
	//	Response respo = Userendpoints.getUser(this.userPayload.getUsername());
	//	respo.then().statusCode(200);
	}
	
	@Test(priority=4, dependsOnMethods = "testCreateUser")
	public void deleteUser()
	{
		logger.info("*************Delete User***************");
		
		Response res = Userendpoints.deleteUser(this.userPayload.getUsername());
		res.then().statusCode(200);
		
		logger.info("*************Delete User***************");
	}

}
