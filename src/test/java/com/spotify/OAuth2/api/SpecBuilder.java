package com.spotify.OAuth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.spotify.OAuth2.api.Routes.*;

public class SpecBuilder {
	
	public static RequestSpecification getRequestSpecification() {
		return new RequestSpecBuilder().
				setBaseUri(System.getProperty("BASE_URI")).
//				setBaseUri("https://api.spotify.com").
				setBasePath(BASE_PATH).
				setContentType(ContentType.JSON).
				addFilter(new AllureRestAssured()).
				log(LogDetail.ALL).
				build();
	}
	
	public static RequestSpecification getAccountRequestSpecification() {
		return new RequestSpecBuilder().
				setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
//				setBaseUri("https://accounts.spotify.com").
				setContentType(ContentType.URLENC).
				addFilter(new AllureRestAssured()).
				log(LogDetail.ALL).
				build();
	}
		
	public static ResponseSpecification getResponseSpecification() {
		return new ResponseSpecBuilder().
				//expectContentType(ContentType.JSON).
				log(LogDetail.ALL).
				build();
	}
}
