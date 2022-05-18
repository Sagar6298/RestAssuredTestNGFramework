package com.spotify.OAuth2.api;

import static com.spotify.OAuth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.OAuth2.api.SpecBuilder.getResponseSpecification;
import static com.spotify.OAuth2.api.SpecBuilder.getAccountRequestSpecification;
import static com.spotify.OAuth2.api.Routes.*;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.response.Response;

public class ReusableApiMethods {

	public static Response post(String path, String token, Object requestPlaylist) {
		return given(getRequestSpecification()).
				body(requestPlaylist).
				auth().oauth2(token).      			//also pass token using auth method
//				header("Authorization", "Bearer " + token).
			when().
				post(path).
			then().spec(getResponseSpecification()).
				extract().
				response();
	}
	
	public static Response postAccount(HashMap<String, String> formParm) {
		return given(getAccountRequestSpecification()).
					formParams(formParm).
				when().
					post(API + TOKEN).
				then().spec(getResponseSpecification()).
					extract().
					response();
	}
	
	public static Response get(String path, String token) {
		return given(getRequestSpecification()).
					auth().oauth2(token).
//					header("Authorization", "Bearer " + token).
				when().
					get(path).
				then().spec(getResponseSpecification()).
					extract().
					response();
	}
	
	public static Response put(String path, String token, Object requestPlaylist) {
		return given(getRequestSpecification()).
				auth().oauth2(token).
//				header("Authorization", "Bearer " + token).
				body(requestPlaylist).
			when().
				put(path).
			then().spec(getResponseSpecification()).
				extract().
				response();
	}
}
