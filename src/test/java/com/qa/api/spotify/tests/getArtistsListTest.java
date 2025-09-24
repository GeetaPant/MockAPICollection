package com.qa.api.spotify.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getArtistsListTest extends BaseTest {
	
	//private String token;
	
	@Test
	public void getArtistTest() {
		
		Map<String, String> queryParams = Map.of("id", "0TnOYISbd1XYRBk9myaseg");
		Response response = restClient.get(BASE_URL_SPOTIFY, "/v1/artists", queryParams, null, AuthType.OAUTH2, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	

}
