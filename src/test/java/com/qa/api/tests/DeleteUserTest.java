package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {
	@Test
	public void createUserwithBuilderTest() {
		User user = User.builder()
		.name("Vedant")
		.email(StringUtils.getRandomEmailID())
		.gender("male")
		.status("active")
		.build();
		Response response = restClient.post(BASE_URL,"public/v2/users", user, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(),201);	
		
		//fetch User id
		String userid = response.jsonPath().getString("id");
		//2-GET Same user
		Response responseGet = restClient.get(BASE_URL,"public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(),200);	
		Assert.assertEquals(responseGet.jsonPath().getString("id"), userid);	
		Assert.assertEquals(responseGet.jsonPath().getString("name"), user.getName());	
	
		//3 - Delete same user
		Response responseDel = restClient.delete(BASE_URL,"public/v2/users/" +userid, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(responseDel.getStatusCode(),204);
		
		//4 - Try Get same user
		Response responseGet1 = restClient.get(BASE_URL,"public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(responseGet1.getStatusCode(),404);	
	}
}
