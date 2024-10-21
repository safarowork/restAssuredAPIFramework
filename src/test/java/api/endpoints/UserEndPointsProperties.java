package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import api.payload.User;
import api.utilities.ReadProperties;
import io.restassured.response.Response;

public class UserEndPointsProperties {	
	
	//Using properties file created in Utility package to access the URLS instead of the routes class
	
	public static Response createUser(User payload) throws IOException
	{
	//	readProperties().getProperty(DEFAULT_BODY_ROOT_PATH)
		Response res = given()
			.header("Content-Type", "application/json")
			.header("accpept", "appplication/json")
			.body(payload)
		.when()
			.post(ReadProperties.readPropertiesConig().getProperty("user_post_url"));
		return res;
	}

	public static Response getUser(String UserName) throws IOException
	{
		Response res = given()
			.header("accpet", "application/json")
			.pathParam("username", UserName)
		.when()
			.get(ReadProperties.readPropertiesConig().getProperty("user_get_url"));
		return res;			
	}

	public static Response updateUser(String UserName, User payload) throws IOException
	{

		Response res = given()
			.header("Content-Type", "application/json")
			.header("accpept", "appplication/json")
			.body(payload)
			.pathParam("username", UserName)
		
		.when()
			.put(ReadProperties.readPropertiesConig().getProperty("user_put_url"));
		return res;
	}

	
	public static Response deleteUser(String UserName) throws IOException
	{
		Response res = given()
			.header("accpet", "application/json")
			.pathParam("username", UserName)
		.when()
			.get(ReadProperties.readPropertiesConig().getProperty("user_delete_url"));
		
		return res;
	}

}
