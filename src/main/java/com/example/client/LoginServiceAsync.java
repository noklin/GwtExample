package com.example.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface LoginServiceAsync {
	void signIn(String login, String password, AsyncCallback<Boolean> callback);
	void isAuthorized(AsyncCallback<String> callback);
}
