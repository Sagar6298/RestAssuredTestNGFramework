package com.spotify.OAuth2.tests;

import java.lang.reflect.Method;

public class BaseTest {

	public void beforeMethod(Method m) {
		System.out.println("STARTING TESTS" + m.getName());
		System.out.println("THREAD ID" + Thread.currentThread().getId());
	}
}
