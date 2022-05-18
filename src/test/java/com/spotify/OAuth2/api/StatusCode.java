package com.spotify.OAuth2.api;

public enum StatusCode {

	CODE_200(200, ""),
	CODE_201(201, ""),
	CODE_400(400, "Missing required field: name"),
	CODE_401(401, "Invalid access token");
	
	private final int code;    //we can also use public access modifier
	private final String msg;
	
	StatusCode(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
}
