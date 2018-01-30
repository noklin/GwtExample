package com.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Launcher implements EntryPoint {
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	public void onModuleLoad() {
		loginService.isAuthorized(new AsyncCallback<String>() {
			@Override
			public void onSuccess(String result) {
				if(result.equals("null")){
					showLoginWidget();
				}else{
					showInfo(result);
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
			
		});
		
	}
	
	private void showInfo(String login){
		HTMLPanel pnl = new HTMLPanel("");
		pnl.add(new Label("Hello " + login));
		Button btn = new Button("logout");
		pnl.add(btn);
		RootPanel.get().add(pnl);
	}
	
	private void showLoginWidget(){
		HTMLPanel pnl = new HTMLPanel("");
		TextBox loginTb = new TextBox();
		TextBox passwordTb = new TextBox();
		Label lbl = new Label();
		loginTb.getElement().setAttribute("plaseHolder", "login");
		passwordTb.getElement().setAttribute("plaseHolder", "password");
		Button btn = new Button("sign in");
		pnl.add(loginTb);
		pnl.add(passwordTb);
		pnl.add(btn);
		pnl.add(lbl);
		RootPanel.get().add(pnl);
		btn.addClickHandler(event -> {
			loginService.signIn(loginTb.getText(), passwordTb.getText(), new AsyncCallback<Boolean>() {
				@Override
				public void onSuccess(Boolean result) {
					if(result){
						Window.Location.replace("/");
					}else{
						lbl.setText("nope");
					}
				}
				
				@Override
				public void onFailure(Throwable caught) {
					
				}
			});
		});
	}


}