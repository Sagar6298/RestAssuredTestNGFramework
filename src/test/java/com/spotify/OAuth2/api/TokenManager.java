package com.spotify.OAuth2.api;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.OAuth2.utils.ConfigLoader;

import static com.spotify.OAuth2.api.ReusableApiMethods.postAccount;
import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public static String getToken() {
		try {
			if(access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing Token........");
				Response response = renewToken();
				access_token = response.path("access_token");
				int expiryDurationInSeconds = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			}
			else {
				System.out.println("Token is good to use");
			}
		} catch (Exception e) {
			throw new RuntimeException("Abroat!!! Renew Token Failed");
		}
		return access_token;
	}
	
	private static Response renewToken() {
		HashMap<String, String> formParm = new HashMap<String, String>();
		formParm.put("client_id", ConfigLoader.getInstance().getClientId());
		formParm.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		formParm.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formParm.put("grant_type", ConfigLoader.getInstance().getGrantType());
		
		Response response = postAccount(formParm);
		
		if(response.statusCode() != 200) {
			throw new RuntimeException("Abroat!!! Renew Token Failed");
		}
		return response;
	}

}
