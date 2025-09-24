package com.qa.api.tests;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserwithDeserializationTest extends BaseTest {
	
	@Test
	public void getAllUsers() {
			Response response = restClient.get(BASE_URL,"public/v2/users", null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
			User[] user =JsonUtils.deserialize(response, User[].class);
			
			System.out.println(Arrays.toString(user));
				
				for(User u : user) {
					System.out.println("Id :" +u.getId());
					System.out.println("Name :" +u.getName());
					System.out.println("Email :" +u.getEmail());
					System.out.println("Gender :" +u.getGender());
					System.out.println("Status :" +u.getStatus());
					System.out.println("*******************************");
					
					
					
				}
			
}
}