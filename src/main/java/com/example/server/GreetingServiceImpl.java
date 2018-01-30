package com.example.server;

import java.util.HashMap;
import java.util.Map;

import com.example.client.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements LoginService {

	private static Map<String,String> legalUsers = new HashMap<>();
	
	static{
		legalUsers.put("noklin", "1234");
	}
	
	@Override
	public String isAuthorized() {
		Object obj = getThreadLocalRequest().getSession().getAttribute("AUTH");
		return String.valueOf(obj);
	}

	@Override
	public boolean signIn(String login, String password) {
		String pswd = legalUsers.get(login);
		boolean result = pswd != null && pswd.equals(password);
		if(result){
			getThreadLocalRequest().getSession().setAttribute("AUTH", login);
		}
		return result;
	}

}