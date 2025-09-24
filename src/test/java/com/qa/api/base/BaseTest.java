package com.qa.api.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.api.client.RestClient;
import com.qa.api.managers.ConfigManagers;

import io.restassured.RestAssured;
import io.qameta.allure.restassured.AllureRestAssured;


public class BaseTest {
	
	protected final static String BASE_URL ="https://gorest.co.in";
	protected final static String BASE_URL_BASICAUTH ="https://the-internet.herokuapp.com";
	protected final static String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_FAKESTOREAPI = "https://fakestoreapi.com";
	protected final static String BASE_URL_BASICAUTHAPI = "https://the-internet.herokuapp.com";
	protected final static String BASE_URL_AMADEUSAPI = "https://test.api.amadeus.com";
	protected final static String BASE_URL_SPOTIFY = "https://api.spotify.com";
	
	protected RestClient restClient;
	
	@BeforeTest
	public void setup() {
		RestAssured.filters(new AllureRestAssured());
		restClient = new RestClient();
		
	}
}
