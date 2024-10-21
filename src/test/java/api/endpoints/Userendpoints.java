package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.response.Response;

/*
 * Userendpoints.java
 * Created to perform CURD(Create,Update,Retrieve and Delete) operations
 */

public class Userendpoints {
	
	public static Response createUser(User payload)
		{
			Response res = given()
				.header("Content-Type", "application/json")
				.header("accpept", "appplication/json")
				.body(payload)
			.when()
				.post(Routes.user_post_url);
			return res;
		}
	
		public static Response getUser(String UserName)
		{
			Response res = given()
				.header("accpet", "application/json")
				.pathParam("username", UserName)
			.when()
				.get(Routes.user_get_url);
			return res;			
		}
	
		public static Response updateUser(String UserName, User payload)
		{

			Response res = given()
				.header("Content-Type", "application/json")
				.header("accpept", "appplication/json")
				.body(payload)
				.pathParam("username", UserName)
			
			.when()
				.put(Routes.user_put_url);
			return res;
		}

		
		public static Response deleteUser(String UserName)
		{
			Response res = given()
				.header("accpet", "application/json")
				.pathParam("username", UserName)
			.when()
				.get(Routes.user_delete_url);
			
			return res;
		}
}
