package com.spotify.OAuth2.api.applicationApi;

import com.spotify.OAuth2.api.ReusableApiMethods;
import com.spotify.OAuth2.pojo.Playlist;
import com.spotify.OAuth2.utils.ConfigLoader;

import static com.spotify.OAuth2.api.TokenManager.getToken;
import static com.spotify.OAuth2.api.Routes.*;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PlaylistApi {

	@Step
	public static Response post(Playlist requestPlaylist) {
		return ReusableApiMethods.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);
	}
	
	@Step
	public static Response post(String token , Playlist requestPlaylist) {
		return ReusableApiMethods.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
	}
	
	@Step
	public static Response get(String playlistId) {
		return ReusableApiMethods.get(PLAYLISTS + "/" + playlistId, getToken());
	}
	
	@Step
	public static Response put(String playlistId, Playlist requestPlaylist) {
		return ReusableApiMethods.put(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
	}
}
