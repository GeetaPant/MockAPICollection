package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;
import static org.hamcrest.Matchers.*;


import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkException;
import com.qa.api.managers.ConfigManagers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;

public class RestClient {
	
	
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec422 = expect().statusCode(422);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);

	
	//private  String baseUrl = ConfigManagers.get("baseUrl");
	
	private RequestSpecification setupRequest(String baseUrl,AuthType authType,ContentType contentType ) {
	RequestSpecification request = 	RestAssured.given().log().all()
							 		.baseUri(baseUrl)
							 			.contentType(contentType)
							 				.accept(contentType);

	switch(authType) {
	case BEARER_TOKEN:
		request.header("Authorization", "Bearer "+ConfigManagers.get("bearerToken"));
		 break;
	case CONTACTS_BEARER_TOKEN:
		request.header("Authorization", "Bearer "+ConfigManagers.get("contacts_bearer_token"));	
	case OAUTH2:
		request.header("Authorization", "Bearer "+generateOAuth2Token());
		break;
	case BASIC_AUTH:
		request.header("Authorization", "Basic " +generateBasicAuthToken());
		break;
	case API_KEY:
		request.header("Authorization", "Bearer "+ConfigManagers.get("apiKey"));
		break;
	case NO_AUTH:
		System.out.println("No Authorization Required");
		break;
	default:
		System.out.println("This Auth is not supported... ");
		 throw new FrameworkException("NO AUTH SUPPORTED");
	}
	return request;
}

	private String generateOAuth2Token() {
		return RestAssured.given()
			.formParam("client_id", ConfigManagers.get("clientId"))
			.formParam("client_secret", ConfigManagers.get("clientSecret"))
			.formParam("grant_Type", ConfigManagers.get("grantType"))
			.post(ConfigManagers.get("tokenUrl"))
			.then()
			.extract()
			.path("access_token");
	}
	
	private String generateBasicAuthToken() {
		String creds = ConfigManagers.get("basicUsername")+ ":"+ConfigManagers.get("basicPassword");
		return Base64.getEncoder().encodeToString(creds.getBytes());
		}
	
// ************CRUD OPERATIONS*************
	
	public Response get( String baseUrl, String endPoint, Map<String, String> queryParams,
									Map<String, String> pathParams,
									AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl,authType, contentType);
		
		applyParams(request,queryParams,pathParams );
		Response response =request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
		}
	
	public <T>Response post(String baseUrl, String endPoint, T body,  Map<String, String> queryParams,
			Map<String, String> pathParams,AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl,authType, contentType);
		
		applyParams(request,queryParams,pathParams );
		Response response = request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
	}
	public Response post(String baseUrl,String endPoint, File file,  Map<String, String> queryParams,
			Map<String, String> pathParams,AuthType authType, ContentType contentType)
	{
RequestSpecification request = setupRequest(baseUrl,authType, contentType);
		
		applyParams(request,queryParams,pathParams );
		Response response = request.body(file).post(endPoint).then().spec(responseSpec201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T>Response put(String baseUrl,String endPoint, T body,  Map<String, String> queryParams,
			Map<String, String> pathParams,AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl,authType, contentType);
	
		applyParams(request,queryParams,pathParams );
		Response response = request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response; 
	}

	
	public <T>Response patch(String baseUrl,String endPoint, T body,  Map<String, String> queryParams,
			Map<String, String> pathParams,AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl,authType, contentType);
	
		applyParams(request,queryParams,pathParams );
		Response response = request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public Response delete(String baseUrl,String endPoint, Map<String, String> queryParams,
			Map<String, String> pathParams,AuthType authType, ContentType contentType)
	{
		RequestSpecification request = setupRequest(baseUrl,authType, contentType);
	
		applyParams(request,queryParams,pathParams );
		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}
	
	
private void applyParams(RequestSpecification request, Map<String, String> queryParams,Map<String, String> pathParams) {
	if(queryParams !=null) {
		request.queryParams(queryParams);
	}
	if(pathParams !=null) {
		request.pathParams(pathParams);
	}	
}	
	}