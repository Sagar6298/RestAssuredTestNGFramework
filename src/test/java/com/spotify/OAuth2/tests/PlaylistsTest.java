package com.spotify.OAuth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.spotify.OAuth2.api.StatusCode;
import com.spotify.OAuth2.api.applicationApi.PlaylistApi;
import com.spotify.OAuth2.pojo.Error;
import com.spotify.OAuth2.pojo.Playlist;
import com.spotify.OAuth2.utils.DataLoader;
import static com.spotify.OAuth2.utils.FakerUtils.*;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;

@Epic("Spotify OAuth2.0")
@Feature("Playlist")
public class PlaylistsTest {
	
	@Story("Create a playlist")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@TmsLink("12345")
	@Issue("7236924")
	@Description("In this test we are testing that user is able to create a playlist")
	@Test(description = "Should be able to create a playlist")
	public void ShouldBeAbleToCreateAPlaylist() {
		
//		Playlist requestPlaylist = new Playlist().     				//Builder pattern with pojo
//				setName("New Playlist").
//				setDescription("New playlist Description").
//				setPublic(false);
//		Response response = PlaylistApi.post(requestPlaylist);
//		assertThat(response.statusCode(), equalTo(201));
//		Playlist responsePlaylist = response.as(Playlist.class);
//		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
//		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
//		assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
		
		Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
		Response response = PlaylistApi.post(requestPlaylist);
		assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
	}
	
	@Test
	public void ShouldBeAbleToGetAPlaylist() {
		
		Playlist requestPlaylist = playlistBuilder("New Playlist", "New playlist Description", false);
		
		Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
		assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
		assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
	}
	
	@Test
	public void ShouldBeAbleToUpdateAPlaylist() {
		
//		Playlist requestPlaylist = new Playlist().     				//Builder pattern with pojo
//				setName("New Playlist").
//				setDescription("New playlist Description").
//				setPublic(false);
//		
//		Response response = PlaylistApi.put("5LJCPXG1PQaC7iRPGtmjtG", requestPlaylist);
//		assertThat(response.statusCode(), equalTo(200));
		
		Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
		Response response = PlaylistApi.put(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
		assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
	}
	
	@Story("Create a playlist")
	@Test
	public void ShouldNotBeAbleToCreateAPlaylistWithoutName() {
		
		Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
		
		Response response = PlaylistApi.post(requestPlaylist);
		assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());
		assertError(response.as(Error.class), StatusCode.CODE_400.getCode(), StatusCode.CODE_400.getMsg());
	}
	
	@Story("Create a playlist")
	@Test
	public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken() {
		String invalid_token = "12345";
		
		Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
		
		Response response = PlaylistApi.post(invalid_token, requestPlaylist);
		assertStatusCode(response.statusCode(), StatusCode.CODE_401.getCode());
		assertError(response.as(Error.class), StatusCode.CODE_401.getCode(), StatusCode.CODE_401.getMsg());
//		Error error = response.as(Error.class);
//		assertThat(error.getError().getStatus(), equalTo(401));
//		assertThat(error.getError().getMessage(), equalTo("Invalid access token"));	
	}
	
	@Step
	public Playlist playlistBuilder(String name, String description, boolean _public) {
		return Playlist.builder().              //we use this code with getter and setter annotations implementations with builder annotation
				name(name).
				description(description).
				_public(false).build();
//		return new Playlist().     				//Builder pattern with pojo
//				setName(name).
//				setDescription(description).
//				setPublic(_public);
		
//		Playlist playlist = new Playlist();    //we use this code with getter and setter annotations implementations without builder
//		playlist.setName(name);
//		playlist.setDescription(description);
//		playlist.set_public(_public);
//		return playlist;
	}
	
	@Step
	public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
	}
	
	@Step
	public void assertStatusCode(int actualStatusCode , int expectedStatusCode) {
		assertThat(actualStatusCode, equalTo(expectedStatusCode));
	}
	
	@Step
	public void assertError(Error responseErr, int expectedStatusCode, String expectedMsg) {
		assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
		assertThat(responseErr.getError().getMessage(), equalTo(expectedMsg));
	}
}
